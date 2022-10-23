package org.example.tgbot.handlers;

import org.example.tgbot.dto.MetaData;
import org.example.tgbot.dto.Response;
import org.example.tgbot.dto.Request;
import org.example.tgbot.dto.TextComponent;

public class SimpleHandler implements Handler {
    public Response handleRequest(Request request) {
        Response response = new Response();
        TextComponent textComponent = new TextComponent(request.getComponent(TextComponent.class));
        response.addComponent(textComponent);

        MetaData metaData = new MetaData(request.getComponent(MetaData.class));
        response.addComponent(metaData);
        /*
        System.out.println("handle request");
        System.out.println(textComponent.getText());
        System.out.println(request.getComponent(MetaData.class).getFromId());
        */
        return response;

    }
}
