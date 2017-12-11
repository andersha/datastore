package utils;

import java.util.Date;

public class Global {

  private static Date startTime;

  public static Date getStartTime() {
    return startTime;
  }

  public static void init() {
    startTime = new Date();
  }
}
