package org.dl.tgbot.service;

import org.dl.tgbot.dto.MetaData;
import org.dl.tgbot.dto.Request;
import org.dl.tgbot.dto.Response;
import org.dl.tgbot.dto.TextComponent;

public class CreateStoriesMessageServiceImpl implements CreateMessageService{
    @Override
    public Response createTextMessage(Request request, String ans) {
        String text;

        text = "Story 1\nStory 2\nStory 3";
        Response response = new Response();
        response.addComponent(new TextComponent(text));
        response.addComponent(request.getComponent(MetaData.class));

        return response;
    }
}
