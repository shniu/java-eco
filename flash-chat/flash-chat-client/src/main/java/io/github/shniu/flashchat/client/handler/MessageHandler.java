package io.github.shniu.flashchat.client.handler;

import io.github.shniu.flashchat.common.protocol.Command;

import java.util.concurrent.Future;

/**
 * @author niushaohan
 * @date 2020/12/25 15
 */
public interface MessageHandler {
    void onMessage(String message);

    // Future<String> login(Command loginCommand);
}
