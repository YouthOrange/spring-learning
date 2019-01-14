package cl.learning.ribbon.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Author : 常亮
 * @Date : 17:12 2019-01-09
 * @Description :
 */

@Data
public class PersonDto implements Comparable<PersonDto> {
    private Long id;
    private String name;
    private String sex;
    private Integer age;
    private String address;
    private String job;
    private Date createAt;
    private Date updateAt;

    @Override
    public int compareTo(PersonDto person) {
        if (this.id > person.id) {
            return 1;
        } else if (this.id.equals(person.id)) {
            return 0;
        } else {
            return -1;
        }
    }
}
