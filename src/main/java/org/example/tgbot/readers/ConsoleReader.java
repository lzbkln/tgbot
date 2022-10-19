package org.example.tgbot.readers;

import org.example.tgbot.dto.Request;

import java.util.Scanner;

public class ConsoleReader implements Reader {
    @Override
    public Request read() {
        Scanner scanner = new Scanner(System.in);

        return new Request(scanner.nextLine());
    }
}
