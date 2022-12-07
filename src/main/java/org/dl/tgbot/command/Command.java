package org.dl.tgbot.command;

import org.dl.tgbot.dto.Request;
import org.dl.tgbot.dto.Response;

public interface Command {
    Response execute(Request request);
}
