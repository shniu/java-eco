package io.github.shniu.flashchat.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.shniu.flashchat.client.domain.LoginBean;
import io.github.shniu.flashchat.client.domain.User;
import io.github.shniu.flashchat.client.handler.MessageHandler;
import io.github.shniu.flashchat.common.Jsons;
import io.github.shniu.flashchat.common.Logs;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author niushaohan
 * @date 2020/12/25 14
 */
public class UIComponent {
    UI ui;

    public UIComponent() {
        ui = new UI();
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

    public void registerLoginHandler(MessageHandler handler) {
        ui.registerLoginHandler(handler);
    }

    public void registerHandler(MessageHandler handler) {
        ui.register(handler);
    }

    static class UI implements Runnable {
        private volatile boolean running;
        private InputListener inputListener;
        private volatile boolean initialized = false;

        List<MessageHandler> messageHandlers;
        private MessageHandler loginHandler;

        private User user;

        // 当前所处界面
        private Page currentPage;

        public UI() {
            this.running = true;
            inputListener = new ConsoleInputListener();
            messageHandlers = new ArrayList<>();

            currentPage = Page.Login;
        }

        public void registerLoginHandler(MessageHandler handler) {
            this.loginHandler = handler;
        }

        public void register(MessageHandler handler) {
            messageHandlers.add(handler);
        }

        public boolean isInitialized() {
            return initialized;
        }

        @Override
        public void run() {
            initialized = true;

            // 这里要检查一下用户有没有登录
            while (running) {
                if (!loginSucceed()) {
                    continue;
                }

                // System.out.println("当前正在和 {who} 聊天, 请输入消息: ");
                String message = inputListener.listening();

                for (MessageHandler handler : messageHandlers) {
                    handler.onMessage(message);
                }
            }
        }

        boolean loginSucceed() {
            if (Objects.isNull(user)) {
                // 还没有登录
                currentPage = Page.Login;

                Logs.printLogin();

                System.out.println("请输入用户名: ");
                String username = inputListener.listening();
                System.out.println("请输入密码: ");
                String password = inputListener.listening();

                LoginBean loginBean = new LoginBean(username, password);
                try {
                    String loginMessage = Jsons.getObjectMapper().writeValueAsString(loginBean);
                    loginHandler.onMessage(loginMessage);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                return false;
            }

            return true;
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
