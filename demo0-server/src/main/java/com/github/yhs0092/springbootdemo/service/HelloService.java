package com.github.yhs0092.springbootdemo.service;

import java.util.List;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@RestController
@RestSchema(schemaId = "helloService")
@RequestMapping(path = "hello")
public class HelloService {
  private static final Logger LOGGER = LoggerFactory.getLogger(HelloService.class);

  public HelloService() {
    System.out.println("Freezing!!!");
  }

  @Autowired
  private ConfigurableEnvironment environment;

  @Autowired
  private ApplicationContext ctx;

  @Value("${property.test0}")
  private String test0;

  @Value("${property.test1}")
  private String test1;

  @Value("${property.test2}")
  private String test2;

  @Value("${property.test3}")
  private String test3;

  @GetMapping("/sayHello")
  public String sayHello(@RequestParam(value = "name", required = false) String name) {
    LOGGER.info("sayHello is called, name = [{}]", name);
    test();
    return "Hello, " + name + "-test0=[" + test0 + "], test1=[" + test1 + "], test2=[" + test2 + "], test3=[" + test3
        + "]";
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

  public void test() {
    ConfigurableApplicationContext configurableApplicationContext = null;
    environment.getProperty("property.test");
    MutablePropertySources propertySources = environment.getPropertySources();
    for (PropertySource<?> propertySource : propertySources) {

      // do something to get the configurations!
    }
  }
}
