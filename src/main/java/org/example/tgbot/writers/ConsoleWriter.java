package org.example.tgbot.writers;

import org.example.tgbot.dto.Response;

public class ConsoleWriter implements Writer {
    @Override
    public void write(Response response) {
        System.out.println(response.data());
    }
}
