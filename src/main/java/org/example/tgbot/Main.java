package org.example.tgbot;

import org.example.tgbot.dto.Request;
import org.example.tgbot.dto.Response;
import org.example.tgbot.handlers.Handler;
import org.example.tgbot.handlers.SimpleHandler;
import org.example.tgbot.readers.ConsoleReader;
import org.example.tgbot.readers.Reader;
import org.example.tgbot.writers.ConsoleWriter;
import org.example.tgbot.writers.Writer;

public class Main {
    public static void main(String[] args) {
        Reader reader = new ConsoleReader();
        Writer writer = new ConsoleWriter();
        Handler handler = new SimpleHandler();

        while (true) {
            Request request = reader.read();
            if (request.data().isEmpty()) {
                break;
            }
            Response response = handler.returnResponse(request);
            writer.write(response);
        }
    }
}
