package org.dl.tgbot.service;

import org.dl.tgbot.Main;
import org.dl.tgbot.dto.MetaData;
import org.dl.tgbot.dto.Request;
import org.dl.tgbot.dto.Response;
import org.dl.tgbot.dto.TextComponent;

import static org.dl.tgbot.Constants.PHRASES_FILENAME;

public class CreateReportMessageService implements CreateMessageService {

    @Override
    public Response createTextMessage(Request request, String ans) {

        String text;
        text = Main.getPhrase(PHRASES_FILENAME, ans, "ru", "RU");

        Response response = new Response(request.getComponent(MetaData.class));
        response.addComponent(new TextComponent(text));

        return response;
    }
}
