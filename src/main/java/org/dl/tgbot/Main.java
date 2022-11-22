package org.dl.tgbot;
import org.dl.tgbot.telegramBot.TelegramBot;
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

    public static String getFromProperty(String fileName, String key) {
        Properties property = new Properties();
        try (FileInputStream fis =
                     new FileInputStream("src/main/resources/" + fileName)) {
            property.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Конфигурационный файл отсутствует!", e);
        }

        return property.getProperty(key);
    }

    private static void startTgBot() throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        TelegramBot bot = new TelegramBot(Main.getFromProperty("config.properties", "token"), "RomanceClubGuides");
        botsApi.registerBot(bot);
    }
}