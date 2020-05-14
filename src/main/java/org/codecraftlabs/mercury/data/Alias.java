package org.codecraftlabs.mercury.data;

import java.security.PrivateKey;
import java.security.cert.Certificate;

public class Alias {
    final private String name;
    final private Certificate certificate;
    final private PrivateKey privateKey;

    public Alias(String name, Certificate certificate, PrivateKey privateKey) {
        this.name = name;
        this.certificate = certificate;
        this.privateKey = privateKey;
    }

    public String getName() {
        return name;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (! getClass().equals(other.getClass())) {
            return false;
        }

        Alias instance = (Alias) other;
        return instance.name.equals(name);
    }
}
