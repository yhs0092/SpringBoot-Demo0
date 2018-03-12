package com.github.yhs0092.springbootdemo.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/clientConsole")
public class ClientConsole {
  private static final Logger LOGGER = LoggerFactory.getLogger(ClientConsole.class);

  private static final String url = "http://127.0.0.1:8080/hello";

  private final RestTemplate restTemplate = new RestTemplate();

  @GetMapping("/sayHello")
  public String sayHello(@RequestParam("name") String name) {
    LOGGER.info("sayHello is called, name = [{}]", name);
    ResponseEntity<String> responseEntity = restTemplate
        .getForEntity(url + "/sayHello?name={name}", String.class, name);
    return responseEntity.getBody();
  }

  @PutMapping("/calc")
  public String calculate(@RequestParam("x") int x, @RequestParam("y") int y, @RequestHeader("oper") String oper) {
    LOGGER.info("calc is called, x = [{}], y = [{}], oper = [{}]", x, y, oper);
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("oper", oper);
    URI url = null;
    try {
      url = new URI(ClientConsole.url + "/calc");
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    RequestEntity<Object> requestEntity = new RequestEntity<>(httpHeaders, HttpMethod.PUT, url);
    ResponseEntity<Double> result = restTemplate
        .exchange(ClientConsole.url + "/calc?x={x}&y={y}", HttpMethod.PUT, requestEntity, Double.class, x, y);
    return String.valueOf(result.getBody());
  }

  @PostMapping("/postTest")
  public String postTest(@RequestBody Person person) {
    LOGGER.info("postTest is called, person = [{}]", person);
    return restTemplate.postForObject(url + "/postTest", person, String.class);
  }

  @DeleteMapping("/deleteTest")
  public String deleteTest(@RequestBody List<Person> personList) {
    LOGGER.info("deleteTest is called, personList = [{}]", personList);
    URI uri = null;
    try {
      uri = new URI(ClientConsole.url + "/deleteTest");
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    RequestEntity<List<Person>> requestEntity = new RequestEntity<>(personList, HttpMethod.DELETE, uri);
    ResponseEntity<String> result = restTemplate.exchange(requestEntity, String.class);
    return result.getBody();
  }
}
