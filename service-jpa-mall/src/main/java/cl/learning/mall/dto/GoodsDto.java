package cl.learning.mall.dto;

import lombok.Data;

/**
 * @Author : 常亮
 * @Date : 13:48 2019-01-17
 * @Description :
 */
@Data
public class GoodsDto {
    private Long id;
    private String name;
    private String brand;
    private Double price;
    private Integer count;
    private String origin;
    private String type;
    private String createAt;
    private String updateAt;
}
