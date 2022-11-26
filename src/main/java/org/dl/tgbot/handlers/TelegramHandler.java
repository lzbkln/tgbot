package org.dl.tgbot.handlers;

import org.dl.tgbot.command.CommandContainer;
import org.dl.tgbot.dto.Request;
import org.dl.tgbot.dto.Response;
import org.dl.tgbot.dto.TextComponent;
import org.dl.tgbot.service.CreateMessageServiceImpl;


public class TelegramHandler implements Handler {

    @Override
    public Response handleRequest(Request request) {
        String COMMAND_PREFIX = "/";
        String msg;
        msg = request.getComponent(TextComponent.class).getText();
        /*
        String text;

        String fileName = "phrases";
        // переписать работу с командами на паттерны Команда
        if (msg == null) {
            text = Main.getPhrase(fileName, report.UNKNOWN_INPUT.message, "ru", "RU");
        } else if (msg.startsWith(COMMAND_PREFIX)) {
            if (msg.startsWith("/start")) {
                text = Main.getPhrase(fileName, report.START.message, "ru", "RU");
            } else if (msg.startsWith("/help")) {
                text = Main.getPhrase(fileName, report.HELP.message, "ru", "RU");
            } else if (msg.startsWith("/stories")) {
                text = "Тут будут истории";
            }
            else {
                text = Main.getPhrase(fileName, report.UNKNOWN_COMMAND.message, "ru", "RU");
            }
        } else if (msg.startsWith("Помощь")) {
            text = Main.getPhrase(fileName, report.HELP.message, "ru", "RU");
        }
        else {
            text = msg;
        }

        Response response = new Response();
        response.addComponent(new TextComponent(text));
        response.addComponent(request.getComponent(MetaData.class));

        */
        Response response = new Response();
        CommandContainer commandContainer = new CommandContainer(new CreateMessageServiceImpl());
        if (msg == null) {
        }
        else if (msg.startsWith(COMMAND_PREFIX)) {
            String commandIdentifier = msg.split(" ")[0].toLowerCase();
            response = commandContainer.retrieveCommand(commandIdentifier).execute(request);
        } else {

        }

        return response;

    }
}