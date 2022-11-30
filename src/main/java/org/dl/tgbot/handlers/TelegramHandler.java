package org.dl.tgbot.handlers;

import org.dl.tgbot.command.CommandContainer;
import org.dl.tgbot.command.CommandName;
import org.dl.tgbot.dto.Request;
import org.dl.tgbot.dto.Response;
import org.dl.tgbot.dto.TextComponent;
import org.dl.tgbot.service.CreateReportMessageService;
import org.dl.tgbot.service.CreateStoriesMessageService;

import static org.dl.tgbot.Constants.COMMAND_PREFIX;


public class TelegramHandler implements Handler {

    @Override
    public Response handleRequest(Request request) {
        String msg;
        msg = request.getComponent(TextComponent.class).getText();

        Response response;
        CommandContainer commandContainer = new CommandContainer(new CreateReportMessageService(), new CreateStoriesMessageService());
        if (msg == null) {
            response = commandContainer.retrieveCommand(CommandName.NO.getCommandName()).execute(request);
        }
        else if (msg.startsWith(COMMAND_PREFIX)) {
            String commandIdentifier = msg.split(" ")[0].toLowerCase();
            response = commandContainer.retrieveCommand(commandIdentifier).execute(request);
        } else {
            response = commandContainer.retrieveCommand(CommandName.NO.getCommandName()).execute(request);
        }

        return response;

    }
}