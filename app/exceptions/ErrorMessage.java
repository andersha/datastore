package exceptions;

public class ErrorMessage {

  public static Error cannotStore = new Error("Cannot store file");
  public static Error noFile = new Error("No file to store");

  public static class Error {
    public String error;

    Error(String error) {
      this.error = error;
    }
  }
}
