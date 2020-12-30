package io.github.shniu.flashchat.client;

import io.github.shniu.flashchat.client.domain.User;
import io.github.shniu.flashchat.client.handler.ChatHandler;
import io.github.shniu.flashchat.client.handler.LoginHandler;
import io.github.shniu.flashchat.client.handler.MessageHandler;
import io.github.shniu.flashchat.client.handler.QueryHandler;
import io.github.shniu.flashchat.common.Jsons;
import io.github.shniu.flashchat.common.Logs;
import io.github.shniu.flashchat.common.net.ResponseHandler;
import io.github.shniu.flashchat.common.protocol.Command;
import io.github.shniu.flashchat.common.protocol.CommandType;
import io.github.shniu.flashchat.common.protocol.command.LoginResponse;
import io.github.shniu.flashchat.common.protocol.command.QueryFriendResponse;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @author niushaohan
 * @date 2020/12/21 14
 */
public class FlashChatClientApplication {
    private FlashChatClient chatClient;
    private UIComponent uiComponent;

    // 把 User 用作全局的，多线程共享，
    // 或者使用一个内部队列，然后由独立的线程来处理消息，进而更新 user 信息
    // 在发送 login 信息后，ui 线程进入 wait 状态，等待响应到达后的 notify
    private Context context = new Context();

    private final Object monitor = new Object();

    ResponseHandler responseHandler = new ResponseHandler() {
        @Override
        public void onMessage(String message) {
            // 根据收到的消息类型来处理
            System.out.println("收到好友 {who} 发来的消息: " + message);

            try {
                Command command = Jsons.readValue(message.getBytes(), Command.class);
                // 登录操作的响应
                if (command.getHeader().getType() == CommandType.LOGIN.getType()) {
                    LoginResponse loginResponse = Jsons.readValue(command.getPayload(), LoginResponse.class);
                    System.out.println("userId is " + loginResponse.getUserId());

                    User user = new User();
                    user.setUserId(loginResponse.getUserId());
                    context.setUser(user);

                    synchronized (monitor) {
                        monitor.notifyAll();
                    }
                } else if (command.getHeader().getType() == CommandType.QUERY_FRIEND.getType()) {
                    QueryFriendResponse friendResponse = Jsons.readValue(command.getPayload(), QueryFriendResponse.class);
                    friendResponse.getFriends().forEach(s -> {
                        String[] frd = s.split(",");
                        User u = new User();
                        u.setUserId(frd[0]);
                        u.setUsername(frd[1]);

                        context.addFriend(u);
                    });

                    synchronized (monitor) {
                        monitor.notifyAll();
                    }
                }
            } catch (RuntimeException e) {
                // e.printStackTrace();
            }
        }
    };

    public FlashChatClientApplication() {
    }

    boolean initAndStartChatClient() throws IOException {
        String endpointId = "Flash Chat Nio Client - 1";
        InetSocketAddress serverAddress = new InetSocketAddress("localhost", 10005);

        chatClient = new FlashChatClient(endpointId, serverAddress, responseHandler);
        chatClient.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!chatClient.isRunning()) {
            return false;
        }

        uiComponent = new UIComponent(context, monitor);
        uiComponent.registerHandler(new LoginHandler(chatClient));
        uiComponent.registerHandler(new QueryHandler(chatClient));
        uiComponent.registerHandler(new ChatHandler(chatClient));
        uiComponent.start();

        return true;
    }

    public void shutdown() {
        uiComponent.stop();

        try {
            chatClient.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void run(String[] args) {
        FlashChatClientApplication application = new FlashChatClientApplication();

        try {
            boolean started = application.initAndStartChatClient();

            if (started) {
                Logs.logWelcome();
            }
        } catch (IOException e) {
            e.printStackTrace();

            application.shutdown();
        }
    }

    public static void main(String[] args) {
        FlashChatClientApplication.run(args);
    }
}
