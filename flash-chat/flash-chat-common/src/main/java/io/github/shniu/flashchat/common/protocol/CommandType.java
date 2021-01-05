package io.github.shniu.flashchat.common.protocol;

/**
 * @author niushaohan
 * @date 2020/12/25 18
 */
public enum CommandType {
    LOGIN('0'),
    CHAT('5'),
    QUERY_FRIEND('A'),
    ADD_FRIEND('B'),
    ;

    private char type;

    CommandType(char type) {
        this.type = type;
    }

    public char getType() {
        return type;
    }

    public static CommandType typeOf(char type) {
        for (CommandType commandType : CommandType.values()) {
            if (commandType.getType() == type) {
                return commandType;
            }
        }

        return null;
    }
}
