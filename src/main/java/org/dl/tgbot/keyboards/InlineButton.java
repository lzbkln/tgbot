package org.dl.tgbot.keyboards;

import org.dl.tgbot.dto.Button;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class InlineButton {
    /**
     * @param buttons Список кнопок
     * @return Список телеграммовских инлайн-кнопок, каждая на новой строке
     */
    static public InlineKeyboardMarkup convertFromButton(List<Button> buttons) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> InlineButtons = new ArrayList<>();

        for (Button button : buttons) {
            List<InlineKeyboardButton> rows = new ArrayList<>();
            rows.add(InlineKeyboardButton
                    .builder()
                    .text(button.getName())
                    .callbackData(button.getCallbackData())
                    .build());
            InlineButtons.add(rows);
        }
        markup.setKeyboard(InlineButtons);

        return markup;
    }
}
