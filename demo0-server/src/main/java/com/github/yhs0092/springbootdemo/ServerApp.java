package com.github.yhs0092.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.github.yhs0092.springbootdemo")
public class ServerApp {

  public static void main(String[] args) {
    System.out.println("Hello World!");
    SpringApplication.run(ServerApp.class, args);
  }

  @RequestMapping("/")
  public String home() {
    return "From ServerApp: Hello World!";
  }
}
