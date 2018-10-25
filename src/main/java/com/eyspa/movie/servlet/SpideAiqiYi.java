package com.eyspa.movie.servlet;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/SpideAiqiYi/*")
public class SpideAiqiYi extends HttpServlet
{
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String url = "http://list.iqiyi.com/www/1/----------------iqiyi--.html";
        final Document doc = Jsoup.connect(url).get();
        final Elements pics = doc.getElementsByClass("site-piclist_pic");
        final Elements infos = doc.getElementsByClass("site-piclist_info");
        final PrintWriter out = response.getWriter();
        out.println("<head>");
        out.println("<meta title='全部影视'></meta>");
        out.println("<style type=\"text/css\">");
        out.print("body{margin-left:60px;}");
        out.println("  .whole{border:solid red;width:200px;height:250px;margin:10px;float: left;}");
        out.println("</style>");
        out.println("</head>");
        for (final Element pic : pics) {
            final String sPic = pic.toString();
            final String href = sPic.substring(sPic.indexOf("href=\"") + 6, sPic.indexOf("\" class"));
            String img;
            if (sPic.contains("> <p class=\"video_dj ")) {
                img = sPic.substring(sPic.indexOf("src=\"//") + 7, sPic.indexOf("> <p class=\"video_dj ") - 1);
            }
            else if (sPic.contains("> <p class=\"viedo_lt")) {
                img = sPic.substring(sPic.indexOf("src=\"//") + 7, sPic.indexOf("> <p class=\"viedo_lt") - 1);
            }
            else {
                img = sPic.substring(sPic.indexOf("src=\"//") + 7, sPic.indexOf("<div class=\"wrapper-listTitle\">") - 6);
            }
            img = "http://" + img;
            System.out.println(img);
            out.write("<a href='" + href + "'> <div class='whole'><img style='width:200px;height:250px;' src='" + img + "' title='" + img + "'></div></a>");
        }
    }

    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
    }
}
