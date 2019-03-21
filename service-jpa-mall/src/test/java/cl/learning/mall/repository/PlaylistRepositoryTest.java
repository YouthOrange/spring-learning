package cl.learning.mall.repository;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author : 常亮
 * @Date : 17:47 2019-03-01
 * @Description :
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PlaylistRepositoryTest {
    @Autowired
    private PlaylistRepository playlistRepository;
    @Test
    public void test1(){
        List<Long> idList = playlistRepository.filterByTitle();
        System.out.println(idList.size());
        System.out.println(JSON.toJSONString(idList));
    }

}