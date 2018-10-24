package com.eyspa.movie.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import com.eyspa.movie.utils.*;
import org.jsoup.*;
import com.eyspa.movie.bean.*;
import java.util.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.*;
@WebServlet(urlPatterns = "/MainPage/*")
public class MainPage extends HttpServlet
{
    private static final Logger log = LoggerFactory.getLogger(MainPage.class);

    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String type = request.getParameter("type");
        final String cate = request.getParameter("cate");
        final String page = request.getParameter("page");
        int pageI = 1;
        String cateI = "";
        if ("dianshi".equals(type)) {
            type = "dianshi";
        }
        else if ("dianying".equals(type)) {
            type = "dianying";
        }
        else if ("zongyi".equals(type)) {
            type = "zongyi";
        }
        else if ("dongman".equals(type)) {
            type = "dongman";
        }
        else if ("all".equals(type)) {
            type = "all";
        }
        else {
            type = "dianshi";
        }
        if ("other".equals(cate)) {
            cateI = "other";
        }
        else if ("all".equals(cate)) {
            cateI = "all";
        }
        else {
            final ArrayList<String> arr1 = new ArrayList<String>();
            for (int i = 100; i < 135; ++i) {
                final String t = new StringBuilder(String.valueOf(i)).toString();
                arr1.add(t);
            }
            for (int i = 0; i < arr1.size(); ++i) {
                if (arr1.get(i).equals(cate)) {
                    cateI = arr1.get(i);
                    break;
                }
                if (i == arr1.size() - 1) {
                    cateI = "all";
                }
            }
        }
        final ArrayList<String> arr2 = new ArrayList<String>();
        for (int i = 1; i < 24; ++i) {
            final String t = new StringBuilder(String.valueOf(i)).toString();
            arr2.add(t);
        }
        for (int i = 0; i < arr2.size(); ++i) {
            if (arr2.get(i).equals(page)) {
                pageI = Integer.parseInt(arr2.get(i));
                break;
            }
            if (i == arr2.size() - 1) {
                pageI = 1;
            }
        }
        final PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML>");
        out.println("<HEAD>");
        out.println("<TITLE>eyspa影院|最新，最全，最受欢迎的影视免费免广告在线观看</TITLE>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/mainpage.css\"> ");
        out.println("</HEAD>");
        out.println("  <BODY>");
        out.println("<div class='bar'>");
        out.println("<form action=\"/search.jsp\" method=\"post\" target=\"_blank\"><ul>");
        out.println("<li><img src = \"images/logo.png\" style=\"width:40px;\"></img></li>");//<a>eyspa影院</a>
        if (type.equals("all")) {
            out.println("<li><current><a href='/MainPage?type=all&cate=all&page=1'>首页</a></current></li>");
            out.println("<li><a href='/MainPage?type=dianshi&cate=all&page=1'>电视剧</a></li>");
            out.println("<li><a href='/MainPage?type=dianying&cate=all&page=1'>电影</a></li>");
            out.println("<li><a href='/MainPage?type=zongyi&cate=all&page=1'>综艺</a></li>");
            out.println("<li><a href='/MainPage?type=dongman&cate=all&page=1'>动漫</a></li>");
        }
        else if (type.equals("dianshi")) {
            out.println("<li><a href='/MainPage?type=all&cate=all&page=1'>首页</a></li>");
            out.println("<li><current><a href='/MainPage?type=dianshi&cate=all&page=1'>电视剧</current></a></li>");
            out.println("<li><a href='/MainPage?type=dianying&cate=all&page=1'>电影</a></li>");
            out.println("<li><a href='/MainPage?type=zongyi&cate=all&page=1'>综艺</a></li>");
            out.println("<li><a href='/MainPage?type=dongman&cate=all&page=1'>动漫</a></li>");
        }
        else if (type.equals("dianying")) {
            out.println("<li><a href='/MainPage?type=all&cate=all&page=1'>首页</a></li>");
            out.println("<li><a href='/MainPage?type=dianshi&cate=all&page=1'>电视剧</a></li>");
            out.println("<li><current><a href='/MainPage?type=dianying&cate=all&page=1'>电影</a></current></li>");
            out.println("<li><a href='/MainPage?type=zongyi&cate=all&page=1'>综艺</a></li>");
            out.println("<li><a href='/MainPage?type=dongman&cate=all&page=1'>动漫</a></li>");
        }
        else if (type.equals("zongyi")) {
            out.println("<li><a href='/MainPage?type=all&cate=all&page=1'>首页</a></li>");
            out.println("<li><a href='/MainPage?type=dianshi&cate=all&page=1'>电视剧</a></li>");
            out.println("<li><a href='/MainPage?type=dianying&cate=all&page=1'>电影</a></li>");
            out.println("<li><current><a href='/MainPage?type=zongyi&cate=all&page=1'>综艺</a></current></li>");
            out.println("<li><a href='/MainPage?type=dongman&cate=all&page=1'>动漫</a></li>");
        }
        else if (type.equals("dongman")) {
            out.println("<li><a href='/MainPage?type=all&cate=all&page=1'>首页</a></li>");
            out.println("<li><a href='/MainPage?type=dianshi&cate=all&page=1'>电视剧</a></li>");
            out.println("<li><a href='/MainPage?type=dianying&cate=all&page=1'>电影</a></li>");
            out.println("<li><a href='/MainPage?type=zongyi&cate=all&page=1'>综艺</a></li>");
            out.println("<li><current><a href='/MainPage?type=dongman&cate=all&page=1'>动漫</a></current></li>");
        }
        out.println("<li><input type=\"text\" placeholder=\"尽情搜吧\" name=\"v_name\"><input type=\"submit\" value='搜索'></li>");
        out.println("</ul></form>");
        out.write("</div>");
        out.write("<div class='cate'>");
        if (!type.equals("all")) {
            out.write("类型:&nbsp;");
            final ArrayList<Category> cs = Movie.getCate(type);
            out.write("<a href='/MainPage?type=" + type + "&cate=all&page=1'>全部</a>&nbsp;");
            for (final Category c : cs) {
                final String cat = c.getId();
                final String name = c.getName();
                final String hre = c.getUrl();
                out.write("<a href='/MainPage?type=" + type + "&cate=" + cat + "&page=1'>" + name + "</a>&nbsp;");
            }
        }
        out.write("</div>");
        final String main = "https://www.360kan.com";
        if (type.equals("all")) {
            out.write("<p>&nbsp;&nbsp;&nbsp;热门电影</p>");
            final Document doc = Jsoup.connect(main).get();
            final Elements s = doc.getElementsByClass("rmcontent");
            final String ss = s.toString();
            final String[] hot = ss.split("</li>");
            for (int j = 0; j < hot.length - 1; ++j) {
                final String href = hot[j].substring(hot[j].indexOf("href") + 6, hot[j].indexOf("\" class=\"js-link\">"));
                final String img = hot[j].substring(hot[j].indexOf("data-src") + 10, hot[j].indexOf("\" alt="));
                String name2 = "";
                String score = "";
                if (hot[j].contains("class=\"s2")) {
                    name2 = hot[j].substring(hot[j].indexOf("class=\"s1") + 11, hot[j].indexOf("</span><span"));
                    score = hot[j].substring(hot[j].indexOf("class=\"s2") + 11, hot[j].indexOf("</span></p"));
                }
                else {
                    name2 = hot[j].substring(hot[j].indexOf("class=\"s1") + 11, hot[j].indexOf("</span></p"));
                }
                out.write("<div class='whole'><a href=\"/player.jsp?type=dianying&url=" + href + "\" target='_blank'>" + "<img src='" + img + "' title='" + name2 + "' alt='" + img + "'>" + "<span>" + name2 + "</span><br><span>" + score + "</span></a></div>");
            }
            final ArrayList<VideoObj> vos = Movie.getdata(main);
            System.out.println(vos.size());
            out.write("<div class='rank'>");
            out.write("<br>影视排行榜<br>");
            for (int k = 0; k < 10; ++k) {
                final VideoObj vo = vos.get(k);
                final int pai = vo.getPai();
                String name3 = vo.getName();
                final String href2 = vo.getHref();
                final String pn = vo.getPn();
                if (name3.length() > 6) {
                    name3 = String.valueOf(name3.substring(0, 5)) + "...";
                }
                final String purl = Movie.getPUrl(href2);
                out.write("<pai>" + pai + "</pai>&nbsp;<a href='" + href2 + "'>" + name3 + "</a>&nbsp;" + pn + "<br>");
            }
            out.write("</div>");
            out.write("<div class='rank'>");
            out.write("<br>电视剧排行榜<br>");
            for (int k = 10; k < 20; ++k) {
                final VideoObj vo = vos.get(k);
                final int pai = vo.getPai();
                String name3 = vo.getName();
                final String href2 = vo.getHref();
                final String pn = vo.getPn();
                if (name3.length() > 6) {
                    name3 = String.valueOf(name3.substring(0, 5)) + "...";
                }
                final String purl = Movie.getPUrl(href2);
                out.write("<pai>" + pai + "</pai>&nbsp;<a href=\"/player.jsp?type=dianshi&url=" + href2 + "&href=" + purl + "\" target='_blank'>" + name3 + "</a>&nbsp;" + vo.getPn() + "<br>");
            }
            out.write("</div>");
            out.write("<div class='rank'>");
            out.write("<br>综艺排行榜<br>");
            for (int k = 20; k < 30; ++k) {
                final VideoObj vo = vos.get(k);
                final int pai = vo.getPai();
                String name3 = vo.getName();
                final String href2 = vo.getHref();
                final String pn = vo.getPn();
                if (name3.length() > 6) {
                    name3 = String.valueOf(name3.substring(0, 5)) + "...";
                }
                final String purl = Movie.getPUrl(href2);
                out.write("<pai>" + pai + "</pai>&nbsp;<a href=\"/player.jsp?type=zongyi&url=" + vo.getHref() + "&href=" + purl + "\" target='_blank'>" + name3 + "</a>&nbsp;" + vo.getPn() + "<br>");
            }
            out.write("</div>");
            out.write("<div class='rank'>");
            out.write("<br>电影排行榜<br>");
            for (int k = 30; k < 40; ++k) {
                final VideoObj vo = vos.get(k);
                final int pai = vo.getPai();
                String name3 = vo.getName();
                final String href2 = vo.getHref();
                final String pn = vo.getPn();
                if (name3.length() > 6) {
                    name3 = String.valueOf(name3.substring(0, 5)) + "...";
                }
                final String purl = Movie.getPUrl(href2);
                out.write("<pai>" + pai + "</pai>&nbsp;<a href=\"/player.jsp?type=dianying&url=" + vo.getHref() + "&href=" + purl + "\" target='_blank'>" + name3 + "</a>&nbsp;" + vo.getPn() + "<br>");
            }
            out.write("</div>");
            out.write("<div class='rank'>");
            out.write("<br>动漫排行榜<br>");
            for (int k = 40; k < 50; ++k) {
                final VideoObj vo = vos.get(k);
                final int pai = vo.getPai();
                String name3 = vo.getName();
                final String href2 = vo.getHref();
                final String pn = vo.getPn();
                if (name3.length() > 6) {
                    name3 = String.valueOf(name3.substring(0, 5)) + "...";
                }
                final String purl = Movie.getPUrl(href2);
                out.write("<pai>" + pai + "</pai>&nbsp;<a href=\"/player.jsp?type=dongman&url=" + vo.getHref() + "&href=" + purl + "\" target='_blank'>" + name3 + "</a>&nbsp;" + vo.getPn() + "<br>");
            }
            out.write("</div>");
        }
        else {
            final String url = "https://www.360kan.com/" + type + "/list.php?rank=rankhot&cat=" + cateI + "&area=all&year=all&pageno=" + pageI;
            final Document doc2 = Jsoup.connect(url).get();
            final Elements plays = doc2.getElementsByClass("js-tongjic");
            for (final Element play : plays) {
                final String p = play.toString();
                final String[] s2 = p.split(">");
                final String hr = String.valueOf(s2[0]) + ">";
                final String hre2 = String.valueOf(main) + hr.substring(hr.indexOf("href=") + 6, hr.indexOf("\">"));
                final String sr = String.valueOf(s2[2]) + ">";
                final String src = sr.substring(sr.indexOf("src=") + 5, sr.indexOf("\">"));
                String year;
                String name4;
                String score2;
                String actor;
                if (p.contains("付费")) {
                    year = s2[6].substring(0, 4);
                    name4 = s2[11].split("<")[0];
                    score2 = s2[13].split("<")[0];
                    actor = s2[16].split("<")[0];
                }
                else {
                    year = s2[4].substring(0, 4);
                    name4 = s2[9].split("<")[0];
                    score2 = s2[11].split("<")[0];
                    actor = s2[14].split("<")[0];
                }
                if (name4.length() > 11) {
                    name4 = String.valueOf(name4.substring(0, 11)) + "...";
                }
                if (actor.length() > 16) {
                    actor = String.valueOf(actor.substring(0, 16)) + "...";
                }
                if (score2.equals("")) {
                    score2 = "暂无";
                }
                if (year.length() != 4) {
                    year = "暂无";
                }
                if (actor.equals("")) {
                    actor = "暂无";
                }
                out.write("<div class='whole'><a href=\"/player.jsp?type=" + type + "&url=" + hre2 + "\" target='_blank'>" + "<img src='" + src + "' title='" + name4 + "' alt='" + src + "'>" + "<span>" + name4 + "</span><br><p>" + actor + "</p></a></div>");
            }
            out.write("<center><div class='page'>");
            if (pageI > 1) {
                out.write("<a  href=\"/MainPage?type=" + type + "&cate=" + cate + "&page=" + (pageI - 1) + "\">上</a>");
            }
            if (pageI > 18) {
                for (int l = 19; l <= 24; ++l) {
                    if (l == pageI) {
                        out.write("<current><a>" + l + "</a></current>");
                    }
                    else {
                        out.write("<a href=\"/MainPage?type=" + type + "&cate=" + cate + "&page=" + l + "\">" + l + "</a>");
                    }
                }
            }
            else {
                for (int l = pageI; l < pageI + 6; ++l) {
                    if (l == pageI) {
                        out.write("<current><a>" + l + "</a></current>");
                    }
                    else {
                        out.write("<a href=\"/MainPage?type=" + type + "&cate=" + cate + "&page=" + l + "\">" + l + "</a>");
                    }
                }
            }
            if (pageI < 24) {
                out.write("<a  href=\"/MainPage?type=" + type + "&cate=" + cate + "&page=" + (pageI + 1) + "\">下</a>");
            }
            out.write("</div></center>");
        }
        out.write("<hr width='80%' height='5px'>");
        out.write("<br>");
        out.write("<center><p>All Rights Reserved 版权所有：<a href=\"http://www.longqcloud.cn/\" target=\"_blank\">eyspa影院</a> 备案号：<a href=\"http://www.miitbeian.gov.cn\" target=\"_blank\">豫ICP备16023798号-1</a></p></center>");
        out.write("<center><p>本站所有视频均采集自互联网，如有侵犯贵公司合法权益，请联系QQ<a href=\"http://wpa.qq.com/msgrd?v=3&uin=1459892910&site=qq&menu=yes\" target=\"_blank\">1459892910</a>删除，谢谢</p></center>");
        out.write("<center><p>Designed by Long Bro</p></center>");
        out.println("  </BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
    }

    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
        response.setContentType("text/html");
        final PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML>");
        out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
        out.println("  <BODY>");
        out.print("    This is ");
        out.print(this.getClass());
        out.println(", using the POST method");
        out.println("  </BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
    }
}
