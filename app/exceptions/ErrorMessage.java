package exceptions;

public class ErrorMessage {

  private static Error cannotStore = new Error("Cannot store file");
  private static Error noFile = new Error("No file to store");

  private static class Error {
    String error;

    Error(String error) {
      this.error = error;
    }
  }

  public static Error cannotStore() {
    return cannotStore;
  }

  public static Error noFile() {
    return noFile;
  }
}
