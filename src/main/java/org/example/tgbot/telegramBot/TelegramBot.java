package org.example.tgbot.telegramBot;

import org.example.tgbot.Main;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.CopyMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "RomanceClubGuides";
    }

    @Override
    public String getBotToken() {
        // вернуть токен, только это сделать нужно через файл конфигурации,
        // чтобы на гитхаб не заливать наш токен
        return Main.getTkFromProperty("config.properties");
    }

    @Override
    public void onUpdateReceived(Update update) {
        // тестовый вывод в консоль
        System.out.println(update);
        var msg = update.getMessage();
        var user = msg.getFrom();
        var userName = user.getUserName();
        var language = user.getLanguageCode();

        System.out.println(user.getFirstName() + " wrote " + msg.getText());
        System.out.print(userName + ' ' + language);

        //эхо ответ
        var id = user.getId();
        copyMessage(id, msg.getMessageId());
    }

    public void copyMessage(Long who, Integer msgId){
        CopyMessage cm = CopyMessage.builder()
                .fromChatId(who.toString())  //We copy from the user
                .chatId(who.toString())      //And send it back to him
                .messageId(msgId)            //Specifying what message
                .build();
        try {
            execute(cm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendText(Long who, String what){
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString()) //Who are we sending a message to
                .text(what).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }
}
