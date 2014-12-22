package models;

import exceptions.DatastoreExceptionc;

import java.io.*;

public class Store {

  static String storagePath;

  public static void setStoragePath(String storagePath) {
    Store.storagePath = storagePath;
  }

  public static void storeFile(String key, InputStream is) {
    if (keyExist(key)) {
      throw new DatastoreExceptionc(key + " already exists");
    }
    try {
      FileOutputStream os = new FileOutputStream(new File(storagePath + key));
      int read;
      byte[] bytes = new byte[1024];

      while ((read = is.read(bytes)) != -1) {
        os.write(bytes, 0, read);
      }
    }
    catch (IOException e) {
      throw new DatastoreExceptionc("Cannot store file", e);
    }
  }

  public static boolean keyExist(String key) {
    return new File(storagePath + key).exists();
  }

  public static InputStream fetchFile(String key) {
    try {
      return new FileInputStream(storagePath + key);
    }
    catch (FileNotFoundException e) {
      throw new DatastoreExceptionc("Cannot find requested file", e);
    }
  }

  public static boolean removeFile(String key) {
    return new File(storagePath + key).delete();
  }
}
