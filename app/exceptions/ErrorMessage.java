package exceptions;

public class ErrorMessage {

  public static Error cannotStore = new Error("Cannot store file");
  public static Error noFile = new Error("No file to store");

  private static class Error {
    String error;

    Error(String error) {
      this.error = error;
    }
  }
}
