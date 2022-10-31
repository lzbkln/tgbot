package org.example.tgbot.writers;

import org.example.tgbot.dto.Response;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
public class TelegramWriter {
    public SendMessage createMessage(Response response, Long who) {
        return SendMessage.builder()
                .chatId(who.toString())
                .text(response.data()).build();
    }
}


