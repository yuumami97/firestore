package lovesick.firestore;

import java.lang.Exception;

public class Http5xxException extends Exception { 
  public Http5xxException(String errorMessage) {
    super(errorMessage);
  }
}


