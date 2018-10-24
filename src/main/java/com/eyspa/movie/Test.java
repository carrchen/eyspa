package com.eyspa.movie;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Test
{
    public static void main(final String[] args) throws IOException {
        final String url = "https://www.360kan.com/ct/O0DncMDbLYC2DD.html";
        final Document doc = Jsoup.connect(url).get();
        final Elements s = doc.getElementsByClass("s-top-list-ji");
        final String ss = s.toString();
        System.out.println(ss);
        String se = "";
        if (ss.contains("display:none")) {
            se = s.toString().substring(ss.indexOf("display:none") + 15, ss.indexOf("收起</a>") - 63);
        }
        else if (ss.contains("<i class=\"ico-yugao\">")) {
            se = s.toString().substring(ss.indexOf("js-tab") + 14, ss.indexOf("<i class=\"ico-yugao\">"));
        }
        else {
            se = s.toString().substring(ss.indexOf("js-tab") + 14);
        }
        System.out.println(se);
        final String[] hs = se.split("href=\"");
        String u = "";
        for (int i = 1; i < hs.length; ++i) {
            u = hs[i].substring(0, hs[i].indexOf("\">"));
            System.out.println(u);
            System.out.println("-------------------------------");
        }
    }


    /*public static void main(final String[] args) throws IOException {
        String url = "https://www.360kan.com/dianshi/list?year=all&amp;area=all&amp;act=all&amp;cat=101";
        final String[] trimChars = { "amp;" };
        url = url.trim();
        System.out.println(url);
    }*/

    /*public static void main(final String[] args) throws IOException {
        final String url = "http://www.pdsu.edu.cn";
        final Document doc = Jsoup.connect(url).get();
        final Element z = doc.getElementById("Tzh").val("151360247");
        System.out.println(z);
        System.out.println("-----------------");
        final Element m = doc.getElementById("Tmm").val("145989");
        final Element f = doc.getElementById("form1");
        System.out.println(f);
        System.out.println("-----------------");
        System.out.println(m);
    }*/
}
