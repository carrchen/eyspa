package com.eyspa.movie;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class TestTencent
{
    public static void main(final String[] args) throws IOException {
        final String url = "http://v.qq.com/x/list/movie";
        final Document doc = Jsoup.connect(url).get();
        final Elements movies = doc.getElementsByClass("list_item");
        for (final Element movie : movies) {
            final String sMov = movie.toString();
            final String href = sMov.substring(sMov.indexOf("<a href=\"") + 9, sMov.indexOf("\" class=\"figure\""));
            final String src = "http:" + sMov.substring(sMov.indexOf("r-lazyload=\"//") + 12, sMov.indexOf("\" alt=\""));
            final String title = sMov.substring(sMov.indexOf("videos:title\">") + 14, sMov.indexOf("</a></strong>"));
            final String score = sMov.substring(sMov.indexOf("<em class=\"score_l\">") + 20, sMov.indexOf("</em>"));
            System.out.println(score);
            String b = "";
            for (int i = 0; i < 751; ++i) {
                b = String.valueOf(b) + " ";
            }
            System.out.println("-----------------");
        }
    }
}
