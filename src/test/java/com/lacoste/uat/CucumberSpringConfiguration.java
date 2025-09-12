package com.lacoste.uat;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@CucumberContextConfiguration
@SpringBootTest(classes = SpringConfiguration.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class CucumberSpringConfiguration {
}
