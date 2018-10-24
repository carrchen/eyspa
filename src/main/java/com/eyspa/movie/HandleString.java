package com.eyspa.movie;


public class HandleString
{
    public static void main(String[] args)
    {
        String s = "<a class='js-tongjic' href='/m/gafjY0L4S0X5UB.html'> ";
        String href = s.substring(s.indexOf("href='") + 6, s.indexOf("'>"));
        System.out.println(href);

        System.out.println(getContent(s, "href", "'>"));

        String s1 = "<img src='https://p.ssl.qhimg.com/t01575b9ac4db91d0eb.jpg'>";
        String src = s1.substring(s1.indexOf("src='") + 5, s1.indexOf("'>"));
        System.out.print(src);
    }

    public static String getHref(String s)
    {
        if (s.contains("href"))
        {
            StringBuilder sb = new StringBuilder(s);

            return sb.delete(0, 28).delete(22, 26).toString();
        }
        return null;
    }

    public static String getSrc(String s)
    {
        if (s.contains("src"))
        {
            StringBuilder sb = new StringBuilder(s);

            return sb.delete(0, 14).delete(47, 49).toString();
        }
        return null;
    }

    public String clearBla(String old)
    {
        return null;
    }

    public static String getContent(String s, String a, String b)
    {
        String newS = null;
        if ((s.contains(a)) && (s.contains(b)))
        {
            int i = s.indexOf(a);
            int j = s.indexOf(b);
            newS = s.substring(i + 6, j);
        }
        return newS;
    }
}