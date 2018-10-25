<%@page import="org.jsoup.Jsoup"%>
<%@page import="org.jsoup.select.Elements"%>
<%@page import="org.jsoup.nodes.Document"%>
<%@page import="com.eyspa.movie.utils.Movie"%>
<%@page import="com.eyspa.movie.bean.SimInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String kw=request.getParameter("v_name");//关键字
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>Long Bro-影视搜搜</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" type="text/css" href="css/mainpage.css">

  </head>
  
  <body>
     <div class='bar'>
       <form action="/search.jsp" method="post" target="_blank">
         <ul>
            <li><a>eyspa影院</a></li>
            <li><a href='/MainPage?type=all&cate=all&page=1'>首页</a></li>
			<li><a href='/MainPage?type=dianshi&cate=all&page=1'>电视剧</a></li>
			<li><a href='/MainPage?type=dianying&cate=all&page=1'>电影</a></li>
			<li><a href='/MainPage?type=zongyi&cate=all&page=1'>综艺</a></li>
			<li><a href='/MainPage?type=dongman&cate=all&page=1'>动漫</a></li>
			<li><input type="text" placeholder="尽情搜吧" name="v_name"><input type="submit" value='搜索'></li>
		 </ul>
		</form>
	</div>
	<%
	    ArrayList<SimInfo> sis=Movie.searchInfo(kw);//根据关键字搜索到的视频的信息
	    %>
	           <br> &nbsp; &nbsp;根据你的输入，为你搜索到以下&nbsp;<font color="red" size="+2"><%=sis.size()%></font>&nbsp;条影视结果<br>
	    <%
		for(int i=0;i<sis.size();i++){
			SimInfo si=sis.get(i);
			String url=si.getUrl();
			
			String img=si.getImg();
			String name=si.getVname();
			String type=si.getType();
			String typ=null;
			if(type.equals("电视剧")){
				typ="dianshi";
			}else if(type.equals("电影")){
				typ="dianying";
			}else if(type.equals("综艺")){
				typ="zongyi";
			}else if(type.equals("动漫")){
				typ="dongman";
			}
			%>
			<div class='whole'>
			   <a href="/player?type=<%=typ%>&url=<%=url%>" target='_blank'>
			   <img src="<%=img%>" alt="<%=name%>" title="<%=name%>"><br>
			   <span>片名:<%=name%></span><br><span>类型:<%=type%></span></a>
			</div>
            <%
		}		
	 %>
    <!-- <div class="blank"></div> -->
    <hr width='95%' height='5px'>
	<%@include file="footer.jsp" %>
  </body>
</html>
