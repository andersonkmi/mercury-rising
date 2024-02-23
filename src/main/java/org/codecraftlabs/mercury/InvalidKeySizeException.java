package org.codecraftlabs.mercury;

public class InvalidKeySizeException extends RuntimeException {
    public InvalidKeySizeException(String message) {
        super(message);
    }

    public InvalidKeySizeException(String message, Throwable exception) {
        super(message, exception);
    }
}
