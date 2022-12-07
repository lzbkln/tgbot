package org.dl.tgbot;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Loader {
    public String getProperty(String fileName, String key) {
        Properties property = new Properties();
        try (InputStream fis = getClass().getResourceAsStream("/"+fileName)) {
            property.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Конфигурационный файл отсутствует", e);
        }

        return property.getProperty(key);
    }
}
