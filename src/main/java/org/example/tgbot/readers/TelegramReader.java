package org.example.tgbot.readers;

import org.example.tgbot.dto.Request;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramReader {

    public Request read(Update update) {
        return new Request(update.getMessage().getText());
    }
}
