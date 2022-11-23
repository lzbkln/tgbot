package org.dl.tgbot.handlers;

import org.dl.tgbot.Main;
import org.dl.tgbot.dto.MetaData;
import org.dl.tgbot.dto.Request;
import org.dl.tgbot.dto.Response;
import org.dl.tgbot.dto.TextComponent;


public class TelegramHandler implements Handler {
    @Override
    public Response handleRequest(Request request) {
        String text;
        String msg;
        msg = request.getComponent(TextComponent.class).getText();
        String fileName = "phrases.properties";
        if (msg == null) {
            text = Main.getFromProperty(fileName, "message.unknownInput");
        } else if (msg.startsWith("/")) {
            if (msg.startsWith("/start")) {
                text = Main.getFromProperty(fileName, "message.start");
            }
            else {
                text = Main.getFromProperty(fileName, "message.unknownCommand");
            }
        } else if (msg.startsWith("Помощь")) {
            text = Main.getFromProperty(fileName, "message.help");
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