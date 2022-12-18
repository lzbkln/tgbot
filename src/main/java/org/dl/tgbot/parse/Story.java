package org.dl.tgbot.parse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

public class Story{

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
            Elements refs = document.select("center td > a[href]");
            for (Element ref : refs) { linksNames.add(ref.attr("href"));}
            linksNames.removeIf(s -> s.contains("https"));
        } catch (Exception e) {
            return null;
        }
        return linksNames;
    }

    public Map<String, String> makeDict(){
        Map<String, String> name_link_dict = new HashMap<>();
        for (int i = 0; i < makeNames().size();i++){
            name_link_dict.put(makeNames().get(i), makeLinks().get(i));
        }
        return name_link_dict;
    }

    public ArrayList<String> getSeasons(String nameOfStory){
        ArrayList<String> seasons = new ArrayList<>();
        String link = "https://gamesisart.ru/guide/" + makeDict().get(nameOfStory);
        try {
            Document document = Jsoup.connect(link).get();
            Elements el = document.select(".main_table_td > ul > table td a[href] > b");
            for (Element e : el) {
                seasons.add(e.text());
            }
        } catch (Exception e) {
            return null;
        }
        return seasons;
    }

    public ArrayList<String> getNames() {
        return new ArrayList<>(makeNames());
    }

    public ArrayList<String> getLinks() {
        return new ArrayList<>(makeLinks());
    }

}