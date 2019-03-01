package cl.learning.es.service;

import cl.learning.es.domain.Person;
import cl.learning.es.repository.PersonRepository;
import cl.learning.es.util.PersonUtil;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @Author : 常亮
 * @Date : 14:59 2019-01-09
 * @Description :
 */
@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public Long savePerson(Person person) {
        Long id = PersonUtil.idBuilder();
        person.setId(id);
        Long time = System.currentTimeMillis();
        person.setCreateAt(new Date(time));
        person.setUpdateAt(new Date(time));
        personRepository.save(person);
        return id;
    }

    public List<Person> findAll() {
        Iterable<Person> persons = personRepository.findAll();
        List<Person> personList = Lists.newArrayList(persons);
        Collections.sort(personList);
        return personList;
    }

    public List<Person> findAll(String address) {
        return personRepository.findByAddressOrderById(address);
    }

    public List<Person> findAll(Integer age) {
        return personRepository.findByAgeOrderById(age);
    }

    public List<Person> findByAgeAfter(Integer minAge) {
        return personRepository.findByAgeAfterOrderById(minAge);
    }

}
