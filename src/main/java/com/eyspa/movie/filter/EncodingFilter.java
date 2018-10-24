package com.eyspa.movie.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter
        implements Filter
{
    public void init(FilterConfig config)
            throws ServletException
    {}

    public void destroy() {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {
        request.setCharacterEncoding("UTF-8");

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        chain.doFilter(request, response);
    }
}