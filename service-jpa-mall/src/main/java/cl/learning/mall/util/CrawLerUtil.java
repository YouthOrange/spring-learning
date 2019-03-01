package cl.learning.mall.util;

import cl.learning.mall.domain.Goods;
import cl.learning.mall.domain.Music;
import cl.learning.mall.domain.Playlist;
import cl.learning.mall.enums.GoodsBigTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : 常亮
 * @Date : 12:02 2019-01-31
 * @Description :
 */
@Slf4j
public class CrawLerUtil {
    public static List<Goods> searchFromJD(String goodsName, int page) throws IOException {
        String keyword = URLEncoder.encode(goodsName, "utf-8");
        String url = "https://search.jd.com/Search?enc=utf-8&page=" + (page * 2 - 1) + "&keyword=" + keyword;
        log.info("search start,url:{}", url);
        List<Goods> goodsList = new ArrayList<>();
        Document document = Jsoup.connect(url).get();
        Elements uls = document.select("ul[class='gl-warp clearfix']");
        Elements lis = uls.select("li[class='gl-item']");
        try {
            if (!CollectionUtils.isEmpty(lis)) {
                lis.forEach(element -> {
                    if (!"广告".equals(element.select("span[class='p-promo-flag']").text())) {
                        Goods goods = new Goods();
                        goods.setTitle(element.select("div[class='p-name p-name-type-2']").select("em").text());
                        if (element.select("div[class='p-price']").select("i").text().length() > 0) {
                            goods.setPrice(Double.parseDouble(element.select("div[class='p-price']").select("i").text()));
                        }
                        goods.setShop(element.select("div[class='p-shop']").select("a").text());
                        goods.setBrand(getBrand(goods.getShop()));
                        goods.setType(GoodsBigTypeEnum.getGoodsBigTypeEnum(goodsName).getType());
                        goods.setCount(10000);
                        goods.setName(getName(goods.getTitle()));
                        goodsList.add(goods);
                    }
                });
            }
        } catch (Exception e) {

        }

        return goodsList;
    }

    public static List<Playlist> searchPlayListFrom163(String keyword, int page) throws IOException {
        List<Playlist> playlists = new ArrayList<>();
        keyword = URLEncoder.encode(keyword, "utf-8");
        String url = "https://music.163.com/discover/playlist/?order=hot&cat=" + keyword + "&limit=35&offset=" + ((page - 1) * 35);
        System.out.println(url);
        Document document = Jsoup.connect(url).get();
        Elements uls = document.select("div[class='g-bd']").select("div[class='g-wrap p-pl f-pr']").select("ul[class='m-cvrlst f-cb']");
        Elements lis = uls.select("li");
        if (!CollectionUtils.isEmpty(lis)) {
            lis.forEach(element -> {
                Playlist playlist = new Playlist();
                playlist.setTitle(element.select("p[class='dec']").select("a").text());
                String count = element.select("div[class='u-cover u-cover-1']").select("div[class='bottom']").select("span[class='nb']").text();
                if (count.endsWith("万")) {
                    count = count.substring(0, count.length() - 1) + "0000";
                }
                if (count.endsWith("亿")) {
                    count = count.substring(0, count.length() - 1) + "00000000";
                }
                playlist.setViewCount(Long.parseLong(count));
                playlist.setAuthor(element.select("a[class='nm nm-icn f-thide s-fc3']").text());
                playlists.add(playlist);
            });
        }
        return playlists;
    }

    public static Music searchMusicFrom163(String url) throws IOException {
        Music music = new Music();
        Document document = Jsoup.connect(url).get();
        document.select("div[class='g-bd4 f-cb']").select("div[class='g-mn4']").select("div[class='g-mn4c']").select("div[class='g-wrap6']").select("div[class='m-lycifo']").select("div[class='f-cb]");
        return music;
    }


    public static String getBrand(String shop) {
        StringBuilder str = new StringBuilder(shop);
        return filter(str, "旗舰店", "京东", "产品", "手机", "官方", "服饰", "男装", "鞋靴", "服装", "专卖店", "专营店");
    }

    public static String getName(String title) {
        title = title.replaceFirst("推荐>>", "");
        StringBuilder title1 = new StringBuilder(title);
        int left = title1.indexOf("【");
        int right = title1.indexOf("】");
        if (left >= 0 && right > left) {
            title1.replace(left, right + 1, "");
        }
        left = title1.indexOf("（");
        right = title1.indexOf("）");
        if (left >= 0 && right > left) {
            title1.replace(left, right + 1, "");
        }
        left = title1.indexOf("[");
        right = title1.indexOf("]");
        if (left >= 0 && right > left) {
            title1.replace(left, right + 1, "");
        }
        left = title1.indexOf("(");
        right = title1.indexOf(")");
        if (left >= 0 && right > left) {
            title1.replace(left, right + 1, "");
        }

        String[] strings = title1.toString().split(" ");
        StringBuilder name = new StringBuilder();
        for (String s : strings) {
            if (!"Apple".equals(s)) {
                name.append(s).append(" ");
            }
            if (name.length() > 10) {
                return name.toString();
            }
        }
        return name.toString();
    }

    public static String filter(StringBuilder param, String... strings) {
        for (String s : strings) {
            int index = param.indexOf(s);
            if (index > 0) {
                param.setLength(index);
            }
        }
        return param.toString();
    }
}
