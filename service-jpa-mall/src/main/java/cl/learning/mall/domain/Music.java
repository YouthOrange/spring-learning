package cl.learning.mall.domain;

import lombok.Data;

/**
 * @Author : 常亮
 * @Date : 17:11 2019-01-31
 * @Description :
 */
@Data
public class Music {
    Long id;
    String name;
    String singer;
    String album;
    Long comments;
    String imgUrl;
    String playUrl;
}
