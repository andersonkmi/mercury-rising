package org.codecraftlabs.mercury.data;

import java.security.PrivateKey;
import java.security.cert.Certificate;

public record Alias(String name, Certificate certificate, PrivateKey privateKey) {

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!getClass().equals(other.getClass())) {
            return false;
        }

        Alias instance = (Alias) other;
        return instance.name.equals(name);
    }
}
