package cl.learning.es.domain;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

/**
 * @Author : 常亮
 * @Date : 14:52 2019-01-09
 * @Description :
 */
@Document(indexName = "person", type = "index")
@Data
public class Person implements Comparable<Person> {
    private Long id;
    private String name;
    private String sex;
    private Integer age;
    private String address;
    private String job;
    private Date createAt;
    private Date updateAt;

    @Override
    public int compareTo(Person person) {
        if (this.id > person.id) {
            return 1;
        } else if (this.id.equals(person.id)) {
            return 0;
        } else {
            return -1;
        }
    }
}
