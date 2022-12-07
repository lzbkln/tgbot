package org.dl.tgbot.telegramBot;

import org.dl.tgbot.command.CommandName;
import org.dl.tgbot.dto.CallbackComponent;
import org.dl.tgbot.dto.Request;
import org.dl.tgbot.dto.Response;
import org.dl.tgbot.handlers.TelegramHandler;
import org.dl.tgbot.writers.Writer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;


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
        initCommandMenu();
    }

    private void initCommandMenu() {
        List<BotCommand> commandList = new ArrayList<>();
        for (CommandName commandName : CommandName.values()) {
            if (commandName != CommandName.NO)
                commandList.add(new BotCommand(commandName.getCommandName(), commandName.getCommandDescription()));
        }
        SetMyCommands commands = new SetMyCommands();
        commands.setCommands(commandList);
        try {
            this.execute(commands);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
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
        if (update.hasMessage() || update.hasCallbackQuery()) {
            System.out.println(update);
            Request request = converter.convertToRequest(update);
            Response response = telegramHandler.handleRequest(request);
            write(response);
        }
        /*else if (update.hasCallbackQuery()) {
            // TODO: перенести обработку в TelegramHandler, проверять там на CallBack и обрабатывать
            String call_data = update.getCallbackQuery().getData();
            long message_id = update.getCallbackQuery().getMessage().getMessageId();
            long chat_id = update.getCallbackQuery().getMessage().getChatId();
            String callbackQueryId = update.getCallbackQuery().getId();

            AnswerCallbackQuery ansCallback = new AnswerCallbackQuery();
            ansCallback.setText("Это ответ");
            ansCallback.setCallbackQueryId(callbackQueryId);

            String answer = "Answer for " + call_data;
            EditMessageText new_message = new EditMessageText();
            new_message.setChatId(chat_id);
            new_message.setMessageId((int) message_id);
            new_message.setText(answer);
            try {
                execute(new_message);
                execute(ansCallback);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        } */

    }

    public void write(Response response) {
        SendMessage message = converter.convertFromResponse(response);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

        if (response.getComponent(CallbackComponent.class) != null) {
            AnswerCallbackQuery ansCallback = new AnswerCallbackQuery();
            ansCallback.setText("Это ответ");
            ansCallback.setCallbackQueryId(response.getComponent(CallbackComponent.class).getCallbackQueryId());
            try {
                execute(ansCallback);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }
}