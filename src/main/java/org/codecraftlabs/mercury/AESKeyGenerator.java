package org.codecraftlabs.mercury;

import javax.annotation.Nonnull;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class AESKeyGenerator {
    private static final String KEY_ALGORITHM = "AES";
    private static final String PBK_ALGORITHM = "PBKDF2WithHmacSHA256";

    public enum KeySize {
        SIZE_128(128),
        SIZE_192(192),
        SIZE_256(256);

        private final int size;

        KeySize(int size) {
            this.size = size;
        }

        public int size() {
            return size;
        }
    }

    @Nonnull
    public Key generateSecretKey(@Nonnull KeySize keySize) {
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
    public Key generateSecretKey() {
        return generateSecretKey(KeySize.SIZE_256);
    }

    @Nonnull
    public Key generateSecretKey(@Nonnull KeySize keySize, @Nonnull char[] password) {
        try {
            byte[] salt = new byte[100];
            SecureRandom random = new SecureRandom();
            random.nextBytes(salt);
            PBEKeySpec pbeKeySpec = new PBEKeySpec(password, salt, 1000, keySize.size());
            SecretKey pbeKey = SecretKeyFactory.getInstance(PBK_ALGORITHM).generateSecret(pbeKeySpec);
            return new SecretKeySpec(pbeKey.getEncoded(), KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException exception) {
            throw new InvalidKeyPairGenerationAlgorithmException("Error when generating key pair", exception);
        }
    }

    @Nonnull
    public Key generateSecretKey(@Nonnull char[] password) {
        return generateSecretKey(KeySize.SIZE_256, password);
    }
}
