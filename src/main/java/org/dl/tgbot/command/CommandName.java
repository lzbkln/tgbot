package org.dl.tgbot.command;

public enum CommandName {
    // может добавить второй параметр - описание команды из файла phases?
    START("/start"),
    HELP("/help"),
    // Ещё хочется добавить второе название для команды на русском языке
    // Это нужно, чтобы кнопки нормально выглядели
    STORIES("/stories"),
    // Возникают трудности из-за того, что это пустая строка
    NO("");
    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
