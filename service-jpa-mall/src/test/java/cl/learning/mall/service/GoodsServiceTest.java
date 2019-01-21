package cl.learning.mall.service;

import cl.learning.base.dto.Response;
import cl.learning.mall.ServiceJpaMallApplication;
import cl.learning.mall.domain.Goods;
import cl.learning.mall.dto.GoodsQueryDto;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author : 常亮
 * @Date : 10:22 2019-01-17
 * @Description :
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ServiceJpaMallApplication.class, GoodsServiceTest.class})
public class GoodsServiceTest {
    @Autowired
    private GoodsService goodsService;

    @Test
    public void test1() {
        int no = 1;
        while (no < 7) {
            Goods goods = new Goods();
            goods.setName("iphone" + no++);
            goods.setCount(10000);
            goods.setBrand("apple");
            goods.setOrigin("zhengzhou");
            goods.setPrice(4999.0);
            goods.setType("3");
            goodsService.saveGoods(goods);
        }
    }

    @Test
    public void test2() {
        GoodsQueryDto queryDto = new GoodsQueryDto();
        queryDto.setSortBy("price");
        queryDto.setPageSize(20);
        System.out.println(queryDto.toString());
        Long start = System.currentTimeMillis();
        Response response = goodsService.findAll(queryDto);
        System.out.println(System.currentTimeMillis()-start);
        System.out.println(JSON.toJSONString(response));
    }


}