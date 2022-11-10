package org.dl.tgbot.writers;

import org.dl.tgbot.dto.Response;
import org.dl.tgbot.dto.TextComponent;

public class ConsoleWriter implements Writer {
    @Override
    public void write(Response response) {
        System.out.println(response.getComponent(TextComponent.class).getText());
    }
}
