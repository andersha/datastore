import models.Store;
import play.Application;
import play.GlobalSettings;
import utils.DatastoreProperties;

public class Global extends GlobalSettings {

  @Override
  public void onStart(Application application) {
    Store.setStoragePath(DatastoreProperties.getProperty("storage_path"));
  }
}
