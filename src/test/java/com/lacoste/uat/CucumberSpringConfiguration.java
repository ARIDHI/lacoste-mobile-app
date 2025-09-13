package com.lacoste.uat;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = UspE2EApplication.class, webEnvironment = WebEnvironment.NONE)
public class CucumberSpringConfiguration {
}
