package org.dl.tgbot.command;

import org.dl.tgbot.dto.Request;
import org.dl.tgbot.dto.Response;
import org.dl.tgbot.service.CreateMessageService;

public class StoriesCommand implements Command {
    private final CreateMessageService createMessageService;

    public StoriesCommand(CreateMessageService createMessageService) {
        this.createMessageService = createMessageService;
    }

    @Override
    public Response execute(Request request) {
        return createMessageService.createTextMessage(request, "Здесь будут истории");
    }
}
