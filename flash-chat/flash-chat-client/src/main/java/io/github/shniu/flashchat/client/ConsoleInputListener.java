package io.github.shniu.flashchat.client;

import com.google.common.base.Strings;

import java.util.Scanner;

/**
 * @author niushaohan
 * @date 2020/12/25 15
 */
public class ConsoleInputListener implements InputListener {
    private Scanner scanner;

    public ConsoleInputListener() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String listening() {
        String line = null;
        while (Strings.isNullOrEmpty(line)) {
            line = scanner.nextLine();
            line = line.trim();

            if (Strings.isNullOrEmpty(line)) {
                System.out.println("输入的消息不合法，请重新输入.");
            }
        }

        return line;
    }
}
