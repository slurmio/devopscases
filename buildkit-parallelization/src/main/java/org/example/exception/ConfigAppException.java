package org.example.exception;

public class ConfigAppException extends AppException {
  public ConfigAppException() {
  }

  public ConfigAppException(final String message) {
    super(message);
  }

  public ConfigAppException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public ConfigAppException(final Throwable cause) {
    super(cause);
  }

  public ConfigAppException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
