package org.dl.tgbot.telegramBot;

import org.dl.tgbot.dto.Request;
import org.dl.tgbot.dto.Response;
import org.dl.tgbot.handlers.TelegramHandler;
import org.dl.tgbot.writers.Writer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot implements Writer {
    private final String token;
    public final String botName;
    TelegramHandler telegramHandler;
    TelegramDataConverter converter;

    public TelegramBot(String token, String botName) {
        this.token = token;
        this.botName = botName;

        converter = new TelegramDataConverter();
        telegramHandler = new TelegramHandler();
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Request request = converter.convertToRequest(update);
        Response response = telegramHandler.handleRequest(request);
        write(response);
    }

    public void write(Response response) {
        SendMessage message = converter.convertFromResponse(response);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}