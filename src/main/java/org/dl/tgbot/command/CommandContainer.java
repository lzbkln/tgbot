package org.dl.tgbot.command;

import com.google.common.collect.ImmutableMap;
import org.dl.tgbot.service.CreateMessageService;

import static org.dl.tgbot.command.CommandName.*;


public class CommandContainer {

    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;


    /**
     * Что делать если хочется и другие Service?
     * Плохо, что передаем несколько сервисов
     * Может, изменить логику сервиса? Тогда не придется передавать несколько сервисов
     * Изменить так: определять выводимую строку внутри сервиса?
     * @param createMessageService Сервис, осуществляющий логику
     */
    public CommandContainer(CreateMessageService createMessageService, CreateMessageService createMessageService1) {

        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(createMessageService))
                .put(HELP.getCommandName(), new HelpCommand(createMessageService))
                .put(NO.getCommandName(), new NoCommand(createMessageService))
                .put(STORIES.getCommandName(), new StoriesCommand(createMessageService1))
                .build();

        unknownCommand = new UnknownCommand(createMessageService);
    }

    /**
     *
     * @param commandIdentifier Команда в виде строки
     * @return Команду, соответствующую команде, либо unknownCommand
     */
    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }

}
