package org.example.tgbot.telegramBot;

import org.example.tgbot.dto.MetaData;
import org.example.tgbot.dto.Request;
import org.example.tgbot.dto.Response;
import org.example.tgbot.dto.TextComponent;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramDataConverter {
    public Request read(Update update) {
        Message msg = update.getMessage();

        Request request = new Request();
        request.addComponent(new TextComponent(msg.getText()));
        request.addComponent(new MetaData(msg));

        return request;
    }

    public SendMessage createMessage(Response response) {
        Long who = response.getComponent(MetaData.class).getUserId();

        return SendMessage.builder()
                .chatId(who.toString())
                .text(response.getComponent(TextComponent.class).getText())
                .build();
    }
}
