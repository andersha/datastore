package exceptions;

public class DatastoreExceptionc extends RuntimeException {

  public DatastoreExceptionc(String msg) {
    super(msg);
  }

  public DatastoreExceptionc(String msg, Throwable t) {
    super(msg, t);
  }
}
