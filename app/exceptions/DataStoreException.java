package exceptions;

public class DataStoreException extends RuntimeException {

  public DataStoreException(String msg) {
    super(msg);
  }

  public DataStoreException(String msg, Throwable t) {
    super(msg, t);
  }
}
