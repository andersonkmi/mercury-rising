package org.codecraftlabs.mercury;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import static org.assertj.core.api.Assertions.assertThat;
import static org.codecraftlabs.mercury.RSAKeyPairGenerator.KeySize.MOD_1024;

public class RSAKeyPairGeneratorTest {
    private RSAKeyPairGenerator rsaKeyPairGenerator;

    @BeforeEach
    void setup() {
        this.rsaKeyPairGenerator = new RSAKeyPairGenerator();
    }

    @Test
    void should_create_key_pair_successfully() {
        KeyPair keyPair = rsaKeyPairGenerator.generateKeyPair(MOD_1024);
        assertThat(keyPair).isNotNull();

        PrivateKey privateKey = keyPair.getPrivate();
        assertThat(privateKey).isNotNull();
        PublicKey publicKey = keyPair.getPublic();
        assertThat(publicKey).isNotNull();
    }
}
