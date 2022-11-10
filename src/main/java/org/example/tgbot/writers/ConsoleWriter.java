package org.example.tgbot.writers;

import org.example.tgbot.dto.Response;
import org.example.tgbot.dto.TextComponent;

public class ConsoleWriter implements Writer {
    @Override
    public void write(Response response) {
        System.out.println(response.getComponent(TextComponent.class).getText());
    }
}
