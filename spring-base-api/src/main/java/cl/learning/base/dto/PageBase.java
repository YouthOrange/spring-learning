package cl.learning.base.dto;

import lombok.Data;

/**
 * @Author : 常亮
 * @Date : 16:55 2019-01-14
 * @Description :
 */
@Data
public class PageBase {
    Integer pageSize = 10;
    Integer pageNo = 0;
}
