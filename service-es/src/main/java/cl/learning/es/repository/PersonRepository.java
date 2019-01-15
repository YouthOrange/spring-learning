package cl.learning.es.repository;

import cl.learning.es.domain.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author : 常亮
 * @Date : 14:57 2019-01-09
 * @Description :
 */
@Repository
public interface PersonRepository extends ElasticsearchRepository<Person, Long> {

    List<Person> findByAddressOrderById(String address);

    List<Person> findByAgeOrderById(Integer age);

    List<Person> findByAgeAfterOrderById(Integer minAge);


}
