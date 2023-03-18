package org.dl.tgbot.service;

import org.dl.tgbot.dto.*;
import org.dl.tgbot.parse.Story;

import java.util.ArrayList;
import java.util.List;

public class CreateStoriesMessageService implements CreateMessageService {
    @Override
    public Response createTextMessage(Request request, String ans) {

        Story story = new Story();
        ArrayList<String> stories = story.getNames();

        List<Button> buttons = new ArrayList<>();
        for (String button : stories) {
            buttons.add(new Button(button, button));
        }

        String text;
        text = "Выберите историю:";
        Response response = new Response(request.getComponent(MetaData.class));
        response.addComponent(new TextComponent(text));
        response.addComponent(new KeyboardComponent(buttons));

        return response;
    }
}
