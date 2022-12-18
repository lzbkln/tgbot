package org.dl.tgbot.handlers;

import org.dl.tgbot.command.CommandContainer;
import org.dl.tgbot.command.CommandName;
import org.dl.tgbot.dto.*;
import org.dl.tgbot.service.*;

import static org.dl.tgbot.Constants.COMMAND_PREFIX;


public class TelegramHandler implements Handler {

    @Override
    public Response handleRequest(Request request) {
        TextComponent msg;
        msg = request.getComponent(TextComponent.class);
        CallbackComponent callbackComponent = request.getComponent(CallbackComponent.class);
        Response response;
        CommandContainer commandContainer = new CommandContainer(new CreateReportMessageService(), new CreateStoriesMessageService());
        //разные хендлеры для команд и инлайн-кнопок
        if (msg != null) {
            if (msg.getText().startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = msg.getText().split(" ")[0].toLowerCase();
                response = commandContainer.retrieveCommand(commandIdentifier).execute(request);
            } else {
                response = commandContainer.retrieveCommand(CommandName.NO.getCommandName()).execute(request);
            }
        } else if (callbackComponent!= null) {
            CreateMessageService createMessageService = new CheckButton();
            response = createMessageService.createTextMessage(request, "");
        } else {
            response = new Response();
            response.addComponent(new TextComponent("Это вообще не текст"));
            response.addComponent(new MetaData(request.getComponent(MetaData.class)));
        }

        return response;

    }
}