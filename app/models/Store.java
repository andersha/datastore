package models;

import exceptions.DatastoreException;
import utils.FileInfo;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Store {

  static String storagePath;

  public static void setStoragePath(String storagePath) {
    Store.storagePath = storagePath;
  }

  public static void storeFile(String key, InputStream is) {
    if (keyExist(key)) {
      throw new DatastoreException(key + " already exists");
    }
    try (FileOutputStream os = new FileOutputStream(new File(storagePath + key));) {
      int read;
      byte[] bytes = new byte[1024];

      while ((read = is.read(bytes)) != -1) {
        os.write(bytes, 0, read);
      }
    }
    catch (IOException e) {
      throw new DatastoreException("Cannot store file", e);
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
      throw new DatastoreException("Cannot find requested file", e);
    }
  }

  public static boolean removeFile(String key) {
    return new File(storagePath + key).delete();
  }

  public static List<FileInfo> listFiles() {
    File[] files = new File(storagePath).listFiles();
    if (files != null) {
      ArrayList<FileInfo> out = new ArrayList<>();
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
      int i = 1;
      for (File file : files) {
        out.add(new FileInfo(file.getName(), file.length(), sdf.format(file.lastModified()), i++));
      }
      return out;
    }
    else {
      return Collections.emptyList();
    }
  }
}
