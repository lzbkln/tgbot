package org.dl.tgbot.dto;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class KeyboardComponent implements Component {
    private final InlineKeyboardMarkup markup;

    public KeyboardComponent(InlineKeyboardMarkup markup) {
        this.markup = markup;
    }
    public InlineKeyboardMarkup getMarkup() {
        return markup;
    }
}
