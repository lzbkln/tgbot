package org.dl.tgbot.service;

import org.dl.tgbot.dto.*;
import org.dl.tgbot.parse.Story;

public class CreateGuidesMessageService implements CreateMessageService{
    @Override
    public Response createTextMessage(Request request, String ans) {
        Response response = new Response();

        String nameOfStory = request.getComponent(CallbackComponent.class).getButtonCallbackData().split("/")[0];
        String season = request.getComponent(CallbackComponent.class).getButtonCallbackData().split("/")[1];
        String episode = request.getComponent(CallbackComponent.class).getButtonCallbackData().split("/")[2];

        Story story = new Story();
        String stories = story.getStory(season, episode, nameOfStory);
        response.addComponent(new TextComponent("А вот и гайд на " + request.getComponent(CallbackComponent.class).getButtonCallbackData() + '\n'+ stories));
        response.addComponent(new MetaData(request.getComponent(MetaData.class)));
        response.addComponent(request.getComponent(CallbackComponent.class));

        return response;
    }
}
