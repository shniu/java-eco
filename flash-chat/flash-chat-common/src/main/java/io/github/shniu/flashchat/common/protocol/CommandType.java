package io.github.shniu.flashchat.common.protocol;

/**
 * @author niushaohan
 * @date 2020/12/25 18
 */
public enum CommandType {
    LOGIN('0');

    private char type;

    CommandType(char type) {
        this.type = type;
    }

    public char getType() {
        return type;
    }
}
