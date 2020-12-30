package io.github.shniu.flashchat.common;

import java.util.Set;

/**
 * @author niushaohan
 * @date 2020/12/25 16
 */
public final class Logs {

    public static void logWelcome() {
        System.out.println("===============================");
        System.out.println("Welcome to Flash Chat");
        System.out.println("===============================");
    }

    public static void printLogin() {
        System.out.println("您还没有登录，请先登录！");
    }

    public static void printLoginSucceed() {
        System.out.println("登录成功！");
    }

    public static void printFetchFriends() {
        System.out.println("Fetching your friend lists.");
    }

    public static void printFriends(Set<String> friends) {
        StringBuilder sb = new StringBuilder();
        sb.append("----------------").append("\n");
        sb.append("UserID\t\t\t").append("Username\n");
        friends.forEach(s -> sb.append(s).append("\n"));
        sb.append("----------------").append("\n");

        System.out.print(sb.toString());
    }
}
