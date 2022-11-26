package org.dl.tgbot.command;

import org.dl.tgbot.dto.Request;
import org.dl.tgbot.dto.Response;
import org.dl.tgbot.handlers.report;
import org.dl.tgbot.service.CreateMessageService;


public class NoCommand implements Command {
    private final CreateMessageService createMessageService;

    public NoCommand(CreateMessageService createMessageService) {
        this.createMessageService = createMessageService;
    }
    @Override
    public Response execute(Request request) {
        return createMessageService.createTextMessage(request, report.UNKNOWN_INPUT.message);
    }
}
