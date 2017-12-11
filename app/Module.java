import models.Store;

import com.google.inject.AbstractModule;
import utils.DatastoreProperties;
import utils.Global;

@SuppressWarnings("unused")
public class Module extends AbstractModule {

  @Override
  protected void configure() {
    Store.setStoragePath(DatastoreProperties.getProperty("storage_path"));
    Global.init();
  }
}
