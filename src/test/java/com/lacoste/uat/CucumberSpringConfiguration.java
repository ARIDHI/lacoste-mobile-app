package com.lacoste.uat;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@CucumberContextConfiguration
@SpringBootTest(classes = SutE2EApplication.class, webEnvironment = WebEnvironment.NONE)
public class CucumberSpringConfiguration {
}
