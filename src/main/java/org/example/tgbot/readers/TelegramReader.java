package org.example.tgbot.readers;

import org.example.tgbot.dto.MetaData;
import org.example.tgbot.dto.Request;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramReader {

    public Request read(Update update) {
        var msg = update.getMessage();
        MetaData metaData = new MetaData(msg);

        return new Request(msg.getText(), metaData);
    }
}
