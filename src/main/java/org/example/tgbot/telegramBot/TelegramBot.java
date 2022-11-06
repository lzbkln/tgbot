package org.example.tgbot.telegramBot;

import org.example.tgbot.Main;
import org.example.tgbot.dto.Request;
import org.example.tgbot.dto.Response;
import org.example.tgbot.handlers.SimpleHandler;
import org.example.tgbot.handlers.TelegramHandler;
import org.example.tgbot.writers.Writer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot implements Writer {
    SimpleHandler simpleHandler;
    TelegramDataConverter converter;

    public TelegramBot() {
        converter = new TelegramDataConverter();
        simpleHandler = new SimpleHandler();
    }

    public static final String botName = "RomanceClubGuides";

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return Main.getTkFromProperty("config.properties");
    }

    @Override
    public void onUpdateReceived(Update update) {
        Request request = converter.read(update);
        Response response = simpleHandler.handleRequest(request);
        write(response);
    }

    //пока только с текстовыми сообщениями
    public void write(Response response) {
        SendMessage message = converter.createMessage(response);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
