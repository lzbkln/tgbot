package org.dl.tgbot.dto;

public class Button {
    private final String name;
    private final String callbackData;

    public Button(String name, String callbackData) {
        this.name = name;
        this.callbackData = callbackData;
    }

    public String getName() {
        return name;
    }

    public String getCallbackData() {
        return callbackData;
    }
}
