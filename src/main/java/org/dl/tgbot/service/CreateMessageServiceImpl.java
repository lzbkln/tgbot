package org.dl.tgbot.service;

import org.dl.tgbot.Main;
import org.dl.tgbot.dto.MetaData;
import org.dl.tgbot.dto.Request;
import org.dl.tgbot.dto.Response;
import org.dl.tgbot.dto.TextComponent;

public class CreateMessageServiceImpl implements CreateMessageService{

    @Override
    public Response createTextMessage(Request request, String ans) {
        // что делать с такими константами?
        String text;
        String fileName = "phrases";
        text = Main.getPhrase(fileName, ans, "ru", "RU");

        Response response = new Response();
        response.addComponent(new TextComponent(text));
        response.addComponent(request.getComponent(MetaData.class));

        return response;
    }
}
