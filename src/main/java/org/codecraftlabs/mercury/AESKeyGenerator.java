package org.codecraftlabs.mercury;

import javax.annotation.Nonnull;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.InvalidParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AESKeyGenerator {
    private static final String KEY_ALGORITHM = "AES";

    public enum KeySize {
        MOD_128(128),
        MOD_192(192),
        MOD_256(256);

        private final int size;

        KeySize(int size) {
            this.size = size;
        }

        public int size() {
            return size;
        }
    }

    @Nonnull
    public SecretKey generateSecretKey(@Nonnull KeySize keySize) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
            SecureRandom secureRandom = new SecureRandom();
            keyGenerator.init(keySize.size(), secureRandom);
            return keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException exception) {
            throw new InvalidKeyPairGenerationAlgorithmException("Error when generating key pair", exception);
        }
    }

    @Nonnull
    public SecretKey generateSecretKey() {
        return generateSecretKey(KeySize.MOD_256);
    }
}
