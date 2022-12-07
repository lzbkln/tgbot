package org.dl.tgbot.telegramBot;

import org.dl.tgbot.dto.*;
import org.dl.tgbot.keyboards.InlineButton;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.List;

public class TelegramDataConverter {
    public Request convertToRequest(Update update) {
        Request request = new Request();
        if (update.hasMessage()) {
            Message message = update.getMessage();
            request.addComponent(new MetaData(message));
            request.addComponent(new TextComponent(message.getText()));
        } else if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            String callData = callbackQuery.getData();
            Message message = callbackQuery.getMessage();
            String callbackQueryId = callbackQuery.getId();

            request.addComponent(new MetaData(message.getChatId()));
            request.addComponent(new CallbackComponent(callData, callbackQueryId));
        }

        return request;
    }

    public SendMessage convertFromResponse(Response response) {
        Long who = response.getComponent(MetaData.class).getUserId();
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
