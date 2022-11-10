package org.example.tgbot;


import org.example.tgbot.telegramBot.TelegramBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws TelegramApiException {
        startTgBot();
    }

    public static String getTkFromProperty(String fileName) {
        Properties property = new Properties();
        try (FileInputStream fis =
                     new FileInputStream("src/main/resources/" + fileName)) {
            property.load(fis);
        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
            //бот должен упасть, если нет конфиг файла
            throw new RuntimeException();
        }

        return property.getProperty("token");
    }

    private static void startTgBot() throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        TelegramBot bot = new TelegramBot();
        botsApi.registerBot(bot);
    }
}
