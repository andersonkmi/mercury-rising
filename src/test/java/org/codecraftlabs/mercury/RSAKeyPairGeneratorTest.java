package org.codecraftlabs.mercury;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class RSAKeyPairGeneratorTest {
    private RSAKeyPairGenerator rsaKeyPairGenerator;

    @BeforeEach
    void setup() {
        this.rsaKeyPairGenerator = new RSAKeyPairGenerator();
    }

    @Test
    void should_create_key_pair_successfully() {
        KeyPair keyPair = rsaKeyPairGenerator.generateKeyPair(1024);
        assertThat(keyPair).isNotNull();

        PrivateKey privateKey = keyPair.getPrivate();
        assertThat(privateKey).isNotNull();
        PublicKey publicKey = keyPair.getPublic();
        assertThat(publicKey).isNotNull();
    }

    @Test
    void should_throw_exception_when_key_size_is_invalid() {
        assertThatExceptionOfType(InvalidKeySizeException.class)
                .isThrownBy(() -> rsaKeyPairGenerator.generateKeyPair(1))
                .withMessage("Invalid key size provided");
    }
}
