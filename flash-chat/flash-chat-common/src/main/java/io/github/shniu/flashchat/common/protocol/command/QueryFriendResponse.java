package io.github.shniu.flashchat.common.protocol.command;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author niushaohan
 * @date 2020/12/30 19
 */
public class QueryFriendResponse {
    private List<String> friends;

    public QueryFriendResponse() {
        this.friends = Lists.newArrayList();
    }

    public void addFriend(String friend) {
        friends.add(friend);
    }

    public List<String> getFriends() {
        return friends;
    }
}
