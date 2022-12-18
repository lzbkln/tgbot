package org.dl.tgbot.service;

import org.dl.tgbot.dto.*;

import java.util.ArrayList;
import java.util.List;

public class CreateEpisodesMessageService implements CreateMessageService{

    @Override
    public Response createTextMessage(Request request, String nameOfSeason) {
        Response response = new Response();

        // TODO: добавить логику вместо тестовых эпизодов: getEpisodes(nameOfSeason)
        String [] episodes = {"1 эпизод", "2 эпизод"};
        List<Button> buttons = new ArrayList<>();
        for (String button : episodes) {
            buttons.add(new Button(button, request.getComponent(CallbackComponent.class).getButtonCallbackData() + "/" + button));
        }

        response.addComponent(new TextComponent("Выберите эпизод:"));
        response.addComponent(new MetaData(request.getComponent(MetaData.class)));
        response.addComponent(request.getComponent(CallbackComponent.class));
        response.addComponent(new KeyboardComponent(buttons));

        return response;
    }
}
