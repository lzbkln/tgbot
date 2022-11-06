package org.example.tgbot.readers;

import org.example.tgbot.dto.Request;
import org.example.tgbot.dto.TextComponent;

import java.util.Scanner;

public class ConsoleReader implements Reader {
    @Override
    public Request read() {
        Scanner scanner = new Scanner(System.in);
        Request request = new Request();
        TextComponent textComponent = new TextComponent(scanner.nextLine());
        request.addComponent(textComponent);

        return request;
    }
}