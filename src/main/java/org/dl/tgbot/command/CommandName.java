package org.dl.tgbot.command;

public enum CommandName {
    START("/start", "Запустить бота"),
    HELP("/help", "Вывести помощь"),
    STORIES("/stories", "Вывести список историй"),
    NO("");
    private final String commandName;
    private final String commandDescription;
    CommandName(String commandName) {
        this(commandName, null);
    }
    CommandName(String commandName, String commandDescription) {
        this.commandName = commandName;
        this.commandDescription = commandDescription;
    }
    public String getCommandName() {
        return commandName;
    }
    public String getCommandDescription() {
        return commandDescription;
    }

}
