package com.eyspa.movie.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class PageController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/footer")
    public String footer(){
        return "footer";
    }

    @RequestMapping("/goods")
    public String goods(){
        return "goods";
    }

    @RequestMapping("/indexYouku")
    public String indexYouku(){
        return "indexYouku";
    }

    @RequestMapping("/player")
    public ModelAndView player(String type, String url,String href){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("player");
        Map si = new HashMap();
        si.put("url",url);
        si.put("type",type);
        si.put("href",href);
        mv.addObject(si);
        return mv;
    }

    @RequestMapping("/playerBlue")
    public String playerBlue(){
        return "playerBlue";
    }

    @RequestMapping("/search")
    public String search(){
        return "search";
    }

    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    @RequestMapping("/test1")
    public String test1(){
        return "test1";
    }



}
