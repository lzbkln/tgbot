package org.dl.tgbot.handlers;

import org.dl.tgbot.command.CommandContainer;
import org.dl.tgbot.command.CommandName;
import org.dl.tgbot.dto.*;
import org.dl.tgbot.service.CreateReportMessageService;
import org.dl.tgbot.service.CreateStoriesMessageService;

import static org.dl.tgbot.Constants.COMMAND_PREFIX;


public class TelegramHandler implements Handler {

    @Override
    public Response handleRequest(Request request) {
        TextComponent msg;
        msg = request.getComponent(TextComponent.class);

        Response response;
        CommandContainer commandContainer = new CommandContainer(new CreateReportMessageService(), new CreateStoriesMessageService());

        if (msg != null) {
            if (msg.getText().startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = msg.getText().split(" ")[0].toLowerCase();
                response = commandContainer.retrieveCommand(commandIdentifier).execute(request);
            } else {
                response = commandContainer.retrieveCommand(CommandName.NO.getCommandName()).execute(request);
            }
        } else {
            response = new Response();
            response.addComponent(new TextComponent("Button"));
            response.addComponent(new MetaData(request.getComponent(MetaData.class)));
            response.addComponent(request.getComponent(CallbackComponent.class));
        }

        return response;

    }
}