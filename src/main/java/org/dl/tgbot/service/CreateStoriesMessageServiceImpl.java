package org.dl.tgbot.service;

import org.dl.tgbot.dto.*;
import org.dl.tgbot.parse.Story;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CreateStoriesMessageServiceImpl implements CreateMessageService{
    @Override
    public Response createTextMessage(Request request, String ans) {
        // todo: привязываемся к телеграму

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        // todo: вставить логику сюда возвращать тут названия историй через какой-то метод

        Story story = new Story();
        ArrayList<String> test;
        try {
            test = story.printTitles();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

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
