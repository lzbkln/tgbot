package org.dl.tgbot.command;

import org.dl.tgbot.dto.Request;
import org.dl.tgbot.dto.Response;
import org.dl.tgbot.handlers.Report;
import org.dl.tgbot.service.CreateMessageService;

public class HelpCommand implements Command {
    private final CreateMessageService createMessageService;

    public HelpCommand(CreateMessageService createMessageService) {
        this.createMessageService = createMessageService;
    }

    @Override
    public Response execute(Request request) {
        return createMessageService.createTextMessage(request, Report.HELP.message);
    }
}
