package org.dl.tgbot.keyboards;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import java.util.ArrayList;
import java.util.List;

public class KeyStory extends Keyboard {
    public static void createStoryKey(long userId) {
        // TODO: переписать так, чтобы в этом методе
        //  создавалась клавиатура на основе переписанного KeyboardComponent

        message.setChatId(userId);
        message.setText("Выберите историю");

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();


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
        message.setReplyMarkup(markup);
    }
}
