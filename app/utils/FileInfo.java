package utils;

public class FileInfo {

  public final int counter;
  public final String name;
  public final long size;
  public final String timestamp;

  public FileInfo(String name, long size, String timestamp, int counter) {
    this.name = name;
    this.size = size;
    this.timestamp = timestamp;
    this.counter = counter;
  }
}
