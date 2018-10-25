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

@WebServlet(urlPatterns = "/SpideYouku/*")
public class SpideYouku extends HttpServlet
{
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String pageId = request.getParameter("pageId");
        final String url = "http://list.youku.com/category/show/c_96_u_1_pt_1_s_1_d_4_p_" + pageId + ".html";
        final Document doc = Jsoup.connect(url).get();
        final Elements movs = doc.getElementsByClass("mr1");
        final PrintWriter out = response.getWriter();
        out.println("<head>");
        out.println("<title>eyspa影院_小酷欢迎你</title>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/index.css\"> ");
        out.println("</head>");
        out.write("<body>");
        for (final Element mov : movs) {
            final String sMov = mov.toString();
            String href = sMov.substring(sMov.indexOf("<a href=\"") + 9, sMov.indexOf(".html\"") + 5);
            if (sMov.contains("正片")) {
                href = "http:" + href;
            }
            final String img = sMov.substring(sMov.indexOf("_src=\"") + 6, sMov.indexOf("\" src=\""));
            final String title = sMov.substring(sMov.indexOf("title=\"") + 7, sMov.indexOf("\" target="));
            if (sMov.contains("<li class=\"actor\">")) {
                final String mix = sMov.substring(sMov.indexOf("<li class=\"actor\">"));
                final String playNum = mix.substring(mix.indexOf("<li>") + 4, mix.indexOf("次播放 </li>") + 3);
                out.write(" <div class='whole'><a href='/LongVideos/playerYouku.jsp?href=" + href + "'  target=\"_blank\">" + "<img src='" + img + "' title='" + img + "' alt='" + img + "'><em>" + title + "</em>" + "</a><br><em>主演:</em>");
                final String[] actors = mix.split("<a href=");
                for (int i = 1; i < actors.length; ++i) {
                    final String actorL = "http://" + actors[i].substring(3, actors[i].indexOf("\" target"));
                    final String actorN = actors[i].substring(actors[i].indexOf(">") + 1, actors[i].indexOf("<"));
                    out.write("<a href='" + actorL + "' target='_blank' title='" + actorN + "'><em>" + actorN + "," + "<em></a>");
                }
                out.write("<br><em>" + playNum + "</em></div>");
            }
            else {
                final String mix = sMov.substring(sMov.indexOf("<ul class=\"info-list\">"));
                final String playNum = mix.substring(mix.indexOf("<li>") + 4, mix.indexOf("次播放 </li>") + 3);
                out.write("<a href=\"/LongVideos/playerYouku.jsp?href=" + href + "\" target=\"_blank\"> <div class='whole'>" + "<img src='" + img + "' title='" + img + "' alt='" + img + "'><em>" + title + "</em>" + "<br><em>" + playNum + "</em></div></a>");
            }
        }
        out.write("</body>");
    }

    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
    }
}
