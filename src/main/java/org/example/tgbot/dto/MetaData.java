package org.example.tgbot.dto;

import org.telegram.telegrambots.meta.api.objects.Message;

public class MetaData implements Component {
    // в других полях пока нет необходимости
    Long who;
    public MetaData(Message message) {
        this.who = message.getFrom().getId();
    }
    public MetaData(MetaData metaData) {
        this.who = metaData.who;
    }
    public Long getFromId() {
        return who;
    }
}
