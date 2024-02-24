package org.codecraftlabs.mercury;

import javax.annotation.Nonnull;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class RSAKeyPairGenerator {
    private static final String KEY_PAIR_ALGORITHM = "RSA";

    public enum KeySize {
        MOD_1024(1024),
        MOD_2048(2048),
        MOD_4096(4096);

        private final int keySize;

        KeySize(int keySize) {
            this.keySize = keySize;
        }

        int getKeySize() {
            return keySize;
        }
    }
    /**
     * This method creates an RSA key pair.
     * @param keySize Key size to be used when creating the pair
     * @return KeyPair instance
     * @throws InvalidKeyPairGenerationAlgorithmException if the algorithm is not valid
     */
    @Nonnull
    public KeyPair generateKeyPair(@Nonnull KeySize keySize) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_PAIR_ALGORITHM);
            keyPairGenerator.initialize(keySize.getKeySize());
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException exception) {
            throw new InvalidKeyPairGenerationAlgorithmException("Error when generating key pair", exception);
        }
    }
}
