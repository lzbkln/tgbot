package org.dl.tgbot.service;

import org.dl.tgbot.dto.*;

import java.util.ArrayList;
import java.util.List;

public class CreateGuidesMessageService implements CreateMessageService{
    @Override
    public Response createTextMessage(Request request, String ans) {
        Response response = new Response();

        response.addComponent(new TextComponent("А вот и гайд на " + request.getComponent(CallbackComponent.class).getButtonCallbackData()));
        response.addComponent(new MetaData(request.getComponent(MetaData.class)));
        response.addComponent(request.getComponent(CallbackComponent.class));

        return response;
    }
}
