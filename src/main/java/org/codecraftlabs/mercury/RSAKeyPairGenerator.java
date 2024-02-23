package org.codecraftlabs.mercury;

import javax.annotation.Nonnull;
import java.security.InvalidParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class RSAKeyPairGenerator {
    private static final String KEY_PAIR_ALGORITHM = "RSA";
    @Nonnull
    public KeyPair generateKeyPair(int keySize) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_PAIR_ALGORITHM);
            keyPairGenerator.initialize(keySize);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException exception) {
            throw new InvalidKeyPairGenerationAlgorithmException("Error when generating key pair", exception);
        } catch (InvalidParameterException exception) {
            throw new InvalidKeySizeException("Invalid key size provided", exception);
        }
    }
}
