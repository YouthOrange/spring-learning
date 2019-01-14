package cl.learning.es.controller;

import cl.learning.es.domain.Person;
import cl.learning.es.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author : 常亮
 * @Date : 15:08 2019-01-09
 * @Description :
 */
@RestController
@RequestMapping(value = "/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @PostMapping(value = "/save")
    public Long savePerson(@RequestBody(required = false) Person person) {
        if (person == null) {
            return -1L;
        }
        return personService.savePerson(person);
    }

    @GetMapping(value = "/findAll")
    public List<Person> findAll(@RequestParam(required = false) String address,
                                @RequestParam(required = false) Integer age,
                                @RequestParam(required = false) Integer minAge) {
        List<Person> personList = null;
        if (address != null) {
            personList = personService.findAll(address);
        } else if (age != null) {
            personList = personService.findAll(age);
        } else if (minAge != null) {
            personList = personService.findByAgeAfter(minAge);
        } else {
            personList = personService.findAll();
        }
        return personList;
    }
}
