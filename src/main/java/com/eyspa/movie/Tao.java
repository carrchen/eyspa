//package com.eyspa.movie;
//
//public class Tao
//{
//    public static void main(final String[] args) throws Exception {
//        final String url = "https://eco.taobao.com/router/rest";
//        final String key = "24936018";
//        final String secret = "e8dccee0ce5e8a549630eb79a670d60f";
//        queryGoods(url, key, secret);
//    }
//
//    private static void queryGoods(final String url, final String key, final String secret) throws Exception {
//        final TaobaoClient client = (TaobaoClient)new DefaultTaobaoClient(url, key, secret);
//        final TbkItemGetRequest req = new TbkItemGetRequest();
//        req.setFields("num_iid,title,pict_url,small_images,reserve_price,zk_final_price,user_type,provcity,item_url,seller_id,volume,nick");
//        req.setQ("女装");
//        req.setCat("16");
//        final TbkItemGetResponse response = (TbkItemGetResponse)client.execute((TaobaoRequest)req);
//        final String r = response.getBody();
//        System.out.println(r);
//        final String[] t = r.split("item_url");
//        for (int i = 1; i < t.length; ++i) {
//            System.out.println("-----------------------------------------------------------------------");
//            System.out.println(t[i]);
//            String ur = t[i].substring(3, t[i].indexOf("\",\""));
//            ur = ur.replace("\\", "");
//            System.out.println(ur);
//            final String nick = t[i].substring(t[i].indexOf("nick") + 7, t[i].indexOf("num_iid") - 3);
//            System.out.println(nick);
//            final String num_iid = t[i].substring(t[i].indexOf("num_iid") + 9, t[i].indexOf("pict_url") - 2);
//            System.out.println(num_iid);
//            String pict_url = t[i].substring(t[i].indexOf("pict_url") + 11, t[i].indexOf("provcity") - 3);
//            pict_url = pict_url.replace("\\", "");
//            System.out.println(pict_url);
//            final String provcity = t[i].substring(t[i].indexOf("provcity") + 11, t[i].indexOf("reserve_price") - 3);
//            System.out.println(provcity);
//            final String reserve_price = t[i].substring(t[i].indexOf("reserve_price") + 16, t[i].indexOf("seller_id") - 3);
//            System.out.println(reserve_price);
//            final String seller_id = t[i].substring(t[i].indexOf("seller_id") + 11, t[i].indexOf("small_images") - 2);
//            System.out.println(seller_id);
//            final String small_images = t[i].substring(t[i].indexOf("small_images") + 14, t[i].indexOf("title") - 2);
//            System.out.println(small_images);
//            final String title = t[i].substring(t[i].indexOf("title") + 8, t[i].indexOf("user_type") - 3);
//            System.out.println(title);
//            final String user_type = t[i].substring(t[i].indexOf("user_type") + 11, t[i].indexOf("volume") - 2);
//            System.out.println(user_type);
//            final String volume = t[i].substring(t[i].indexOf("volume") + 8, t[i].indexOf("zk_final_price") - 2);
//            System.out.println(volume);
//            final String zk_final_price = t[i].substring(t[i].indexOf("zk_final_price") + 17);
//            System.out.println(zk_final_price);
//        }
//    }
//}
