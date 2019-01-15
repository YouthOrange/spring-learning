package cl.learning.mall.service;

import cl.learning.mall.domain.Record;
import cl.learning.mall.dto.RecordDto;
import cl.learning.mall.repository.RecordRepository;
import cl.learning.mall.util.MallUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : 常亮
 * @Date : 11:11 2019-01-14
 * @Description :
 */
@Slf4j
@Service
public class RecordService {
    @Autowired
    private RecordRepository recordRepository;


    public Long saveRecord(Record record) {
        return recordRepository.save(record).getId();
    }

    public List<RecordDto> getRecordByVipId(Long vipId) {
        List<Record> recordList = recordRepository.findByVipIdOrderByCreateAt(vipId);

        log.info("recordList:{}", JSON.toJSONString(recordList));
        return MallUtil.recordListTransfer2Dto(recordList);
    }
}
