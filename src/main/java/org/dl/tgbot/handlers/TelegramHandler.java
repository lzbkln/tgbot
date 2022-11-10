package org.dl.tgbot.handlers;

import org.dl.tgbot.dto.MetaData;
import org.dl.tgbot.dto.Request;
import org.dl.tgbot.dto.Response;
import org.dl.tgbot.dto.TextComponent;

public class TelegramHandler implements Handler {
    @Override
    public Response handleRequest(Request request) {
        String text;
        
        if (request.getComponent(TextComponent.class).getText() == null) {
            text = "Мы друг друга не поняли, отправьте текстовое сообщение";
        } else if (request.getComponent(TextComponent.class).getText().startsWith("/start")) {
            text = "Привет, я бот для девочек! Я помогу тебе пройти Клуб Романтики!";
        } else {
            text = request.getComponent(TextComponent.class).getText();
        }

        Response response = new Response();
        response.addComponent(new TextComponent(text));
        response.addComponent(request.getComponent(MetaData.class));

        return response;
    }
}
