package org.dl.tgbot.command;

import com.google.common.collect.ImmutableMap;
import org.dl.tgbot.service.CreateMessageService;
import org.dl.tgbot.service.CreateStoriesMessageService;

import java.util.Map;

import static org.dl.tgbot.command.CommandName.*;


public class CommandContainer {
    private final Map<String, Command> commandMap;
    private final Command unknownCommand;


    /**
     * @param createReportMessageService  Сервис, осуществляющий логику отправки заготовленных фраз
     * @param createStoriesMessageService Сервис, осуществляющий логику создания сообщения с историями
     */
    public CommandContainer(CreateMessageService createReportMessageService, CreateStoriesMessageService createStoriesMessageService) {
        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(createReportMessageService))
                .put(HELP.getCommandName(), new HelpCommand(createReportMessageService))
                .put(NO.getCommandName(), new NoCommand(createReportMessageService))
                .put(STORIES.getCommandName(), new StoriesCommand(createStoriesMessageService))
                .build();

        unknownCommand = new UnknownCommand(createReportMessageService);
    }

    /**
     * @param commandIdentifier Команда в виде строки
     * @return Команду, соответствующую команде, либо unknownCommand
     */
    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }

}
