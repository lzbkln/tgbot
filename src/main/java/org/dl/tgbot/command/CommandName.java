package org.dl.tgbot.command;

public enum CommandName {
    START("/start"),
    HELP("/help"),
    STORIES("/stories"),
    NO("");
    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
