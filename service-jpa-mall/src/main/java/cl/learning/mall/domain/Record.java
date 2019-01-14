package cl.learning.mall.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


/**
 * @Author : 常亮
 * @Date : 10:02 2019-01-10
 * @Description :
 */
@Data
@Entity
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long vipId;
    Double money;
    String goods;
    @Column(name = "create_at", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date createAt;
}
