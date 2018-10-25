package com.eyspa.movie.servlet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
@WebServlet(urlPatterns = "/SearchYouku/*")
public class SearchYouku extends HttpServlet
{
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
    }

    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String index = request.getParameter("name");
        index = URLEncoder.encode(index, "utf-8");
        final String url = "http://www.soku.com/search_video/q_" + index;
        final Document doc = Jsoup.connect(url).get();
        final Elements infs = doc.getElementsByClass("");
    }
}
