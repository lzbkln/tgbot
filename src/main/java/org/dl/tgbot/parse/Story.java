package org.dl.tgbot.parse;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Story implements Page {
    protected static String name;
    // TODO: переписать парсинг страницы, чтобы не использовался файл
    public HashMap<String, String> makeDictNames() throws FileNotFoundException {
        String path = "./src/main/resources/stories";
        File file = new File(path);
        Scanner scannerF = new Scanner(file);
        HashMap<String, String> linkNames = new HashMap<>();
        while (scannerF.hasNextLine()) {
            String line = scannerF.nextLine();
            Pattern pattern = Pattern.compile ("\"(.+?)\"");
            Matcher matcher = pattern.matcher (line);
            int i = 0;
            String[] pair = new String[2];
            while (matcher.find()) {
                pair[i] = matcher.group(1);
                i++;
            }
            linkNames.put(pair[0], pair[1]);
        }
        return linkNames;
    }

    public ArrayList<String> printTitles() throws FileNotFoundException {
        return new ArrayList<>(makeDictNames().keySet());
    }

    public String getPage(String link)  {
        StringBuilder page = new StringBuilder();
        BufferedReader in = null;
        try {
            URL url = new URL(link);
            in = new BufferedReader(new InputStreamReader(url.openStream(),"cp1251")); //позволяет создать входной поток для чтения файла ресурса, связанного с созданным объектом класса URL
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
                if (in != null){
                    in.close();
                }
            }
            catch (IOException e){
                System.out.println("IOException");
            }

        }
        return page.toString();
    }
}