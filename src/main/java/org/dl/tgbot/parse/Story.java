package org.dl.tgbot.parse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class Story implements Page {
    protected static String name;

    public ArrayList<String> makeNames() {
        ArrayList<String> storiesNames = new ArrayList<>();

        try {
            Document document = Jsoup.connect("https://gamesisart.ru/guide/Romance_Club_Prohozhdenie.html").get();
            Elements el = document.select("center td > a img[title]");
            for (Element e : el) {
                storiesNames.add(e.attr("title"));
            }
        } catch (Exception e) {
            return null;
        }

        return storiesNames;
    }

    public ArrayList<String> makeLinks() {
        ArrayList<String> linksNames = new ArrayList<>();

        try {
            Document document = Jsoup.connect("https://gamesisart.ru/guide/Romance_Club_Prohozhdenie.html").get();
            Elements el = document.select("center td > a[href]");
            for (Element e : el) {
                System.out.println(e.attr("href"));
            }
        } catch (Exception e) {
            return null;
        }

        return linksNames;
    }

    public ArrayList<String> printNames() {
        return new ArrayList<>(makeNames());
    }

    public ArrayList<String> printLinks() {
        return new ArrayList<>(makeLinks());
    }

    public String getPage(String link) {
        return link;
    }
}