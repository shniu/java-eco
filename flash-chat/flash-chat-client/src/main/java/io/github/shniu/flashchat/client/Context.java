package io.github.shniu.flashchat.client;

import com.google.common.collect.Sets;
import io.github.shniu.flashchat.client.domain.User;

import java.util.Objects;
import java.util.Set;

/**
 * @author niushaohan
 * @date 2020/12/30 17
 */
public class Context {
    private volatile User user;
    // private ConcurrentMap<String, User> friends;
    private Set<User> friends;
    private volatile boolean fetched;

    public Context() {
        friends = Sets.newConcurrentHashSet();
        fetched = false;
    }

    public void addFriend(User frd) {
        friends.add(frd);

        setFetched();
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFetched() {
        fetched = true;
    }

    public boolean isFetched() {
        return fetched;
    }

    public User findFriend(String username) {
        for (User friend : friends) {
            if (friend.getUsername().equals(username)) {
                return friend;
            }
        }

        return null;
    }

    public synchronized void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public boolean isLogin() {
        return Objects.nonNull(user);
    }
}
