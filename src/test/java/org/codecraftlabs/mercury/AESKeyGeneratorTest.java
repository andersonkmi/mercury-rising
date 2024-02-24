package org.codecraftlabs.mercury;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class AESKeyGeneratorTest {
    private AESKeyGenerator aesKeyGenerator;

    @BeforeEach
    void setup() {
        aesKeyGenerator = new AESKeyGenerator();
    }

    @Test
    void should_create_key_successfully() {
        SecretKey secretKey = aesKeyGenerator.generateSecretKey();
        assertThat(secretKey).isNotNull();
    }
}
