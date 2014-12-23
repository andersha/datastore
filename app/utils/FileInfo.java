package utils;

public class FileInfo {

  public String name;
  public long size;
  public String timestamp;

  public FileInfo(String name, long size, String timestamp) {
    this.name = name;
    this.size = size;
    this.timestamp = timestamp;
  }
}
