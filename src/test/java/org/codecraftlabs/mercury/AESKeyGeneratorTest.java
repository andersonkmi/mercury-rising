package org.codecraftlabs.mercury;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.Key;

import static org.assertj.core.api.Assertions.assertThat;

public class AESKeyGeneratorTest {
    private AESKeyGenerator aesKeyGenerator;

    @BeforeEach
    void setup() {
        aesKeyGenerator = new AESKeyGenerator();
    }

    @Test
    void should_create_key_successfully() {
        Key secretKey = aesKeyGenerator.generateSecretKey();
        assertThat(secretKey).isNotNull();
    }

    @Test
    void should_create_key_with_password_successfully() {
        Key secretKey = aesKeyGenerator.generateSecretKey("password".toCharArray());
        assertThat(secretKey).isNotNull();
    }
}
