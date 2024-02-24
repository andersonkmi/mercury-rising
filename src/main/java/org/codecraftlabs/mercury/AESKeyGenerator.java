package org.codecraftlabs.mercury;

import javax.annotation.Nonnull;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.InvalidParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AESKeyGenerator {
    private static final String KEY_ALGORITHM = "AES";
    private static final int DEFAULT_KEY_SIZE = 256;

    @Nonnull
    public SecretKey generateSecretKey(int keySize) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
            SecureRandom secureRandom = new SecureRandom();
            keyGenerator.init(keySize, secureRandom);
            return keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException exception) {
            throw new InvalidKeyPairGenerationAlgorithmException("Error when generating key pair", exception);
        } catch (InvalidParameterException exception) {
            throw new InvalidKeySizeException("Invalid key size provided", exception);
        }
    }

    @Nonnull
    public SecretKey generateSecretKey() {
        return generateSecretKey(DEFAULT_KEY_SIZE);
    }
}
