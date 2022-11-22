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

        if (msg == null) {
            text = Main.getFromProperty("phrases.properties", "text1");
        } else if (msg.startsWith("/start")) {
            text = Main.getFromProperty("phrases.properties", "text2");
        } else if (msg.startsWith("Помощь")) {
            text = Main.getFromProperty("phrases.properties", "text3");
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