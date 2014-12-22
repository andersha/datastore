package exceptions;

public class DatastoreException extends RuntimeException {

  public DatastoreException(String msg) {
    super(msg);
  }

  public DatastoreException(String msg, Throwable t) {
    super(msg, t);
  }
}
