package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    // \d{2}\.\d{2}
    private static Pattern pattern = Pattern.compile("\\d{2}\\.\\d{2}");

    public static Document getPage() throws IOException {
        String url = "http://pogoda.spb.ru/";
        Document page = Jsoup.parse(new URL(url), 3000);
        return page;
    }

    protected static String getDateFromString(String stringDate) throws Exception {
        Matcher matcher = pattern.matcher(stringDate);
        if (matcher.find()) {
            return matcher.group();
        }
        throw new Exception("Can't extract date from string!");
    }

    protected static void printFourValues(Elements values, int index) {
        for (int i = 0; i < 4; i++) {
            Element valueLine = values.get(index);
            for (Element td : valueLine.select("td")) {
                System.out.println(td.text() + "   ");
            }
        }
    }

}
