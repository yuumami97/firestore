package lovesick.firestore;

import java.lang.Exception;

public class Http4xxException extends Exception { 
  public Http4xxException(String errorMessage) {
    super(errorMessage);
  }
}


