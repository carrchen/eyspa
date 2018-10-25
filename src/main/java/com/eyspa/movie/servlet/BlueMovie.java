package com.eyspa.movie.servlet;


import com.eyspa.movie.utils.Movie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
@WebServlet(urlPatterns = "/BlueMovie/*")
public class BlueMovie extends HttpServlet
{
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        final PrintWriter out = response.getWriter();
        String cata = request.getParameter("cata");
        final String page = request.getParameter("page");
        int pageI = 1;
        if ("ren".equals(cata)) {
            cata = "ren";
        }
        else if ("qiang".equals(cata)) {
            cata = "qiang";
        }
        else if ("zhi".equals(cata)) {
            cata = "zhi";
        }
        else if ("ya".equals(cata)) {
            cata = "ya";
        }
        else if ("wu".equals(cata)) {
            cata = "wu";
        }
        else if ("tou".equals(cata)) {
            cata = "tou";
        }
        else if ("zhong".equals(cata)) {
            cata = "zhong";
        }
        else if ("ka".equals(cata)) {
            cata = "ka";
        }
        else {
            cata = "all";
        }
        final ArrayList<String> arr = new ArrayList<String>();
        for (int i = 1; i < 200; ++i) {
            final String t = new StringBuilder(String.valueOf(i)).toString();
            arr.add(t);
        }
        System.out.println(arr.get(0));
        for (int i = 0; i < arr.size(); ++i) {
            if (arr.get(i).equals(page)) {
                pageI = Integer.parseInt(arr.get(i));
                break;
            }
            if (i == arr.size() - 1) {
                pageI = 1;
            }
        }
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML>");
        out.println("<head>");
        out.println("<title>轻松一刻</title>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/mainpage.css\"> ");
        out.println("</head>");
        out.println("  <BODY>");
        out.println("<div class='bar'>");
        out.println("<ul>");
        out.println("<li><a href='/Film/MainServlet?cata=all&page=1'>首页</li></a>");
        out.println("<li><a href='/Film/MainServlet?cata=ren&page=1'>人妻熟女</li></a>");
        out.println("<li><a href='/Film/MainServlet?cata=qiang&page=1'>强奸乱伦</li></a>");
        out.println("<li><a href='/Film/MainServlet?cata=zhi&page=1'>制服师生</li></a>");
        out.println("<li><a href='/Film/MainServlet?cata=ya&page=1'>亚洲情色</li></a>");
        out.println("<li><a href='/Film/MainServlet?cata=wu&page=1'>无码专区</li></a>");
        out.println("<li><a href='/Film/MainServlet?cata=tou&page=1'>偷拍自拍</li></a>");
        out.println("<li><a href='/Film/MainServlet?cata=zhong&page=1'>中文字幕</li></a>");
        out.println("<li><a href='/Film/MainServlet?cata=ka&page=1'>卡通动漫</li></a>");
        out.println("</ul>");
        out.write("</div>");
        final String purl = "http://www.szyk13.com";
        final String url = "http://www.szyk13.com/?s=index-index-p-" + pageI + ".html";
        final Document doc = Jsoup.connect(url).get();
        final Elements es = doc.getElementsByClass("videoContent");
        final String ses = es.toString();
        final String[] ss = ses.split("<li>");
        for (int j = 1; j < ss.length; ++j) {
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
            final String href = String.valueOf(purl) + ss[j].substring(ss[j].indexOf("href=\"") + 6, ss[j].indexOf("\"><img"));
            String name = ss[j].substring(ss[j].indexOf("gif\">") + 5, ss[j].indexOf("</a>"));
            if (name.contains("\\")) {
                name = String.valueOf(name.substring(0, name.indexOf("\\") - 1)) + name.substring(name.indexOf("\\") + 1);
            }
            final String region = ss[j].substring(ss[j].indexOf("region") + 8, ss[j].indexOf("</span> <span"));
            final String cate = ss[j].substring(ss[j].indexOf("category") + 10, ss[j].indexOf("</span> <a"));
            final String date = ss[j].substring(ss[j].indexOf("class=\"time") + 13, ss[j].indexOf("</span> </li>"));
            final String[] s = Movie.getPlayUrl(href);
            final String img = s[0];
            final String playurl = s[1];
            out.write("<div class='whole'><a href=\"/playerBlue.jsp?&href=" + playurl + "\" target='_blank'>" + "<img src='" + img + "' title='" + name + "' alt='" + img + "'>" + "<span>" + name + "</span><br><span>" + date + "</span></a></div>");
        }
        out.write("<hr width='80%' height='5px'>");
        out.write("<center><div class='page'>");
        if (pageI > 1) {
            out.write("<a  href=\"/BlueMovie?&page=" + (pageI - 1) + "\">上</a>");
        }
        if (pageI > 194) {
            for (int k = 195; k <= 200; ++k) {
                final String pageIndex = "<a href=\"/BlueMovie?&page=" + k + "\">" + k + "</a>";
                if (k == pageI) {
                    out.write("<current>" + pageIndex + "</current>");
                }
                else {
                    out.write(pageIndex);
                }
            }
        }
        else {
            for (int k = pageI; k < pageI + 6; ++k) {
                final String pageIndex = "<a href=\"/BlueMovie?&page=" + k + "\">" + k + "</a>";
                if (k == pageI) {
                    out.write("<current>" + pageIndex + "</current>");
                }
                else {
                    out.write(pageIndex);
                }
            }
        }
        if (pageI < 200) {
            out.write("<a  href=\"/BlueMovie?&page=" + (pageI + 1) + "\">下</a>");
        }
        out.write("</div></center>");
        out.println("  </BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
    }

    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
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
