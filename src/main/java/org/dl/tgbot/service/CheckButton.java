package org.dl.tgbot.service;

import org.dl.tgbot.dto.CallbackComponent;
import org.dl.tgbot.dto.Request;
import org.dl.tgbot.dto.Response;

public class CheckButton implements CreateMessageService {

    @Override
    public Response createTextMessage(Request request, String ans) {
        Response response;
        String callData = request.getComponent(CallbackComponent.class).getButtonCallbackData();
        String [] callDataArray = callData.split("/");
        CreateMessageService createMessageService;
        if (callDataArray.length == 1) {
            createMessageService = new CreateSeasonsMessageService();
            response = createMessageService.createTextMessage(request, callDataArray[0]);
        } else if (callDataArray.length == 2) {
            createMessageService = new CreateEpisodesMessageService();
            response = createMessageService.createTextMessage(request, callDataArray[1]);
        } else {
            createMessageService = new CreateGuidesMessageService();
            response = createMessageService.createTextMessage(request, callDataArray[2]);
        }
        return response;
    }
}
