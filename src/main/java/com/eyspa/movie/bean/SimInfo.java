package com.eyspa.movie.bean;

public class SimInfo {

    public String url;
    public String img;
    public String vname;
    private String type;

    public String getType()
    {
        return this.type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getUrl()
    {
        return this.url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getImg()
    {
        return this.img;
    }

    public void setImg(String img)
    {
        this.img = img;
    }

    public String getVname()
    {
        return this.vname;
    }

    public void setVname(String vname)
    {
        this.vname = vname;
    }
}
