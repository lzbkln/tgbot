package org.dl.tgbot.dto;

public class CallbackComponent implements Component {
    private final String buttonCallbackData;
    private final String callbackQueryId;

    public CallbackComponent(String buttonCallbackData, String callbackQueryId) {
        this.buttonCallbackData = buttonCallbackData;
        this.callbackQueryId = callbackQueryId;
    }

    public String getCallbackQueryId() {
        return callbackQueryId;
    }

    public String getButtonCallbackData() {
        return buttonCallbackData;
    }
}
