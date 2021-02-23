package org.digcredit.project.im.client.ui;

import com.google.common.collect.Maps;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.Scanner;

/**
 * @author niushaohan
 * @date 2021/2/8 17
 */
public class ConsoleCommandManager implements ConsoleCommand {
    private Map<String, ConsoleCommand> commands = Maps.newHashMap();

    public ConsoleCommandManager() {
        commands.put("createGroup", new GroupConsoleCommand());
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {

    }
}
