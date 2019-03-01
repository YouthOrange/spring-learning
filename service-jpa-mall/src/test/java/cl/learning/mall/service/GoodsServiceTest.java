package cl.learning.mall.service;

import cl.learning.base.dto.Paging;
import cl.learning.base.dto.Response;
import cl.learning.mall.ServiceJpaMallApplication;
import cl.learning.mall.domain.Goods;
import cl.learning.mall.dto.GoodsDto;
import cl.learning.mall.dto.GoodsQueryDto;
import cl.learning.mall.repository.GoodsRepository;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author : 常亮
 * @Date : 10:22 2019-01-17
 * @Description :
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ServiceJpaMallApplication.class, GoodsServiceTest.class})
public class GoodsServiceTest {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    private static final Jedis jedis = new Jedis("localhost");

    @Test
    public void test1() {
        int no = 1;
        List<Goods> goodsList = new ArrayList<>();
        while (no < 100) {
            Goods goods = new Goods();
            goods.setName("xiaoniu" + no++);
            goods.setCount(10000);
            goods.setBrand("xiaoniu");
            goods.setOrigin("chengdu");
            goods.setPrice(1999.0);
            goods.setType("3");
            goodsList.add(goods);
        }
        goodsService.saveGoods(goodsList);
    }

    @Test
    public void test2() {
        GoodsQueryDto queryDto = new GoodsQueryDto();
        queryDto.setSortBy("price");
        queryDto.setPageSize(20);
        System.out.println(queryDto.toString());
        Response<Paging<GoodsDto>> response = goodsService.findAll(queryDto);
        System.out.println(JSON.toJSONString(response));
        mongoTemplate.insertAll(response.getResult().getData());
    }

    @Test
    public void test3() {
        Map<String, List<Goods>> map = goodsService.findAllGroupByBrand().getResult();
        try {
            long start = System.currentTimeMillis();
            Pipeline pipeline = jedis.pipelined();
            map.forEach((k, v) -> pipeline.set(k, JSON.toJSONString(v)));
            pipeline.sync();
            System.out.println("pipeline cost:" + (System.currentTimeMillis() - start) + "ms");
            pipeline.close();
        } catch (Exception e) {
            System.out.println("pipeline error,cause by:{}" + Throwables.getStackTraceAsString(e));
            long start = System.currentTimeMillis();
            map.forEach((k, v) -> jedis.set(k, JSON.toJSONString(v)));
            System.out.println("pipeline cost:" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    @Test
    public void test4() {
        Map<String, List<Goods>> map = goodsService.findAllGroupByBrand().getResult();
        long start = System.currentTimeMillis();
        map.forEach((k, v) -> jedis.set(k, JSON.toJSONString(v)));
        System.out.println("jedis cost:" + (System.currentTimeMillis() - start) + "ms");
    }

    @Test
    public void test5() {
        List<Goods> list = goodsRepository.findAll();
        list.forEach(goods -> goods.setId(null));
        List<Goods> newlist = new ArrayList<>();
        int i = 0;
        long start = System.currentTimeMillis();
        while (i < 100) {
            i++;
            newlist.addAll(list);
        }
        Integer count = goodsService.saveGoods(newlist).getResult();
        System.out.println("cost" + (System.currentTimeMillis() - start));
        System.out.println(count);
    }

    @Test
    public void test6() {
        GoodsQueryDto queryDto = new GoodsQueryDto();
        queryDto.setSortBy("price");
        queryDto.setPageSize(1000);
        queryDto.setPageNo(0);
        Response<Paging<GoodsDto>> response = goodsService.findAll(queryDto);
//        System.out.println(JSON.toJSONString(response));
        System.out.println(response.getResult().getTotalElements());
        List<GoodsDto> goodsList = response.getResult().getData();
        long start1 = System.currentTimeMillis();
        goodsList.forEach(goods -> {
            if (goods.getId() == 2350L) {
                System.out.println(JSON.toJSONString(goods));
                System.out.println("循环耗时：" + (System.currentTimeMillis() - start1) + "ms");
            }
        });
        long start2 = System.currentTimeMillis();
        Map<Long, GoodsDto> goodsMap = goodsList.stream().collect(Collectors.toMap(GoodsDto::getId, goods -> goods));
        System.out.println(JSON.toJSONString(goodsMap.get(2350L)));
        GoodsDto goodsDto = goodsMap.get(2350L);
        if (null != goodsDto) {

        }
        System.out.println("stream 耗时：" + (System.currentTimeMillis() - start2) + "ms");

    }

    @Test
    public void test7(){
        List<Goods> goodsList = goodsRepository.findAll();
        mongoTemplate.insertAll(goodsList);
    }
    @Test
    public void test8(){
        Query query = new Query();
        Criteria criteria = Criteria.where("brand").is("卫龙");
        query.addCriteria(criteria);
        List<Goods> goodsDtoList = mongoTemplate.find(query,Goods.class);
        System.out.println(JSON.toJSONString(goodsDtoList));
    }
}