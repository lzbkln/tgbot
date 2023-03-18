package org.dl.tgbot.handlers;

import org.dl.tgbot.dto.Request;
import org.dl.tgbot.dto.Response;

public interface Handler {
    Response handleRequest(Request request);
}
