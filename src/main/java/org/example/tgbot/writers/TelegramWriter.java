package org.example.tgbot.writers;

import org.example.tgbot.dto.Response;
import org.telegram.telegrambots.meta.api.methods.CopyMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
public class TelegramWriter {
    //отдельный дата класс, хранит данные пользователя, как поле реквеста
    public SendMessage createMessage(Response response, Long who) {
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString())
                .text(response.data()).build();
        return sm;
    }
}
//тг сендер (фасад), разные сендеры для разных сообщений
//можно реализовать writer ботом и собственно сделать норм метод write для бота
