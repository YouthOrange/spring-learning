package cl.learning.mall.service;

import cl.learning.mall.domain.Goods;
import cl.learning.mall.domain.Music;
import cl.learning.mall.domain.Playlist;
import cl.learning.mall.util.CrawLerUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author : 常亮
 * @Date : 11:39 2019-01-31
 * @Description :
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CrawLerTest {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private MusicService musicService;

    @Test
    public void test1() {
        try {
            List<Goods> goodsList = new ArrayList<>();
            int page = 100;
            while (page > 0) {
                goodsList.addAll(CrawLerUtil.searchFromJD("民谣", page));
                page--;
            }
            goodsList = goodsList.stream().filter(goods -> StringUtils.isNotEmpty(goods.getBrand())).collect(Collectors.toList());
            System.out.println(goodsList.size());
            System.out.println(JSON.toJSONString(goodsService.saveGoods(goodsList)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        try {
            List<Playlist> playlistList = new ArrayList<>();
            int page =1;
            while (page<39){
                playlistList.addAll(CrawLerUtil.searchPlayListFrom163("粤语",page));
                page++;
            }
            System.out.println(JSON.toJSONString(playlistList));
            System.out.println(musicService.savePlayList(playlistList));
        } catch (Exception e) {
            log.error("musicService.savePlayList error,cause by:{}", Throwables.getStackTraceAsString(e));

        }
    }
    @Test
    public void test3(){
        try {
            Music music = CrawLerUtil.searchMusicFrom163("https://music.163.com/song?id=65533");
            System.out.println(JSON.toJSONString(music));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
