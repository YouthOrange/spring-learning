package cl.learning.ribbon.controller;

import cl.learning.ribbon.dto.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.List;

/**
 * @Author : 常亮
 * @Date : 17:10 2019-01-09
 * @Description :
 */
@RestController
@RequestMapping(value = "/ribbon")
public class PersonController {
    @Autowired
    RestTemplate restTemplate;

    @PostMapping(value = "/save")
    public Long savePerson(@RequestBody(required = false) PersonDto person) throws URISyntaxException {
        if (person == null) {
            return -1L;
        }
        PersonDto personDto = restTemplate.postForObject("http://SERVICE-ES/person/save", person, PersonDto.class);
        if (personDto == null) {
            return -1L;
        }
        return personDto.getId();
    }

    @GetMapping(value = "/findAll")
    public List findAll(@RequestParam(required = false) String address,
                        @RequestParam(required = false) Integer age,
                        @RequestParam(required = false) Integer minAge) {
        if (address != null) {

            return restTemplate.getForEntity("http://SERVICE-ES/person/findAll?address=" + address, List.class).getBody();
        }
        if (age != null) {
            return restTemplate.getForEntity("http://SERVICE-ES/person/findAll?age=" + age, List.class).getBody();
        }
        if (minAge != null) {
            return restTemplate.getForEntity("http://SERVICE-ES/person/findAll?minAge=" + minAge, List.class).getBody();
        }
        return restTemplate.getForEntity("http://SERVICE-ES/person/findAll", List.class).getBody();

    }
}
