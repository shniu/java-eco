package io.github.shniu.flashchat.client;

import io.github.shniu.flashchat.client.handler.LoginHandler;
import io.github.shniu.flashchat.client.handler.MessageHandler;
import io.github.shniu.flashchat.common.Logs;
import io.github.shniu.flashchat.common.net.ResponseHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @author niushaohan
 * @date 2020/12/21 14
 */
public class FlashChatClientApplication {
    private FlashChatClient chatClient;
    private UIComponent uiComponent;

    ResponseHandler responseHandler = new ResponseHandler() {
        @Override
        public void onMessage(String message) {
            // 根据收到的消息类型来处理
            System.out.println("收到好友 {who} 发来的消息: " + message);
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

        uiComponent = new UIComponent();
        uiComponent.registerLoginHandler(new LoginHandler(chatClient));
        uiComponent.registerHandler(new MessageHandler() {
            @Override
            public void onMessage(String message) {
                chatClient.send(message);
            }
        });
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
