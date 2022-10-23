package org.example.tgbot.dto;

import java.util.HashSet;
import java.util.Set;

public class Composite {
    protected final Set<Component> components = new HashSet<>();

    public void addComponent(Component component) {
        components.add(component);
    }
    public <T extends Component> T getComponent(Class<T> componentClass) {
        for (Component component: components) {
            if(component.getClass().equals(componentClass))
                return (T)component;
        }
        return null;
    }
}
