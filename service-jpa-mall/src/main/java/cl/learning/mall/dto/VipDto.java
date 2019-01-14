package cl.learning.mall.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author : 常亮
 * @Date : 10:45 2019-01-14
 * @Description :
 */
@Data
public class VipDto implements Serializable {
    private static final long serialVersionUID = 6400437360634434232L;
    private Long id;

    private Long vipId;

    private String name;

    private String tel;

    private Integer integral;

    private Double totalMoney;

    private List<RecordDto> recordList;
}
