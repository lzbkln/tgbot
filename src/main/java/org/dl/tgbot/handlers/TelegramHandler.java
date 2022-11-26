package org.dl.tgbot.handlers;

import org.dl.tgbot.Main;
import org.dl.tgbot.dto.MetaData;
import org.dl.tgbot.dto.Request;
import org.dl.tgbot.dto.Response;
import org.dl.tgbot.dto.TextComponent;


public class TelegramHandler implements Handler {

    @Override
    public Response handleRequest(Request request) {
        String COMMAND_PREFIX = "/";

        String text;
        String msg;
        msg = request.getComponent(TextComponent.class).getText();
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

        return response;
    }
}