package com.eyspa.movie.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class SearchTest
{
    public static void main(final String[] args) throws IOException {
        final String url = "https://so.360kan.com/index.php?kw=下一站";
        final Document doc = Jsoup.connect(url).get();
        final Elements es = doc.getElementsByClass("js-longitem");
        for (final Element s : es) {
            System.out.println("----------------------------------------------------");
            final String ss = s.toString();
            final String u = ss.substring(ss.indexOf("<a href=\"") + 9, ss.indexOf("\" class=\"g-playicon"));
            final Document docu = Jsoup.connect(u).get();
            final Elements btns = docu.getElementsByClass("s-cover");
            String pu = null;
            final String sb = btns.toString();
            pu = sb.substring(sb.indexOf("href=\"") + 6, sb.indexOf("\" class=\""));
            final String img = ss.substring(ss.indexOf("<img src=\"") + 10, ss.indexOf("\" alt=\""));
            final String name = ss.substring(ss.indexOf("title=\"") + 7, ss.indexOf("\" data-logger=\"ctype"));
            final String type = ss.substring(ss.indexOf("<span class=\"playtype\">") + 24, ss.indexOf("]</span>"));
            System.out.println(type);
        }
    }
}
