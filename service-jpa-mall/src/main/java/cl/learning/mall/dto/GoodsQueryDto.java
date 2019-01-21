package cl.learning.mall.dto;

import cl.learning.base.dto.PageBase;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author : 常亮
 * @Date : 16:02 2019-01-14
 * @Description :
 */
@Data
public class GoodsQueryDto extends PageBase implements Serializable {
    private static final long serialVersionUID = -6892879815983372083L;
    String type;
    String key;
    String origin;
    String brand;
    String sortBy;
    Double minPrice;
    Double maxPrice;
}
