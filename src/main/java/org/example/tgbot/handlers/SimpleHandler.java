package org.example.tgbot.handlers;

import org.example.tgbot.dto.Response;
import org.example.tgbot.dto.Request;

public class SimpleHandler implements Handler {
    public Response handleRequest(Request request) {
        return new Response(request.data());
    }
}
