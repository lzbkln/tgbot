package org.dl.tgbot.telegramBot;

import org.dl.tgbot.Main;
import org.dl.tgbot.dto.MetaData;
import org.dl.tgbot.dto.Request;
import org.dl.tgbot.dto.Response;
import org.dl.tgbot.handlers.TelegramHandler;
import org.dl.tgbot.keyboards.KeyStory;
import org.dl.tgbot.keyboards.MakeKeyboard;
import org.dl.tgbot.writers.Writer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
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
        if (update.hasMessage()) {
            Request request = converter.convertToRequest(update);
            Response response = telegramHandler.handleRequest(request);
            write(response);
        } else if (update.hasCallbackQuery()){
            //пока просто БОТ заменяет клавиатуру на текст
            String call_data = update.getCallbackQuery().getData();
            long message_id = update.getCallbackQuery().getMessage().getMessageId();
            long chat_id = update.getCallbackQuery().getMessage().getChatId();
            // пропишем Data для каждой кнопки при создании в keyStory
            // сделаем две кнопки, чтобы листать истории
            String callbackQueryId = update.getCallbackQuery().getId();
            System.out.println(call_data);
            System.out.println(message_id);
            System.out.println(chat_id);

            // ответ на колбэк, он может быть пустой, а может быть и с текстом
            AnswerCallbackQuery ansCallback = new AnswerCallbackQuery();
            ansCallback.setText("Это ответ");
            ansCallback.setCallbackQueryId(callbackQueryId);
            if (call_data.equals("First")) {
                String answer = "AnswerFirst";
                EditMessageText new_message = new EditMessageText();
                new_message.setChatId(chat_id);
                new_message.setMessageId((int) message_id);
                new_message.setText(answer);
                try {
                    execute(new_message);
                    // телеграм требует дополнительно отправлять ответ на коллбэк
                    execute(ansCallback);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void write(Response response) {
        SendMessage message = converter.convertFromResponse(response);
        if (message.getText().equals(Main.getFromProperty("phrases.properties", "message.start"))){
            MakeKeyboard.createKeyboard(MetaData.userId);
            try {
                execute(MakeKeyboard.message);
            } catch (TelegramApiException e){
                throw new RuntimeException(e);
            }
        } else if (message.getText().equals("Истории")){
            KeyStory.createStoryKey(MetaData.userId);
            try {
                execute(MakeKeyboard.message);
            } catch (TelegramApiException e){
                throw new RuntimeException(e);
            }
        } else {
            try {
                execute(message);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }
}