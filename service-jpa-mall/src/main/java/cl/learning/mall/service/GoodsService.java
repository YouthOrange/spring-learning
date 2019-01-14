package cl.learning.mall.service;

import cl.learning.base.dto.Page;
import cl.learning.base.dto.Response;
import cl.learning.mall.domain.Goods;
import cl.learning.mall.dto.GoodsQueryDto;
import cl.learning.mall.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : 常亮
 * @Date : 15:58 2019-01-14
 * @Description :
 */
@Service
public class GoodsService {
    @Autowired
    private GoodsRepository goodsRepository;

    public Response<Page<Goods>> findAll(GoodsQueryDto queryDto) {
        List<Goods> goodsList;
        if (queryDto == null) {
            goodsList = goodsRepository.findAll();
        } else {
            goodsList = goodsRepository.findByNameContainsOrTypeOrderById(queryDto.getKey(), queryDto.getType());
        }
        Page<Goods> page = new Page<>();
        int start = (queryDto.getPageNo() - 1) * queryDto.getPageSize();
        int end = Math.min(start + queryDto.getPageSize(), goodsList.size());
        page.setTotal(goodsList.size());
        page.setData(goodsList.subList(start, end));
        return Response.ok(page);

    }
}
