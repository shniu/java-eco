package org.digcredit.project.im.client.ui;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author niushaohan
 * @date 2021/2/8 17
 */
public interface ConsoleCommand {
    void exec(Scanner scanner, Channel channel);
}
