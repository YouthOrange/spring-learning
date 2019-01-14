package cl.learning.mall.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Author : 常亮
 * @Date : 10:00 2019-01-10
 * @Description :
 */
@Data
@Entity
public class Vip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long vipId;

    private String name;

    private String tel;

    private Integer integral;

    private Double totalMoney;

}
