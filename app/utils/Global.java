package utils;

import models.Store;
import play.Application;
import play.GlobalSettings;
import utils.DatastoreProperties;

import java.util.Date;

public class Global extends GlobalSettings {

  private static Date startTime;

  public static Date getStartTime() {
    return startTime;
  }

  public static void init() {
    startTime = new Date();
  }

  @Override
  public void onStart(Application application) {
    Store.setStoragePath(DatastoreProperties.getProperty("storage_path"));
    init();
  }
}
