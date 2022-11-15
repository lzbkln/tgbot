package org.dl.tgbot.telegramBot;

import org.dl.tgbot.dto.MetaData;
import org.dl.tgbot.dto.Request;
import org.dl.tgbot.dto.Response;
import org.dl.tgbot.dto.TextComponent;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramDataConverter {
    public Request convertToRequest(Update update) {
        Message msg = update.getMessage();

        Request request = new Request();
        request.addComponent(new TextComponent(msg.getText()));
        request.addComponent(new MetaData(msg));

        return request;
    }

    public SendMessage convertFromResponse(Response response) {
        Long who = response.getComponent(MetaData.class).getUserId();

        return SendMessage.builder()
                .chatId(who.toString())
                .text(response.getComponent(TextComponent.class).getText())
                .build();
    }
}
