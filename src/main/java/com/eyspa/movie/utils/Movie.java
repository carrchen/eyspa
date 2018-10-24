package com.eyspa.movie.utils;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import com.eyspa.movie.bean.Category;
import com.eyspa.movie.bean.SimInfo;
import com.eyspa.movie.bean.VideoObj;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Movie
{

    public static ArrayList<Category> getCate(final String type) throws IOException {
        final ArrayList<Category> gs = new ArrayList<Category>();
        final String url1 = "https://www.360kan.com/" + type + "/list";
        final Document doc1 = Jsoup.connect(url1).get();
        final Elements cats = doc1.getElementsByClass("js-filter-content");
        final Element cat = cats.get(1);
        final String scat = cat.toString();
        final String[] ca = scat.split("href=\"");
        for (int i = 1; i < ca.length; ++i) {
            final String lcat = ca[i].substring(0, ca[i].indexOf("\" target"));
            final String id = lcat.substring(lcat.indexOf("cat=") + 4);
            final String ncat = ca[i].substring(ca[i].indexOf("\">") + 2, ca[i].indexOf("</a>"));
            final Category g = new Category();
            g.setId(id);
            g.setName(ncat);
            g.setUrl(lcat);
            gs.add(g);
        }
        return gs;
    }

    public static ArrayList<VideoObj> getdata(final String url) throws IOException {
        final ArrayList<VideoObj> vos = new ArrayList<VideoObj>();
        final Document doc = Jsoup.connect(url).get();
        final Elements es = doc.getElementsByClass("rank");
        for (int j = 0; j < 5; ++j) {
            final String[] ss = es.get(j).toString().split("<li class=\"rank-item\">");
            for (int i = 1; i < ss.length; ++i) {
                String pai = "";
                if (ss[i].contains("top3\">")) {
                    pai = ss[i].substring(ss[i].indexOf("top3\">") + 6, ss[i].indexOf("</span>"));
                }
                else {
                    pai = ss[i].substring(ss[i].indexOf("class=\"num\">") + 12, ss[i].indexOf("</span>"));
                }
                final String name = ss[i].substring(ss[i].indexOf("title=\"") + 7, ss[i].indexOf("href") - 2);
                final String hre = ss[i].substring(ss[i].indexOf("href") + 6, ss[i].indexOf("\" class"));
                final String pn = ss[i].substring(ss[i].indexOf("class=\"vv\"") + 11, ss[i].indexOf("万</span>") + 1);
                final VideoObj vo = new VideoObj();
                vo.setPai(Integer.parseInt(pai));
                vo.setName(name);
                vo.setHref(hre);
                vo.setPn(pn);
                vos.add(vo);
            }
        }
        return vos;
    }

    public static String getPUrl(final String href) throws IOException {
        String url = "";
        final Document docu = Jsoup.connect(href).get();
        final Elements btns = docu.getElementsByClass("s-cover");
        final String sb = btns.toString();
        url = sb.substring(sb.indexOf("href=\"") + 6, sb.indexOf("\" class=\""));
        return url;
    }

    public static ArrayList searchInfo(String kw) throws IOException {
        kw = URLEncoder.encode(kw, "utf-8");
        final String url = "https://so.360kan.com/index.php?kw=" + kw;
        final Document doc = Jsoup.connect(url).get();
        final Elements es = doc.getElementsByClass("js-longitem");
        final ArrayList<SimInfo> sis = new ArrayList<SimInfo>();
        for (final Element s : es) {
            final String ss = s.toString();
            final String uu = ss.substring(ss.indexOf("<a href=\"") + 9, ss.indexOf("\" class=\"g-playicon"));
            final String img = ss.substring(ss.indexOf("<img src=\"") + 10, ss.indexOf("\" alt=\""));
            final String name = ss.substring(ss.indexOf("title=\"") + 7, ss.indexOf("\" data-logger=\"ctype"));
            final String type = ss.substring(ss.indexOf("<span class=\"playtype\">") + 24, ss.indexOf("]</span>"));
            final SimInfo si = new SimInfo();
            si.setUrl(uu);
            si.setImg(img);
            si.setVname(name);
            si.setType(type);
            sis.add(si);
        }
        return sis;
    }

    public static String[] getPlayUrl(final String url) throws IOException {
        final Document doc = Jsoup.connect(url).get();
        Elements es = doc.getElementsByClass("list-content");
        String ses = es.toString();
        String playUrl = "";
        String[] s;
        if (ses.isEmpty()) {
            es = doc.getElementsByClass("people");
            ses = es.toString();
            playUrl = ses.substring(ses.indexOf("playerurl") + 13, ses.indexOf("m3u8") + 4);
            s = new String[] { "", playUrl };
        }
        else {
            final String img = ses.substring(ses.indexOf("src") + 5, ses.indexOf(".jpg") + 4);
            if (ses.contains("m3u8")) {
                playUrl = ses.substring(ses.indexOf("<img src=\"/Tpl/custom/style/images/ckplay.png\">") + 25, ses.indexOf("m3u8") + 4);
                playUrl = playUrl.substring(playUrl.indexOf("blank") + 7);
            }
            s = new String[] { img, playUrl };
        }
        return s;
    }
}