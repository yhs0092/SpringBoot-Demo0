package com.github.yhs0092.springbootdemo.service;

import java.util.List;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@RestSchema(schemaId = "helloService")
@RequestMapping(path = "hello")
public class HelloService {
  private static final Logger LOGGER = LoggerFactory.getLogger(HelloService.class);

  @GetMapping("/sayHello")
  public String sayHello(@RequestParam(value = "name", required = false) String name) {
    LOGGER.info("sayHello is called, name = [{}]", name);
    return "Hello, " + name;
  }

  @PutMapping("/calc")
  public Double calculate(@RequestParam("x") double x, @RequestParam("y") double y,
      @RequestHeader("oper") String operator) {
    LOGGER.info("calc is called, x = [{}], y = [{}], oper = [{}]", x, y, operator);
    switch (operator) {
      case "+":
        return x + y;
      case "-":
        return x - y;
      case "*":
        return x * y;
      case "/":
        return x / y;
      default:
        return null;
    }
  }

  @PostMapping("/postTest")
  public String postTest(@RequestBody Person person) {
    LOGGER.info("postTest is called, person = [{}]", person);
    return person.toString();
  }

  @DeleteMapping("/deleteTest")
  public String deleteTest(@RequestBody List<Person> personList) {
    LOGGER.info("deleteTest is called, personList = [{}]", personList);
    return personList.toString();
  }
}
