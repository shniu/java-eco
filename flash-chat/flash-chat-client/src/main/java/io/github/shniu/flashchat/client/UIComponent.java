package io.github.shniu.flashchat.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import io.github.shniu.flashchat.client.domain.User;
import io.github.shniu.flashchat.client.handler.MessageHandler;
import io.github.shniu.flashchat.common.Jsons;
import io.github.shniu.flashchat.common.Logs;
import io.github.shniu.flashchat.common.protocol.CommandType;
import io.github.shniu.flashchat.common.protocol.command.LoginBean;
import io.github.shniu.flashchat.common.protocol.command.QueryBean;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author niushaohan
 * @date 2020/12/25 14
 */
public class UIComponent {
    UI ui;

    public UIComponent(Context context, Object monitor) {
        ui = new UI(context, monitor);
    }

    public void stop() {
        ui.stop();
    }

    public void start() {
        Thread uiHolder = new Thread(ui, "ui-component");
        uiHolder.start();

        while (!ui.isInitialized()) {
            // System.out.println("Waiting for initialized.");
        }
    }

    public void registerHandler(MessageHandler handler) {
        ui.register(handler);
    }

    static class UI implements Runnable {
        private final Object monitor;
        private volatile boolean running;
        private InputListener inputListener;
        private volatile boolean initialized = false;

        // List<MessageHandler> messageHandlers;
        // private MessageHandler loginHandler;
        Map<CommandType, MessageHandler> handlers;

        private Context context;

        // 当前所处界面
        private Page currentPage;

        public UI(Context context, Object monitor) {
            this.monitor = monitor;
            this.context = context;

            handlers = Maps.newHashMap();

            this.running = true;
            inputListener = new ConsoleInputListener();
            currentPage = Page.Login;
        }

        public void register(MessageHandler handler) {
            handlers.put(handler.commandType(), handler);
        }

        public boolean isInitialized() {
            return initialized;
        }

        @Override
        public void run() {
            initialized = true;

            Logs.printLogin();
            while (!loginSucceed()) {
                Logs.printLogin();
            }

            // 登录成功后，拉取好友列表
            Logs.printFetchFriends();
            while (!fetchSucceed()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Logs.printFetchFriends();
            }

            String username = null;
            String userId = null;

            while (running) {
                if (Page.Main.equals(currentPage)) {
                    System.out.println("您当前处于主页面，请选择一个好友进行聊天(输入 @{好友名称}, 比如 @John): ");
                    String message = inputListener.listening();
                    if (message.startsWith("@")) {
                        username = message.substring(message.indexOf("@") + 1);

                        User friend = context.findFriend(username);
                        if (Objects.nonNull(friend)) {
                            currentPage = Page.Chatting;
                            userId = friend.getUserId();
                        } else {
                            System.out.println("Your friend " + username + " does not exist.");
                        }
                    }
                } else if (Page.Chatting.equals(currentPage)) {
                    System.out.println("当前正在和 {" + username + "} 聊天, 请输入消息: ");
                    String message = inputListener.listening();

                    if (message.equalsIgnoreCase("Quit")) {
                        username = null;
                        userId = null;
                        currentPage = Page.Main;
                        continue;
                    }

                    // 封装 message
                    MessageHandler chatHandler = handlers.get(CommandType.CHAT);
                    chatHandler.onMessage(message);
                }
            }
        }

        private boolean fetchSucceed() {
            if (!context.isFetched()) {
                QueryBean queryBean = new QueryBean(context.getUser().getUserId());
                String query = Jsons.writeValueAsString(queryBean);
                MessageHandler queryHandler = handlers.get(CommandType.QUERY_FRIEND);
                queryHandler.onMessage(query);

                synchronized (monitor) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (context.isFetched()) {
                Set<String> set = context.getFriends().stream()
                        .map(user -> Joiner.on("\t\t\t\t").join(user.getUserId(), user.getUsername()))
                        .collect(Collectors.toSet());
                Logs.printFriends(set);
            }

            return context.isFetched();
        }

        boolean loginSucceed() {
            if (!context.isLogin()) {
                // 还没有登录
                currentPage = Page.Login;

                System.out.println("请输入用户名: ");
                String username = inputListener.listening();
                System.out.println("请输入密码: ");
                String password = inputListener.listening();

                LoginBean loginBean = new LoginBean(username, password);
                try {
                    String loginMessage = Jsons.getObjectMapper().writeValueAsString(loginBean);
                    MessageHandler loginHandler = handlers.get(CommandType.LOGIN);
                    loginHandler.onMessage(loginMessage);

                    // 进入 wait
                    synchronized (monitor) {
                        try {
                            monitor.wait();
                            System.out.println("wakeup");
                            System.out.println(context.getUser());
                            System.out.println(context.isLogin());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }

            if (context.isLogin()) {
                Logs.printLoginSucceed();
                currentPage = Page.Main;
            }

            return context.isLogin();
        }

        public void stop() {
            running = false;
        }
    }

    enum Page {
        // 客户端还没有登录，位于登录界面，先登录
        Login,
        // 当前客户端处于主界面，登录成功后进入，在这个界面下可以接收各种命令做进一步操作
        Main,
        // 当前客户端处于聊天中，只能收发消息，如果要做其他事情，需要退出
        Chatting
    }
}
