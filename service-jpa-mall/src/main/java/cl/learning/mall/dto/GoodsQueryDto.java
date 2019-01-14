package cl.learning.mall.dto;

import cl.learning.base.dto.PageBase;
import lombok.Data;

/**
 * @Author : 常亮
 * @Date : 16:02 2019-01-14
 * @Description :
 */
@Data
public class GoodsQueryDto extends PageBase {
    String type;
    String key;
}
