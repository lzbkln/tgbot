package org.dl.tgbot.dto;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class KeyboardComponent implements Component {
    // TODO: переписать эту компоненту так, чтобы не было привязки к телеграмму,
    //  возможно, переименовать

    // TODO: создать отдельный класс кнопки не привязанной к телеграмму с полями: name, callback
    // Это нужно, потому что у каждой кнопки может быть свой callback
    private final InlineKeyboardMarkup markup;

    public KeyboardComponent(InlineKeyboardMarkup markup) {
        this.markup = markup;
    }
    public InlineKeyboardMarkup getMarkup() {
        return markup;
    }
}
