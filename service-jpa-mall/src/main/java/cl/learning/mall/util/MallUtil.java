package cl.learning.mall.util;

import cl.learning.mall.domain.Record;
import cl.learning.mall.dto.RecordDto;
import lombok.experimental.UtilityClass;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author : 常亮
 * @Date : 11:45 2019-01-14
 * @Description :
 */
@UtilityClass
public class MallUtil {
    public static RecordDto recordTransfer2Dto(Record record) {
        RecordDto recordDto = new RecordDto();
        BeanUtils.copyProperties(record, recordDto);
        Date date = record.getCreateAt();
        recordDto.setCreateAt(date.toString());
        return recordDto;
    }

    public static List<RecordDto> recordListTransfer2Dto(List<Record> recordList) {
        return recordList.stream().map(record -> recordTransfer2Dto(record)).collect(Collectors.toList());
    }

    public static Integer countIntegral(Double money) {
        if (money < 100) {
            return (int) Math.floor(money * 0.05);
        } else if (money < 1000) {
            return (int) Math.floor(money * 0.10);
        } else if (money < 10000) {
            return (int) Math.floor(money * 0.15);
        } else {
            return (int) Math.floor(money * 0.20);
        }
    }
}
