package com.eyspa.movie.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class TestAiqiyi
{
    public static void main(final String[] args) throws IOException {
        final String url = "http://list.iqiyi.com/www/1/----------------iqiyi--.html";
        final Document doc = Jsoup.connect(url).get();
        final Elements pics = doc.getElementsByClass("site-piclist_pic");
        for (final Element pic : pics) {
            final String sPic = pic.toString();
            final String source = sPic.substring(sPic.indexOf("href=\"") + 6, sPic.indexOf("\" class"));
            if (sPic.contains("> <p class=\"video_dj ")) {
                sPic.substring(sPic.indexOf("src=\"//") + 7, sPic.indexOf("> <p class=\"video_dj ") - 1);
            }
            else if (sPic.contains("> <p class=\"viedo_lt")) {
                sPic.substring(sPic.indexOf("src=\"//") + 7, sPic.indexOf("> <p class=\"viedo_lt") - 1);
            }
            else {
                sPic.substring(sPic.indexOf("src=\"//") + 7, sPic.indexOf("<div class=\"wrapper-listTitle\">") - 6);
            }
            final String duration = null;
            if (sPic.contains("<p class=\"viedo_rb\"> <span class=\"icon-vInfo\">")) {
                final int i = sPic.indexOf("<p class=\"viedo_rb\"> <span class=\"icon-vInfo\">");
                final int j = sPic.indexOf("00 </span> </p>");
                System.out.println(j);
            }
        }
    }
}
