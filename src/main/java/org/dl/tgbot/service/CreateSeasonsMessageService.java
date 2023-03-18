package org.dl.tgbot.service;

import org.dl.tgbot.dto.*;
import org.dl.tgbot.parse.Story;

import java.util.ArrayList;
import java.util.List;

public class CreateSeasonsMessageService implements CreateMessageService{

    @Override
    public Response createTextMessage(Request request, String nameOfStory) {
        Response response = new Response();

        Story story = new Story();
        ArrayList<String> seasons = story.getSeasons(nameOfStory);
        List<Button> buttons = new ArrayList<>();
        for (String button : seasons) {
            buttons.add(new Button(button, request.getComponent(CallbackComponent.class).getButtonCallbackData() + "/" + button));
        }

        response.addComponent(new TextComponent("Выберите сезон:"));
        response.addComponent(new MetaData(request.getComponent(MetaData.class)));
        response.addComponent(request.getComponent(CallbackComponent.class));
        response.addComponent(new KeyboardComponent(buttons));

        return response;
    }
}
