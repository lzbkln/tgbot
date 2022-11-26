package org.dl.tgbot.service;

import org.dl.tgbot.dto.Request;
import org.dl.tgbot.dto.Response;

public interface CreateMessageService {
    Response createTextMessage(Request request, String ans);
}
