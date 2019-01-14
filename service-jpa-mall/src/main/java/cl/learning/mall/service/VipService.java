package cl.learning.mall.service;

import cl.learning.mall.domain.Vip;
import cl.learning.mall.dto.RecordDto;
import cl.learning.mall.dto.VipDto;
import cl.learning.mall.repository.RecordRepository;
import cl.learning.mall.repository.VipRepository;
import cl.learning.mall.util.MallUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : 常亮
 * @Date : 10:54 2019-01-10
 * @Description :
 */
@Slf4j
@Service
public class VipService {
    @Autowired
    private VipRepository vipRepository;
    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private RecordService recordService;

    public Long saveVip(VipDto vipDto) {
        vipDto.setVipId(idBuilder());
        log.info("create vip,info:{}", vipDto.toString());
        Vip vip = new Vip();
        BeanUtils.copyProperties(vipDto, vip);
        vip = vipRepository.save(vip);
        return vip.getVipId();
    }

    public VipDto findByVipId(Long vipId) {
        Vip vip = vipRepository.findByVipId(vipId);
        List<RecordDto> recordList = recordService.getRecordByVipId(vipId);
        log.info("recordList:{}", JSON.toJSONString(recordList));
        VipDto vipDto = new VipDto();
        BeanUtils.copyProperties(vip, vipDto);
        vipDto.setRecordList(recordList);
        return vipDto;
    }

    public Long idBuilder() {
        Long time = System.currentTimeMillis();
        Integer id = time.hashCode();
        return id.longValue();
    }

    public Double addMoney(Long vipId, Double money) {
        Vip vip = vipRepository.findByVipId(vipId);
        vip.setTotalMoney(vip.getTotalMoney() + money);
        return vipRepository.save(vip).getTotalMoney();
    }

    public Vip addIntegralAndMoney(Long vipId, Double money) {
        Integer integral = MallUtil.countIntegral(money);
        Vip vip = vipRepository.findByVipId(vipId);
        vip.setIntegral(vip.getIntegral() + integral);
        vip.setTotalMoney(vip.getTotalMoney() + money);
        return vipRepository.save(vip);
    }
}
