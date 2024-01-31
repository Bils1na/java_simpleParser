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
    // \d{2}\.\d{2} https://www.youtube.com/watch?v=IxokmNxzqPs
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

    protected static int printFourValues(Elements values, int index) {
        int iterationCount = 4;
        if (index == 0) {
            Element valueLine = values.get(0);
            boolean isMorning = valueLine.text().contains("Утро");

            if (isMorning) {
                iterationCount = 3;
            }

            for (int i = 0; i < iterationCount; i++) {
                Element valueLn = values.get(index + i);
                for (Element td : valueLn.select("td")) {
                    System.out.print(td.text() + "   ");
                }
                System.out.println();
            }
            return iterationCount;
        } else {
            for (int i = 0; i < 4; i++) {
                Element valueLine = values.get(index + i);
                for (Element td : valueLine.select("td")) {
                    System.out.print(td.text() + "   ");
                }
                System.out.println();
            }
            return iterationCount;
        }
    }

}
