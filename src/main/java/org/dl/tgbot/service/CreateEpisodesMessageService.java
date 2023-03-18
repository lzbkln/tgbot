package org.dl.tgbot.service;

import org.dl.tgbot.dto.*;
import org.dl.tgbot.parse.Story;

import java.util.ArrayList;
import java.util.List;

public class CreateEpisodesMessageService implements CreateMessageService{

    @Override
    public Response createTextMessage(Request request, String nameOfSeason) {
        Response response = new Response();
        String nameOfStory = request.getComponent(CallbackComponent.class).getButtonCallbackData().split("/")[0];


        Story story = new Story();
        ArrayList<String> episodes = story.getEpisode(nameOfStory, nameOfSeason);
        List<Button> buttons = new ArrayList<>();
        for (String button : episodes) {
            int limit = 1;
            String subStr = button.codePointCount(0, button.length()) > limit ?
                    button.substring(0, button.offsetByCodePoints(0, limit)) : button;
            buttons.add(new Button(subStr, request.getComponent(CallbackComponent.class).getButtonCallbackData() + "/" + subStr));
        }

        response.addComponent(new TextComponent("Выберите эпизод:"));
        response.addComponent(new MetaData(request.getComponent(MetaData.class)));
        response.addComponent(request.getComponent(CallbackComponent.class));
        response.addComponent(new KeyboardComponent(buttons));

        return response;
    }
}
