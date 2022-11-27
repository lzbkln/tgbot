package org.dl.tgbot.service;

import org.dl.tgbot.dto.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class CreateStoriesMessageServiceImpl implements CreateMessageService{
    @Override
    public Response createTextMessage(Request request, String ans) {
        // todo: привязываемся к телеграму

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        // todo: вставить Лизину логику сюда

        String[] test = {"First", "Second", "Third", "Fourth", "Fifth", "Sixth"};
        for (String s : test) {
            List<InlineKeyboardButton> rows = new ArrayList<>();
            rows.add(InlineKeyboardButton
                    .builder()
                    .text(s)
                    .callbackData(s)
                    .build());
            buttons.add(rows);
        }
        markup.setKeyboard(buttons);

        String text;
        text = "Выберите историю:";
        Response response = new Response();
        response.addComponent(new TextComponent(text));
        response.addComponent(request.getComponent(MetaData.class));
        response.addComponent(new KeyboardComponent(markup));

        return response;
    }
}
