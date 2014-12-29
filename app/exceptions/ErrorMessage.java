package exceptions;

public class ErrorMessage {

  public static Error cannotStore = new Error("Cannot store file. Make sure file does not already exist.");
  public static Error cannotFetch = new Error("Cannot fetch file. Make sure it exist.");
  public static Error cannotDelete = new Error("Cannot delete file. Make sure it exist.");
  public static Error noFile = new Error("No file to store. Put file in a multipart form body with the key 'document'.");

  public static class Error {
    public String error;

    Error(String error) {
      this.error = error;
    }
  }
}
