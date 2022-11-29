package org.dl.tgbot.telegramBot;

import org.dl.tgbot.dto.*;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

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
        // тут тоже привязка к телеграму
        InlineKeyboardMarkup markup = null;
        if (response.getComponent(KeyboardComponent.class) != null) {
            markup = response.getComponent(KeyboardComponent.class).getMarkup();
        }
        // любой из аргументов может быть null, кроме chatId
        return SendMessage.builder()
                .chatId(who.toString())
                .text(response.getComponent(TextComponent.class).getText())
                .replyMarkup(markup)
                .build();
    }
}
