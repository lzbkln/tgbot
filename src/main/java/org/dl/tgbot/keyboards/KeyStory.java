package org.dl.tgbot.keyboards;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import java.util.ArrayList;
import java.util.List;

public class KeyStory extends Keyboard {
    public static void createStoryKey(long userId) {
        message.setChatId(userId);
        message.setText("Выберите историю");

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();


        String[] test = {"ис", "прпрпр", " fvgfrgv", "fejje", "bdssjdm", "ffjjfjf", "ffjfjfj", "fjfjfjf", "jfjffjkffk", "jjfjfkfkf", "ис", "прпрпр", " fvgfrgv", "fejje", "bdssjdm", "ffjjfjf", "ffjfjfj", "fjfjfjf", "jfjffjkffk", "jjfjfkfkf", "ис", "прпрпр", " fvgfrgv", "fejje", "bdssjdm", "ffjjfjf", "ffjfjfj", "fjfjfjf", "jfjffjkffk", "jjfjfkfkf", "ис", "прпрпр", " fvgfrgv", "fejje", "bdssjdm", "ffjjfjf", "ffjfjfj", "fjfjfjf", "jfjffjkffk", "jjfjfkfkf"};
        for (var i = 0; i < test.length; i++) {
            List<InlineKeyboardButton> rows = new ArrayList<>();
            rows.add(InlineKeyboardButton
                    .builder()
                    .text(test[i])
                    .callbackData(test[i])
                    .build());
            buttons.add(rows);
        }
        markup.setKeyboard(buttons);
        message.setReplyMarkup(markup);
    }
}
