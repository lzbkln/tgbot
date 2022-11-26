package org.dl.tgbot.command;

import org.dl.tgbot.dto.Request;
import org.dl.tgbot.dto.Response;
import org.dl.tgbot.handlers.report;
import org.dl.tgbot.service.CreateMessageServiceImpl;

public class NoCommand implements Command {
    private final CreateMessageServiceImpl createMessageService;

    public NoCommand(CreateMessageServiceImpl createMessageService) {
        this.createMessageService = createMessageService;
    }

    public Response execute(Request request) {
        return createMessageService.createTextMessage(request, report.UNKNOWN_INPUT.message);
    }
}
