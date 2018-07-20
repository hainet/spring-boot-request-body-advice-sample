package com.hainet.spring.boot.request.body.advice.sample.web;

import com.hainet.spring.boot.request.body.advice.sample.domain.Person;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @PostMapping("/")
    public String post(@RequestBody final Person person) {
        return person.toString();
    }
}
