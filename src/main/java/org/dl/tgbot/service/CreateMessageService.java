package org.dl.tgbot.service;

import org.dl.tgbot.dto.Request;
import org.dl.tgbot.dto.Response;

public interface CreateMessageService {
    /**
     *
     * @param request Обертка над данными
     * @param ans Строка, которую нужно отправить
     */
    Response createTextMessage(Request request, String ans);
}
