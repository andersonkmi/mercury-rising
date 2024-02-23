package org.codecraftlabs.mercury;

public class InvalidKeyPairGenerationAlgorithmException extends RuntimeException {
    public InvalidKeyPairGenerationAlgorithmException(String message) {
        super(message);
    }

    public InvalidKeyPairGenerationAlgorithmException(String message, Throwable exception) {
        super(message, exception);
    }
}
