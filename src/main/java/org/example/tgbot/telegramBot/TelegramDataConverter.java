package org.example.tgbot.telegramBot;
import org.example.tgbot.dto.MetaData;
import org.example.tgbot.dto.Request;
import org.example.tgbot.dto.Response;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramDataConverter {
    public Request read(Update update) {
        var msg = update.getMessage();
        MetaData metaData = new MetaData(msg);

        return new Request(msg.getText(), metaData);
    }
    public SendMessage createMessage(Response response) {
        Long who = response.metaData().getFromId();

        return SendMessage.builder()
                .chatId(who.toString())
                .text(response.data()).build();
    }
}
