package org.dl.tgbot.handlers;

import org.dl.tgbot.dto.MetaData;
import org.dl.tgbot.dto.Request;
import org.dl.tgbot.dto.Response;
import org.dl.tgbot.dto.TextComponent;

public class SimpleHandler implements Handler {
    public Response handleRequest(Request request) {
        Response response = new Response();

        response.addComponent(request.getComponent(TextComponent.class));
        response.addComponent(request.getComponent(MetaData.class));

        return response;

    }
}
