package org.dl.tgbot.command;

import com.google.common.collect.ImmutableMap;
import org.dl.tgbot.service.CreateMessageService;

import static org.dl.tgbot.command.CommandName.HELP;
import static org.dl.tgbot.command.CommandName.START;

public class CommandContainer {

    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(CreateMessageService createMessageService) {

        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(createMessageService))
                .put(HELP.getCommandName(), new HelpCommand(createMessageService))
                .build();

        unknownCommand = new UnknownCommand(createMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }

}
