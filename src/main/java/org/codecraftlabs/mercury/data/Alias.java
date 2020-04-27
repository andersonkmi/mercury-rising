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
}
