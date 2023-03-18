package org.dl.tgbot.parse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Story{

    public ArrayList<String> makeStoriesNames() {
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

    public Map<String, String> makeDictOfNamesLinks(){
        Map<String, String> name_link_dict = new HashMap<>();
        ArrayList<String> names = makeStoriesNames();
        ArrayList<String> links = makeLinks();
        for (int i = 0; i < names.size();i++){
            name_link_dict.put(names.get(i), links.get(i));
        }
        return name_link_dict;
    }

    public ArrayList<String> getSeasons(String nameOfStory){
        ArrayList<String> seasons = new ArrayList<>();
        String link = "https://gamesisart.ru/guide/" + makeDictOfNamesLinks().get(nameOfStory);
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

    public ArrayList<String> getEpisode(String nameOFStory, String nameOfSeason){
        ArrayList<String> episodes = new ArrayList<>();
        Map<String, ArrayList<String>> map = new HashMap<>();
        String link = "https://gamesisart.ru/guide/" + makeDictOfNamesLinks().get(nameOFStory);
        try {
            Document document = Jsoup.connect(link).get();
            Elements el = document.select(".main_table_td > ul > table td a[href]");
            for (Element e : el) { episodes.add(e.text());}
            String element;
            ArrayList<String> episodes2 = new ArrayList<>();
            while (episodes.size() != 0){
                element = episodes.remove(0);
                String substr = element.substring(0, 5);
                if (substr.equals("Сезон") && episodes2.size() != 0 || episodes.size() == 0) {
                    if (episodes.size() == 0)
                        episodes2.add(element);
                    map.put(episodes2.remove(0), episodes2);
                    episodes2 = new ArrayList<>();
                }
                episodes2.add(element);
            }
        } catch (Exception e) {
            return null;
        }
        return map.get(nameOfSeason);
    }


    public String makeLink(String season, String episode, String nameOFStory) {
        String link;
        String linkOfStory = makeDictOfNamesLinks().get(nameOFStory).replace(".html", "");
        String episodeNumber;
        String seasonNumber;
        seasonNumber = (season.substring(season.length() - 1));
        if (seasonNumber.equals("1")){
            if (Character.isDigit(episode.charAt(0))) {
                episodeNumber = String.valueOf(episode.charAt(0));
                link = "https://gamesisart.ru/guide/" + linkOfStory
                        + ".html#Act_" + seasonNumber + "_" + episodeNumber;
            }
            else {
                link = "https://gamesisart.ru/guide/" + linkOfStory
                        + ".html#Bonus";
            }
        }
        else{
            if (Character.isDigit(episode.charAt(0))) {
                episodeNumber = String.valueOf(episode.charAt(0));
                link = "https://gamesisart.ru/guide/" + linkOfStory + "_"
                        + seasonNumber + ".html#Act_" + seasonNumber + "_" + episodeNumber;
            }
            else {
                link = "https://gamesisart.ru/guide/" + linkOfStory + "_"
                        + seasonNumber + ".html#Bonus";
            }
        }
        return link;
    }

    public String getPage(String link)  {
        StringBuilder page = new StringBuilder();
        BufferedReader in = null;
        try {
            URL url = new URL(link);
            in = new BufferedReader(new InputStreamReader(url.openStream(), "cp1251"));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                page.append(inputLine);
            }
            in.close();
        }
        catch (IOException e){
            System.out.println("IOException");
        }
        finally {
            try{
                if (in!=null){
                    in.close();
                }
            }
            catch (IOException e){
                System.out.println("IOException");
            }

        }
        return page.toString();
    }

    public String getStory(String season, String episode, String nameOFStory){
        StringBuilder tempString = new StringBuilder();
        String page = getPage(makeLink(season, episode, nameOFStory));
        String seasonNumber = season.substring(season.length() - 1);
        String firstTag = "<a name=\"Act_" + seasonNumber + "_" + episode;
        Pattern pattern = Pattern.compile(firstTag + ".+?<br/><br/><br/>");
        Matcher matcher = pattern.matcher(page);
        Pattern pattern1 = Pattern.compile("<p>(.+?)</p>");
        Matcher matcher1;
        if (matcher.find()) {
            matcher1 = pattern1.matcher(matcher.group());
            int i = 1;
            String string;
            String[] nonType = {"<font class=\"TextItem\">",
                    "</font> <font class=\"TextUp\">",
                    "</font>",
                    "&#34;",
                    "<font class=\"TextLove\">",
                    "<font class=\"TextGold\">"};
            while (matcher1.find()) {
                string = matcher1.group(i);
                if (string.charAt(0) != '<') {
                    string = string.replaceAll("&#34;", "\"");
                    tempString.append('\n').append(string);
                }
                else {
                    for (String s : nonType) {
                        if (string.contains(s)){
                            string = string.replaceAll(s, "");
                        }
                    }
                    tempString.append(string);
                }
            }
        }
        return tempString.toString();
    }

    public ArrayList<String> getNames() {
        return new ArrayList<>(makeStoriesNames());
    }
}