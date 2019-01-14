package cl.learning.base.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author : 常亮
 * @Date : 16:50 2019-01-14
 * @Description :
 */
@Data
public class Page<T> implements Serializable {
    private static final long serialVersionUID = 2290532973176755467L;
    List<T> data;
    Integer total;
}
