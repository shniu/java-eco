package io.github.shniu.flashchat.server;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import io.github.shniu.flashchat.common.Id;
import io.github.shniu.flashchat.common.Jsons;
import io.github.shniu.flashchat.common.net.AbstractEndpoint;
import io.github.shniu.flashchat.common.protocol.Command;
import io.github.shniu.flashchat.common.protocol.CommandType;
import io.github.shniu.flashchat.common.protocol.Header;
import io.github.shniu.flashchat.common.protocol.Version;
import io.github.shniu.flashchat.common.protocol.command.AddFriendBean;
import io.github.shniu.flashchat.common.protocol.command.ChatBean;
import io.github.shniu.flashchat.common.protocol.command.LoginBean;
import io.github.shniu.flashchat.common.protocol.command.LoginResponse;
import io.github.shniu.flashchat.common.protocol.command.QueryBean;
import io.github.shniu.flashchat.common.protocol.command.QueryFriendResponse;
import io.github.shniu.flashchat.server.domain.User;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.AbstractSelectableChannel;
import java.nio.channels.spi.AbstractSelector;
import java.nio.channels.spi.SelectorProvider;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author niushaohan
 * @date 2020/12/22 09
 */
public class FlashChatServer extends AbstractEndpoint {
    // userId -> User
    private Map<String, User> online;

    // userId -> SelectionKey
    private Map<String, SocketChannel> channels;

    // userId -> friend sets
    private Map<String, Set<User>> friends;

    public FlashChatServer(String endpointId, int port) throws IOException {
        super(endpointId, port);

        online = Maps.newHashMap();

        friends = Maps.newHashMap();
        Set<User> friendSets = friends.getOrDefault("10000", Sets.newHashSet());
        friendSets.add(new User("999", "test"));
        friends.put("10000", friendSets);

        channels = Maps.newHashMap();
    }

    @Override
    protected Selector selector() throws IOException {
        AbstractSelector selector = SelectorProvider.provider().openSelector();
        channel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("Channel register on the selector.");
        return selector;
    }

    @Override
    protected AbstractSelectableChannel channel(InetSocketAddress address) throws IOException {
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false);
        channel.bind(address);

        System.out.println("Flash chat server listen on " + address.getPort());
        return channel;
    }

    @Override
    protected void handleInboundData(SelectionKey sender, byte[] bytes) throws IOException {
        System.out.println("Inbound data " + new String(bytes));

        try {
            Command command = Jsons.readValue(bytes, Command.class);

            // 登录操作
            if (CommandType.LOGIN.getType() == command.getHeader().getType()) {
                System.out.println("Login command");

                LoginBean loginBean = Jsons.readValue(command.getPayload(), LoginBean.class);
                // 先不做用户名密码的判断，进来就默认登录成功

                String userId = Id.id();
                System.out.println("Login user is " + loginBean.getUsername() + ", return id is " + userId);

                Command loginSucceed = new Command();
                LoginResponse loginResponse = new LoginResponse(userId);
                byte[] respBytes = Jsons.writeValueAsBytes(loginResponse);
                Header header = new Header(Version.PROTOCOL_VERSION, CommandType.LOGIN.getType(), respBytes.length);
                loginSucceed.setHeader(header);
                loginSucceed.setPayload(respBytes);

                sender.attach(ByteBuffer.wrap(Jsons.writeValueAsBytes(loginSucceed)));
                handleWrite(sender);

                online.put(userId, new User(userId, loginBean.getUsername()));
                channels.put(userId, (SocketChannel) sender.channel());
                return;
            } else if (CommandType.QUERY_FRIEND.getType() == command.getHeader().getType()) {
                // 查询好友
                System.out.println("Query friend command");
                QueryBean queryBean = Jsons.readValue(command.getPayload(), QueryBean.class);

                Set<User> friendSets = friends.get(queryBean.getUserId());
                QueryFriendResponse friendResponse = new QueryFriendResponse();
                friendSets.forEach(user ->
                        friendResponse.addFriend(Joiner.on(",")
                                .join(user.getUserId(), user.getUsername())));

                byte[] payload = Jsons.writeValueAsBytes(friendResponse);
                Command friendCommand = new Command();
                Header header = new Header(Version.PROTOCOL_VERSION, CommandType.QUERY_FRIEND.getType(), payload.length);
                friendCommand.setHeader(header);
                friendCommand.setPayload(payload);

                sender.attach(ByteBuffer.wrap(Jsons.writeValueAsBytes(friendCommand)));
                handleWrite(sender);
                return;
            } else if (CommandType.ADD_FRIEND.getType() == command.getHeader().getType()) {
                // 添加好友
                System.out.println("Add friend command");
                AddFriendBean addFriendBean = Jsons.readValue(command.getPayload(), AddFriendBean.class);

                // 互为好友
                Set<User> friendSets = this.friends.getOrDefault(addFriendBean.getOwnId(), Sets.newHashSet());
                User frd = online.get(addFriendBean.getQuoteUserId());
                friendSets.add(frd);
                this.friends.putIfAbsent(addFriendBean.getOwnId(), friendSets);

                friendSets = this.friends.getOrDefault(addFriendBean.getQuoteUserId(), Sets.newHashSet());
                frd = online.get(addFriendBean.getOwnId());
                friendSets.add(frd);
                this.friends.putIfAbsent(addFriendBean.getQuoteUserId(), friendSets);

                return;
            } else if (CommandType.CHAT.getType() == command.getHeader().getType()) {
                // 发送聊天消息
                System.out.println("Chat command");
                ChatBean chatBean = Jsons.readValue(command.getPayload(), ChatBean.class);

                SocketChannel channel = channels.get(chatBean.getTo());
                if (Objects.isNull(channel)) {
                    // 离线处理
                    System.out.println("Offline: " + chatBean);
                    return;
                }

                SelectionKey toKey = channel.keyFor(selector);

                byte[] payload = Jsons.writeValueAsBytes(chatBean);
                Command chatCommand = new Command();
                chatCommand.setHeader(new Header(Version.PROTOCOL_VERSION, CommandType.CHAT.getType(), payload.length));
                chatCommand.setPayload(payload);
                toKey.attach(ByteBuffer.wrap(Jsons.writeValueAsBytes(chatCommand)));
                handleWrite(toKey);
                return;
            }
        } catch (Exception e) {
            // swallow
            e.printStackTrace();
            return;
        }

        for (SelectionKey registeredKey : sender.selector().keys()) {
            if (registeredKey.channel() instanceof ServerSocketChannel) {
                continue;
            }

            if (registeredKey.equals(sender)) {
                continue;
            }

            SocketChannel socketChannel = (SocketChannel) registeredKey.channel();

            // Send to all other clients that have registered.
            registeredKey.attach(ByteBuffer.wrap(bytes));
            handleWrite(registeredKey);
            System.out.println(socketChannel.socket().getRemoteSocketAddress() + " write finished.");
        }
    }

    @Override
    protected void handleWrite(SelectionKey key) throws IOException {
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        SocketChannel channel = (SocketChannel) key.channel();
        channel.write(buffer);
        key.interestOps(SelectionKey.OP_READ);
    }
}
