package org.dl.tgbot.dto;

import java.util.List;

public class KeyboardComponent implements Component {
    private final List<Button> buttons;

    public KeyboardComponent(List<Button> markup) {
        this.buttons = markup;
    }

    public List<Button> getButtons() {
        return buttons;
    }
}
