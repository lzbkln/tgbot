package org.dl.tgbot.parse;

import java.io.UnsupportedEncodingException;

public interface Page {
    /**
     * @param link страница
     * @return page возвращает страницу в виде строки
     */
    String getPage (String link) throws UnsupportedEncodingException;
}