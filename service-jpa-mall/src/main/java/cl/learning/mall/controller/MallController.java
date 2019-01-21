package cl.learning.mall.controller;

import cl.learning.base.dto.Paging;
import cl.learning.base.dto.Response;
import cl.learning.mall.domain.Goods;
import cl.learning.mall.domain.Record;
import cl.learning.mall.dto.GoodsDto;
import cl.learning.mall.dto.GoodsQueryDto;
import cl.learning.mall.dto.VipDto;
import cl.learning.mall.service.GoodsService;
import cl.learning.mall.service.RecordService;
import cl.learning.mall.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : 常亮
 * @Date : 11:03 2019-01-10
 * @Description :
 */

@RestController
@RequestMapping(value = "/mall")
public class MallController {
    @Autowired
    private VipService vipService;

    @Autowired
    private RecordService recordService;

    @Autowired
    private GoodsService goodsService;

    @GetMapping(value = "/vip/get")
    public Response<VipDto> findByVipId(Long vipId) {
        return vipService.findByVipId(vipId);
    }

    @PostMapping(value = "/vip/save")
    public Long saveVip(@RequestBody VipDto vip) {
        return vipService.saveVip(vip);
    }

    @PostMapping(value = "/record/save")
    public Long saveRecord(@RequestBody Record record) {
        vipService.addIntegralAndMoney(record.getVipId(), record.getMoney());
        return recordService.saveRecord(record);
    }

    @GetMapping(value = "/goods/find")
    public Response<Paging<GoodsDto>> findAll(@RequestParam(required = false) GoodsQueryDto queryDto) {
        return goodsService.findAll(queryDto);
    }

    @PostMapping(value = "/goods/save")
    public Response<Goods> saveGoods(@RequestBody Goods goods) {
        return goodsService.saveGoods(goods);
    }
}
