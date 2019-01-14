package cl.learning.mall.dto;

import lombok.Data;

/**
 * @Author : 常亮
 * @Date : 11:39 2019-01-14
 * @Description :
 */
@Data
public class RecordDto {
    Long id;
    Long vipId;
    Double money;
    String goods;
    String createAt;
}
