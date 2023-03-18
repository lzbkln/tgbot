package org.dl.tgbot.readers;

import org.dl.tgbot.dto.Request;
import org.dl.tgbot.dto.TextComponent;

import java.util.Scanner;

public class ConsoleReader implements Reader {
    @Override
    public Request read() {
        Scanner scanner = new Scanner(System.in);

        Request request = new Request();
        request.addComponent(new TextComponent(scanner.nextLine()));

        return request;
    }
}