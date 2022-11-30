package org.dl.tgbot.keyboards;
import org.dl.tgbot.Main;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

import static org.dl.tgbot.Constants.PHRASES_FILENAME;

// пока не используй этот класс
@Deprecated
public class MakeKeyboard extends Keyboard{
    public static void createKeyboard(long userId) {

        message.setChatId(userId);
        message.setText(Main.getPhrase(PHRASES_FILENAME, "message.start", "ru", "RU"));
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
        row.add("Истории");
        row.add("Помощь");
        keyboard.add(row);

        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);
    }
}
