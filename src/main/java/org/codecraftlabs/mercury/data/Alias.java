package org.codecraftlabs.mercury.data;

import java.security.PrivateKey;
import java.security.cert.Certificate;

public class Alias {
    final private String name;
    private Certificate certificate;
    private PrivateKey privateKey;

    public Alias(String name) {
        this.name = name;
    }

    public Alias(String name, Certificate certificate) {
        this(name);
        this.certificate = certificate;
    }

    public Alias(String name, Certificate certificate, PrivateKey privateKey) {
        this(name, certificate);
        this.privateKey = privateKey;
    }

    public String getName() {
        return name;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
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
