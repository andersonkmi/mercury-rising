package org.codecraftlabs.mercury;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import static org.assertj.core.api.Assertions.assertThat;

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
}
