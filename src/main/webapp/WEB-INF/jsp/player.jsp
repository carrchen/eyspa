<%@page import="utils.Movie"%>
<%@page import="org.jsoup.nodes.Element"%>
<%@page import="org.jsoup.select.Elements"%>
<%@page import="org.jsoup.Jsoup"%>
<%@page import="org.jsoup.nodes.Document"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
String type=request.getParameter("type");
String url=request.getParameter("url");
String hre=request.getParameter("href");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>正在播放</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="来eyspa影院，免费免广告观看最新，最全，最受欢迎的影视。">
	<link rel="stylesheet" type="text/css" href="css/player.css">
  </head>
  
  <body>
         <div class="top"><iframe src='http://api.baiyug.cn/vip/?url=<%=hre %>' class="player" width=66% height="550px" align="left"></iframe><br>
         <!-- http://beaacc.com/api.php?url=-->
         <!-- http://player.jidiaose.com/supapi/iframe.php?v= -->
         <!-- http://y.mt2t.com/lines?url= -->
         <!-- http://www.82190555.com/index/qqvod.php?url= -->
      <%
           String title=null;
           //电视和动漫的区别在于:前者有评分，后者无;前者为演员，后者为人物
		   if(type.equals("dianshi")||type.equals("dongman")){
		     Document doc=Jsoup.connect(url).get();
		     Elements s=doc.getElementsByClass("s-top-list-ji");
		     String ss=s.toString();
		     String se="";
		     if(ss.contains("display:none")){//集数较多，折叠的
		        //可能有很多页个100集，需将其分离再只将剧集的链接合并一起
		        String m[]=ss.split("<div class=\"num-tab-main g-clear js-tab\"");
			    //System.out.println(m.length);
			    String e="";
			    for(int i=1;i<m.length;i++){
			        e=m[i].substring(m[i].indexOf("display:none")+15,m[i].indexOf("收起</a>")-63);
			        se=se+e;
			    }
		     }else if(ss.contains("<i class=\"ico-yugao\">")){//有预告片的
			     se=s.toString().substring(ss.indexOf("js-tab")+14,ss.indexOf("<i class=\"ico-yugao\">"));
		     }else{//无预告片且未折叠，有预告片且折叠
			    se=s.toString().substring(ss.indexOf("js-tab")+14);
		     }
		     //System.out.println(se);
		     //爬取视频名等信息
		     Elements info=doc.getElementsByClass("s-top-info");
		     String sinfo=info.toString();	
		     //System.out.println(sinfo);	     
		     title=sinfo.substring(sinfo.indexOf("<h1>")+4,sinfo.indexOf("</h1>"));
		     
		     out.write("<div class='episode'>");
		     out.println("<br><h2 class='title'>"+title+"</h2>");
		     String update="";//更新至哪一集或完结
		     if(type.equals("dianshi")){
		         update=sinfo.substring(sinfo.indexOf("<p class=\"tag\">")+15,sinfo.indexOf("<p class=\"link g-clear\">"));;//更新情况
		         update=update.substring(0,update.indexOf("</p>"));
		         String desc=sinfo.substring(sinfo.indexOf("class=\"btn2\"")+19,sinfo.indexOf("分集介绍")-2);
		         desc=desc.replace("amp;", "");
		         out.println(update+"<br>");
		         out.println("<other><a href='"+desc+"' target='_blank'>分集剧情</a></other><br>");
		         
		     }else{//因动漫无分集介绍，故与电视剧的截取方式不同
		         update=sinfo.substring(sinfo.indexOf("<p class=\"tag\">")+15,sinfo.indexOf("<div id=\"js-desc-switch\""));;//更新情况
		         update=update.substring(0,update.indexOf("</p>"));
		         out.println(update+"<br>");
		     }
		     
 			   Element s8=doc.getElementById("js-desc-switch");			   
	       	   String[] r=s8.toString().split("<span>");
			    //类型
			   String style=r[1].substring(10, r[1].indexOf("</p>"));
			   //年份
			   String year=r[2].substring(10,r[2].indexOf("</p>"));
			   //地区
			   String area=r[3].substring(10,r[3].indexOf("</p>"));
			   //导演
			   String lD=r[4].substring(r[4].indexOf("href")+6, r[4].indexOf("\">"));
			   String director=r[4].substring(r[4].indexOf("\">")+2, r[4].indexOf("</a>"));
			   //演员
		       String as[]=r[5].split("&nbsp;");
		       if(type.equals("dianshi")){
		             String score=sinfo.substring(sinfo.indexOf("class=\"s\">")+10,sinfo.indexOf("class=\"s\">")+13);
		       		 out.println("<br>评分:<des>"+score+"</des><br>类型:<des>"+style+"</des><br>年份:<des>"+year+"</des><br>地区:<des>"+area+"</des><br>导演:<des>"+director+"</des><br>演员:");
		       }else{
		        	 out.println("<br>类型:<des>"+style+"</des><br>年份:<des>"+year+"</des><br>地区:<des>"+area+"</des><br>导演:<des>"+director+"</des><br>人物:");
		       }
		       for(int i=0;i<as.length-1;i++){
		         //System.out.println(as[i]);
		         String href=as[i].substring(as[i].indexOf("href")+6,as[i].indexOf("\">"));
		         //System.out.println(href);
		         String name=as[i].substring(as[i].indexOf("\">")+2,as[i].indexOf("</a>"));
		         out.println("<other><a href='"+href+"' target='_blank'><des>"+name+"</des></a></other>");
		       }
		       out.write("<br><hr width='100%' height='5px'>");
		     
		     
		     //剧集
		     String[] hs=se.split("href=\"");
		     String u="";
		     
		     for(int i=1;i<hs.length;i++){
			   u=hs[i].substring(0,hs[i].indexOf("\">"));
			   if(u.equals(hre)){//当前播放
			       out.write("<current><a href='/player.jsp?type="+type+"&url="+url+"&href="+u+"'>"+i+"</a></current>&emsp;");
			   }else{
			       out.write("<noc><a href='/player.jsp?type="+type+"&url="+url+"&href="+u+"'>"+i+"</a></noc>&emsp;");
			   }
			   //每行八集
			   if(i%3==0){
			      out.write("<br>");
			   }
		   }
		   out.write("</div></div><br>");
		   
		   //简介
		   String desc=sinfo.substring(sinfo.indexOf("<p class=\"item-desc js-close-wrap\" style=\"display:none;\">")+73,sinfo.indexOf("<a href=\"#\" class=\"js-close btn\">"));
		   out.println("</div>简介:"+desc+"");
		 }
         else if(type.equals("dianying")||type.equals("zongyi")){
		   Document doc=Jsoup.connect(url).get();
		   Elements s=doc.getElementsByClass("top-info");//电影
		   String ss=s.toString();
		   out.write("<div class='zongyi'>");
		   
		   //片名
		    title=ss.substring(ss.indexOf("<h1>")+4,ss.indexOf("</h1>"));
		   
		   if(type.equals("dianying")){
		      //评分
		      String score=ss.substring(ss.indexOf("<span class=\"s\">")+16,ss.indexOf("</span>"));
		      if(score.length()>6){
		         	out.println("<br><h2 class='title'>"+title+"</h2><br>");
		      }else{
		         	out.println("<br><h2 class='title'>"+title+"</h2><br>评分:"+score+"<br>");   
		      }
		      
		     Element s8=doc.getElementById("js-desc-switch");
		     String[] r=s8.toString().split("<span>");		
		     //年份
		     String year=r[1].substring(10, r[1].indexOf("</p>"));
		     //地区
		     String area=r[2].substring(10,r[2].indexOf("</p>"));
		     //演员
		     String actor=r[3].substring(10,r[3].indexOf("</p>"));
		     String as[]=actor.split("&nbsp;");
		     
		     
		     //导演
		     String lD=r[4].substring(r[4].indexOf("href")+6, r[4].indexOf("\">"));
		     String director=r[4].substring(r[4].indexOf("\">")+2, r[4].indexOf("</a>"));
		     //System.out.println(lD+director);
		     out.println("年份:<des>"+year+"</des><br>地区:<des>"+area+"</des><br>导演:<des>"+director+"</des><br>演员:");
		     for(int i=0;i<as.length-1;i++){
		         //System.out.println(as[i]);
		         String href=as[i].substring(as[i].indexOf("href")+6,as[i].indexOf("\">"));
		         //System.out.println(href);
		         String name=as[i].substring(as[i].indexOf("\">")+2,as[i].indexOf("</a>"));
		         out.println("<des>"+name+"</des>");
		     }
		     String pu=Movie.getPUrl(url);//根据视频的链接获取播放链接
		     out.println("<br><br><a href='/player.jsp?type="+type+"&url="+url+"&href="+pu+"'>点此播放</a></div>");
		   }else if(type.equals("zongyi")){
		   		out.println("<br><em class='title'>"+title+"</em><br>");
		        //爬取综艺节目的详细信息
		        Element s8=doc.getElementById("js-desc-switch");
		        String[] r=s8.toString().split("<span>");
		        //类型
		        String st=r[1].substring(10, r[1].indexOf("</p>"));
		        //年份
		       String year=r[2].substring(10,r[2].indexOf("</p>"));
		       //地区
		       String area=r[3].substring(10, r[3].indexOf("</p>"));
		       //播出电台频道channel
		       String channel=r[4].substring(10, r[4].indexOf("</p>"));
		        out.println("<br>类型：<des>"+st+"</des><br>年份:<des>"+year+"</des><br>地区:<des>"+area+"</des><br>频道：<des>"+channel+"</des><br>主持:");
		       //主持
		       String host=r[5].substring(10, r[5].indexOf("</p>"));
		       //System.out.println(host);
		       String []hs=host.split("</a>");
		       for(int i=0;i<hs.length-1;i++){
		          // System.out.println(hs[i]);
		           String hh=hs[i].substring(hs[i].indexOf("href")+6,hs[i].indexOf("\">"));
		           String hn=hs[i].substring(hs[i].indexOf("\">")+2);//主持人链接
		           out.println("<des>"+hn+"</des>");
		       }
		       out.write("<br><hr width='100%' height='5px'>");
		       
		        //爬取剧集
		        Elements es=doc.getElementsByClass("juji-main-wrap");
		        String ses=es.toString();
		        String[] s9=ses.split("<li title");
		        for(int i=1;i<s9.length;i++){
			         //期别
			         String stage=s9[i].substring(2,s9[i].indexOf("\" class"));
			         //链接
			         String pu=s9[i].substring(s9[i].indexOf("<a href=\"")+9,s9[i].indexOf("\" data-daochu"));
			         //该期的img
			         //System.out.println(s9[i].substring(s9[i].indexOf("data-src=\"")+10,s9[i].indexOf("\" alt=")));
			         //期别日期
			         String date=s9[i].substring(s9[i].indexOf("<span class=\"w-newfigure-hint\">")+31,s9[i].indexOf("</span>"));

			         out.println("<a href='/player.jsp?type="+type+"&url="+url+"&href="+pu+"' title='"+stage+"'>"+date+"</a><br>");
		             
		        }
		   }
		   
		   //简介
		   String desc=ss.substring(ss.indexOf("<p class=\"item-desc js-close-wrap\" style=\"display:none;\">")+73,ss.indexOf("<a href=\"#\" class=\"js-close btn\">"));
           out.println("</div></div><br>简介:<span>"+desc+"</span>");
         
         }
       %>
       <br><br><br>
       <hr width='95%' height='5px'>
       <%@include file="footer.jsp" %>
       
       <script>
           function fun(){
                 document.title="正在播放:<%=title%>-eyspa影院";
           }   
           window.onload=fun;   
       </script>
  </body>
</html>
