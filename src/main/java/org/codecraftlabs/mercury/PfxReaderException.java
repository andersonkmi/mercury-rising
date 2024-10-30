package org.codecraftlabs.mercury;

public class PfxReaderException extends RuntimeException {
    public PfxReaderException(String message) {
        super(message);
    }

    public PfxReaderException(String message, Throwable exception) {
        super(message, exception);
    }
}
