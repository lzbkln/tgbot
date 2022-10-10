package org.example.tgbot.handlers;

import org.example.tgbot.dto.Request;
import org.example.tgbot.dto.Response;

public interface Handler {
    Response returnResponse(Request request);
}
