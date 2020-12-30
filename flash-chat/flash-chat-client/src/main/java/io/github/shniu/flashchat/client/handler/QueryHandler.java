package io.github.shniu.flashchat.client.handler;

import io.github.shniu.flashchat.client.FlashChatClient;
import io.github.shniu.flashchat.common.Jsons;
import io.github.shniu.flashchat.common.protocol.Command;
import io.github.shniu.flashchat.common.protocol.CommandType;
import io.github.shniu.flashchat.common.protocol.Header;
import io.github.shniu.flashchat.common.protocol.Version;

/**
 * @author niushaohan
 * @date 2020/12/30 22
 */
public class QueryHandler implements MessageHandler {
    private FlashChatClient chatClient;

    public QueryHandler(FlashChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public void onMessage(String message) {
        Command command = new Command();
        command.setHeader(new Header(Version.PROTOCOL_VERSION, CommandType.QUERY_FRIEND.getType(), message.length()));
        command.setPayload(message.getBytes());

        String msg = Jsons.writeValueAsString(command);
        if (msg != null) {
            chatClient.send(msg);
        }
    }

    @Override
    public CommandType commandType() {
        return CommandType.QUERY_FRIEND;
    }
}
