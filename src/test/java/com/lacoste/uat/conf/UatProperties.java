package com.lacoste.uat.conf;

import com.lacoste.uat.data.CapsPrifix;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "uat")
@Data
public class UatProperties {

  private String currentEnv;
  private Map<String, CapsPrifix> envs;

  public CapsPrifix environment() {
    return envs.get(currentEnv.toLowerCase());
  }
}
