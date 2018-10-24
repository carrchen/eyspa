package com.eyspa.movie;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class TestYouku
{
    public static void main(final String[] args) throws IOException {
        searchVideo();
    }

    private static void searchVideo() throws IOException {
        final String url = "http://www.soku.com/search_video/q_%E5%A4%A7%E8%AF%9D%E8%A5%BF%E6%B8%B8?f=1&kb=020200000000000_%E4%BD%99%E7%BD%AA_%E5%A4%A7%E8%AF%9D%E8%A5%BF%E6%B8%B8";
        final Document doc = Jsoup.connect(url).get();
        final Elements infs = doc.getElementsByClass("s_dir");
        for (final Element e : infs) {
            final String se = e.toString();
            System.out.println(se);
            final String img = "http:" + se.substring(se.indexOf("<img src=\"") + 10, se.indexOf("<img src=\"") + 58);
            final String name = se.substring(se.indexOf("_log_title=") + 12, se.indexOf("_iku_showid") - 2);
            String type = se.substring(se.indexOf("<span class=\"base_type\">") + 24, se.indexOf("<span class=\"base_type\">") + 38);
            type = type.substring(0, type.indexOf("</span>"));
            String area = se.substring(se.indexOf("<span class=\"s_area\">") + 24, se.indexOf("<span class=\"s_area\">") + 48);
            area = area.substring(0, area.indexOf("</span>"));
            String desc = se.substring(se.indexOf("<span data-text=") + 17, se.indexOf("</span> <a _log_type="));
            desc = desc.substring(0, desc.indexOf("\">"));
            final int l = e.toString().length();
            System.out.println(l);
            System.out.println("--------------------");
        }
    }

    private static void spideInfo() throws IOException {
        final String url = "http://list.youku.com/category/show/c_96_u_1_pt_1_s_1_d_4.html?spm=a2h1n.8251845.filterPanel.5!5~1~3!2~A";
        final Document doc = Jsoup.connect(url).get();
        final Elements movs = doc.getElementsByClass("mr1");
        for (final Element mov : movs) {
            final String sMov = mov.toString();
            final String href = "http://" + sMov.substring(sMov.indexOf("<a href=\"//") + 11, sMov.indexOf(".html\"") + 5);
            final String img = sMov.substring(sMov.indexOf("_src=\"") + 6, sMov.indexOf("\" src=\""));
            final String title = sMov.substring(sMov.indexOf("title=\"") + 7, sMov.indexOf("\" target="));
            if (sMov.contains("<li class=\"actor\">")) {
                final String mix = sMov.substring(sMov.indexOf("<li class=\"actor\">"));
                final String[] actors = mix.split("<a href=");
                for (int i = 1; i < actors.length; ++i) {
                    final String actorL = actors[i].substring(3, actors[i].indexOf("\" target"));
                    System.out.println(actorL);
                    final String actorN = actors[i].substring(actors[i].indexOf(">") + 1, actors[i].indexOf("<"));
                    System.out.println(actorN);
                }
                final String playNum = mix.substring(mix.indexOf("<li>") + 4, mix.indexOf("次播放 </li>") + 3);
                System.out.println(playNum);
            }
            else {
                final String mix = sMov.substring(sMov.indexOf("<ul class=\"info-list\">"));
                System.out.println(mix);
                final String playNum2 = mix.substring(mix.indexOf("<li>") + 4, mix.indexOf("次播放 </li>") + 3);
                System.out.println(playNum2);
            }
            System.out.println("-----------------------------");
        }
    }
}
