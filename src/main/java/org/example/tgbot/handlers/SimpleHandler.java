package org.example.tgbot.handlers;

import org.example.tgbot.dto.MetaData;
import org.example.tgbot.dto.Request;
import org.example.tgbot.dto.Response;
import org.example.tgbot.dto.TextComponent;

public class SimpleHandler implements Handler {
    public Response handleRequest(Request request) {
        Response response = new Response();

        response.addComponent(request.getComponent(TextComponent.class));
        response.addComponent(request.getComponent(MetaData.class));

        return response;

    }
}
