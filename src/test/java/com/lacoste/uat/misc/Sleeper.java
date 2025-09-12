package com.lacoste.uat.misc;

import java.time.Duration;

import static org.awaitility.Awaitility.await;

public class Sleeper {

  public static void wait(Duration duration) {
    await().pollDelay(duration).atMost(duration.plusSeconds(1)).until(() -> true);
  }
}
