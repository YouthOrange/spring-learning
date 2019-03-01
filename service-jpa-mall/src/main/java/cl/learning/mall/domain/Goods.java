package cl.learning.mall.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.index.CompoundIndex;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author : 常亮
 * @Date : 14:28 2019-01-14
 * @Description :
 */
@Data
@Entity
@CompoundIndex(name = "brand_price", def = "{'brand':1,'price':1}")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String brand;
    private Double price;
    private Integer count;
    private String origin;
    private String type;
    private String title;
    private String shop;
    private Date createAt;
    private Date updateAt;
}
