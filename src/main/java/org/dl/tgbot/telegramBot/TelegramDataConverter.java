package org.dl.tgbot.telegramBot;

import org.dl.tgbot.dto.*;
import org.dl.tgbot.keyboards.InlineButton;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.List;

public class TelegramDataConverter {
    public Request convertToRequest(Update update) {
        Message msg = update.getMessage();

        Request request = new Request(new MetaData(msg));
        request.addComponent(new TextComponent(msg.getText()));

        return request;
    }

    public SendMessage convertFromResponse(Response response) {
        Long who = response.getComponent(MetaData.class).getUserId();
        //TODO: переделать
        InlineKeyboardMarkup markup = null;


        if (response.getComponent(KeyboardComponent.class) != null) {
            List<Button> buttonList = response.getComponent(KeyboardComponent.class).getButtons();
            markup = InlineButton.convertFromButton(buttonList);
        }
        return SendMessage.builder()
                .chatId(who.toString())
                .text(response.getComponent(TextComponent.class).getText())
                .replyMarkup(markup)
                .build();
    }
}
