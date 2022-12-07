package org.dl.tgbot;

import org.dl.tgbot.telegramBot.TelegramBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main {
    public static void main(String[] args) throws TelegramApiException {
        startTgBot();
    }

    public static String getPhrase(String fileName, String key, String language, String country) {
        Locale locale = new Locale(language, country);
        ResourceBundle rb = ResourceBundle.getBundle(fileName, locale);

        return rb.getString(key);
    }

    public static String getFromProperty(String fileName, String key) {
        Loader loader = new Loader();
        return loader.getProperty(fileName, key);
    }

    private static void startTgBot() throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        TelegramBot bot = new TelegramBot(Main.getFromProperty("config.properties", "token"), "RomanceClubGuides");
        botsApi.registerBot(bot);
    }
}