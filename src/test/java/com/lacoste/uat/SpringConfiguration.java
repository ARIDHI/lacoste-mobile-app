package com.lacoste.uat;

import com.lacoste.uat.conf.UatProperties;
import com.lacoste.uat.data.CapsPrifix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = SpringConfiguration.class)
public class SpringConfiguration {

  @Bean
  CapsPrifix environement(UatProperties properties)
  {
    return properties.environment();
  }

}
