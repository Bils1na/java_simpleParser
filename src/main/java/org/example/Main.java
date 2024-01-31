package org.example;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Main {
    public static void main(String[] args) throws Exception {
        Document page = Parser.getPage();
        // css query language
        Element tableWth = page.select("table[class=wt]").first();
        Elements days = tableWth.select("tr[class=wth]");
        Elements dayValue = tableWth.select("tr[valign=top]");
        int index = 0;
        for (Element day : days) {
            String dateString = day.select("th[id=dt]").text();
            String date = Parser.getDateFromString(dateString);
            System.out.println(date + "    Явление    Температура    Давл    Влажность   Ветер");
            int count = Parser.printFourValues(dayValue, index);
            index += count;
        }

    }
}