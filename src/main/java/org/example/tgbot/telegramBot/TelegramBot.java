package org.example.tgbot.telegramBot;

import org.example.tgbot.Main;
import org.example.tgbot.dto.Request;
import org.example.tgbot.dto.Response;
import org.example.tgbot.handlers.SimpleHandler;
import org.example.tgbot.readers.TelegramReader;
import org.example.tgbot.writers.TelegramWriter;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {

    TelegramReader telegramReader;
    TelegramWriter telegramWriter;
    SimpleHandler simpleHandler;

    public TelegramBot() {
        telegramReader = new TelegramReader();
        telegramWriter = new TelegramWriter();
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
        Request request;
        Response response;
        var msg = update.getMessage();
        var user = msg.getFrom();
        var id = user.getId();
        if (!update.getMessage().hasText()) {
            System.out.println("It hasn't text");
            return;
        }
        request = telegramReader.read(update);
        response = simpleHandler.handleRequest(request);
        SendMessage sendMes = telegramWriter.createMessage(response, id);
        sendMessage(sendMes);
    }

    //пока только с текстовыми сообщениями
    public void sendMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
