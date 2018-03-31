package com.github.yhs0092.springbootdemo.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

public class TestConfigListener implements EnvironmentPostProcessor, Ordered {
  public TestConfigListener() {
    System.out.println("init TestConfigListener!!!!!");
  }

  @Override
  public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
    System.out.println("get environment!!!!!");
  }

  @Override
  public int getOrder() {
    return Ordered.LOWEST_PRECEDENCE;
  }
}
