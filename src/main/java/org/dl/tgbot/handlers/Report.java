package org.dl.tgbot.handlers;

public enum Report {
    UNKNOWN_INPUT(0, "message.unknownInput"),
    START(1, "message.start"),
    HELP(2, "message.help"),
    UNKNOWN_COMMAND(3, "message.unknownCommand");
    public final int code;
    public final String message;
    Report(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
