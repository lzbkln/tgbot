package org.example.tgbot.telegramBot;

import org.example.tgbot.Main;
import org.example.tgbot.dto.Request;
import org.example.tgbot.dto.Response;
import org.example.tgbot.handlers.TelegramHandler;
import org.example.tgbot.readers.TelegramReader;
import org.example.tgbot.writers.TelegramWriter;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.CopyMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {
    //маппер
    //компонент
    TelegramReader telegramReader;
    TelegramWriter telegramWriter;
    TelegramHandler telegramHandler;

    public TelegramBot() {
        telegramReader = new TelegramReader();
        telegramWriter = new TelegramWriter();
        telegramHandler = new TelegramHandler();
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
            // copyMessage(id, msg.getMessageId());
            return;
        }
        request = telegramReader.read(update);
        response = telegramHandler.handleRequest(request);
        //System.out.println(update);

        //System.out.println((user.getId()));

        SendMessage sendMes = telegramWriter.createMessage(response, id);
        sendMessage(sendMes);
        //эхо ответ
        //copyMessage(id, msg.getMessageId());
    }

    public void copyMessage(Long who, Integer msgId) {
        CopyMessage cm = CopyMessage.builder()
                .fromChatId(who.toString())
                .chatId(who.toString())
                .messageId(msgId)
                .build();
        try {
            execute(cm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendText(Long who, String what) {
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString())
                .text(what).build();
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
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
