package org.example.tgbot.handlers;

import org.example.tgbot.dto.Request;
import org.example.tgbot.dto.Response;

public class TelegramHandler implements Handler {
    @Override
    public Response handleRequest(Request request) {
        String text;

        if (request.getData() == null) {
            text = "Мы друг друга не поняли, отправьте текстовое сообщение";
        }
        else if (request.getData().startsWith("/start")) {
            text = "Привет, я бот для девочек! Я помогу тебе пройти Клуб Романтики!";
        }
        else {
            text = request.getData();
        }
        return new Response(text, request.getMetaData());
    }
}
