package com.eyspa.movie.utils;

import org.jsoup.Jsoup;
import java.io.IOException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ThreeTest
{
    public static void main(final String[] args) throws Exception {
        final String url = "https://www.360kan.com";
        final Document doc = Jsoup.connect(url).get();
        final Elements s = doc.getElementsByClass("list");
        System.out.println(s);
    }

    private static void hotfilm() throws IOException {
        final String url = "https://www.360kan.com";
        final Document doc = Jsoup.connect(url).get();
        final Elements s = doc.getElementsByClass("rmcontent");
        final String ss = s.toString();
        final String[] hot = ss.split("</li>");
        for (int i = 0; i < hot.length - 1; ++i) {
            final String href = hot[i].substring(hot[i].indexOf("href") + 6, hot[i].indexOf("\" class=\"js-link\">"));
            final String img = hot[i].substring(hot[i].indexOf("data-src") + 10, hot[i].indexOf("\" alt="));
            String title = "";
            String score = "";
            if (hot[i].contains("class=\"s2")) {
                title = hot[i].substring(hot[i].indexOf("class=\"s1") + 11, hot[i].indexOf("</span><span"));
                score = hot[i].substring(hot[i].indexOf("class=\"s2") + 11, hot[i].indexOf("</span></p"));
            }
            else {
                title = hot[i].substring(hot[i].indexOf("class=\"s1") + 11, hot[i].indexOf("</span></p"));
            }
            System.out.println(String.valueOf(href) + "\n" + img + "\n" + title + "\n" + score);
            System.out.println("-------------------------------");
        }
    }

    private static void spideIndex() throws IOException {
        final String url = "https://www.360kan.com/dianying/list.php";
        final String main = "https://www.360kan.com";
        final Document doc = Jsoup.connect(url).get();
        final Elements plays = doc.getElementsByClass("js-tongjic");
        for (final Element play : plays) {
            final String p = play.toString();
            final String[] s = p.split(">");
            final String hr = String.valueOf(s[0]) + ">";
            final String hre = String.valueOf(main) + hr.substring(hr.indexOf("href=") + 6, hr.indexOf("\">"));
            final String sr = String.valueOf(s[2]) + ">";
            final String src = sr.substring(sr.indexOf("src=") + 5, sr.indexOf("\">"));
            if (p.contains("付费")) {
                final String year = s[6].substring(0, 4);
                final String name = s[11].split("<")[0];
                final String score = s[13].split("<")[0];
                final String actor = s[16].split("<")[0];
            }
            else {
                final String year = s[4].substring(0, 4);
                final String name = s[9].split("<")[0];
                final String score = s[11].split("<")[0];
                final String actor = s[14].split("<")[0];
            }
            System.out.println(hre);
            final Document docu = Jsoup.connect(hre).get();
            final Element btn = docu.getElementById("js-site-wrap");
            final String sb = btn.toString();
            final String pu = sb.substring(sb.indexOf("href=\"") + 6, sb.indexOf("\" class=\""));
            System.out.println(pu);
            System.out.println("--------------------");
        }
    }

    public static void detailInfo() throws Exception {
        final String url = "https://www.360kan.com/va/Zc5pbXNxAJc3Dz.html";
        final Document doc = Jsoup.connect(url).get();
        final Elements s = doc.getElementsByClass("top-info");
        final String ss = s.toString();
        final String title = ss.substring(ss.indexOf("<h1>") + 4, ss.indexOf("</h1>"));
        final String score = ss.substring(ss.indexOf("<span class=\"s\">") + 16, ss.indexOf("</span>"));
        final String desc = ss.substring(ss.indexOf("<p class=\"item-desc js-close-wrap\" style=\"display:none;\">") + 73, ss.indexOf("<a href=\"#\" class=\"js-close btn\">"));
        final Element s2 = doc.getElementById("js-desc-switch");
        System.out.println(s2);
        final String[] r = s2.toString().split("<span>");
        System.out.println(r[1].substring(10, r[1].indexOf("</p>")));
        System.out.println("------------------------");
        System.out.println(r[2].substring(10, r[2].indexOf("</p>")));
        System.out.println("------------------------");
        System.out.println(r[3].substring(10, r[3].indexOf("</p>")));
        System.out.println("------------------------");
        final String lD = r[4].substring(r[4].indexOf("href") + 6, r[4].indexOf("\">"));
        final String director = r[4].substring(r[4].indexOf("\">") + 2, r[4].indexOf("</a>"));
        System.out.println(String.valueOf(lD) + director);
        System.out.println("------------------------");
        System.out.println(r[5]);
    }

    private static void searchVideo() throws IOException {
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
            ss.substring(ss.indexOf("<span class=\"playtype\">") + 24, ss.indexOf("]</span>"));
        }
    }

    private static void spideType() throws IOException {
        final String type = "dianshi";
        final String url = "https://www.360kan.com/" + type + "/list";
        final Document doc = Jsoup.connect(url).get();
        final Elements cats = doc.getElementsByClass("js-filter-content");
        final Element cat = cats.get(1);
        final String scat = cat.toString();
        final String[] ca = scat.split("href=\"");
        for (int i = 1; i < ca.length; ++i) {
            String lcat = ca[i].substring(0, ca[i].indexOf("\" target"));
            lcat = "https://www.360kan.com/" + type + "/list?year=all&area=all&act=all&cat=" + lcat.substring(lcat.indexOf("cat=") + 4);
            final String ncat = ca[i].substring(ca[i].indexOf("\">") + 2, ca[i].indexOf("</a>"));
            System.out.println(String.valueOf(lcat) + "    " + ncat);
            System.out.println("---------------------------");
        }
        final int num = ca.length - 1;
        System.out.println(num);
    }

    private static void spideSeries() throws IOException {
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

    public static void spideQi() throws Exception {
        final String url = "https://www.360kan.com/va/ZM5lanNw72I7ED.html";
        final Document doc = Jsoup.connect(url).get();
        final Elements es = doc.getElementsByClass("juji-main-wrap");
        final String ses = es.toString();
        final String[] s = ses.split("<li title");
        for (int i = 1; i < s.length; ++i) {
            System.out.println(s[i].substring(2, s[i].indexOf("\" class")));
            System.out.println(s[i].substring(s[i].indexOf("<a href=\"") + 9, s[i].indexOf("\" data-daochu")));
            System.out.println(s[i].substring(s[i].indexOf("data-src=\"") + 10, s[i].indexOf("\" alt=")));
            System.out.println(s[i].substring(s[i].indexOf("<span class=\"w-newfigure-hint\">") + 31, s[i].indexOf("</span>")));
            System.out.println("--------------------------------------------------------------");
        }
    }
}
