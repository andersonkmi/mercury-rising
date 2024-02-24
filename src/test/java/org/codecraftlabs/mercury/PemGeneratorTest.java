package org.codecraftlabs.mercury;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.KeyPair;

public class PemGeneratorTest {
    private PemGenerator pemGenerator;
    private RSAKeyPairGenerator rsaKeyPairGenerator;

    @BeforeEach
    void setup() {
        pemGenerator = new PemGenerator();
        rsaKeyPairGenerator = new RSAKeyPairGenerator();
    }

    @Test
    void export_key_pair_successfully() {
        KeyPair rsaKeyPair = rsaKeyPairGenerator.generateKeyPair(1024);
        String privateKeyPem = pemGenerator.export(rsaKeyPair.getPrivate());
        Assertions.assertThat(privateKeyPem).isNotNull();

        String publicKeyPem = pemGenerator.export(rsaKeyPair.getPublic());
        Assertions.assertThat(publicKeyPem).isNotNull();
    }
}
