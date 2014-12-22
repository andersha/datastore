package utils;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigFactory;
import exceptions.DatastoreException;

import java.util.HashMap;
import java.util.Map;

public class DatastoreProperties {

  private static Config config;

  private static final Map<String, String> PROPS = new HashMap<String, String>() {{
    put("storage_path", "D:");
  }};

  public static String getProperty(String key) {
    String value;
    try {
      value = getConfigProperty(key);
    }
    catch (ConfigException e) {
      value = PROPS.get(key);
      if (value == null) {
        throw new DatastoreException("No property exists with key '" + key + "'");
      }
    }
    return value;
  }

  private static String getConfigProperty(String key) throws ConfigException {
    return getConfigFactory().getString(key);
  }

  private static Config getConfigFactory() {
    if (config == null) {
      config = ConfigFactory.load();
    }
    return config;
  }
}
