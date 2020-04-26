package org.codecraftlabs.mercury;

public enum SecurityProvider {
    BOUNCY_CASTLE("PKCS12", "BC");

    final private String type;
    final private String providerName;

    SecurityProvider(String type, String providerName) {
        this.type = type;
        this.providerName = providerName;
    }

    public String getType() {
        return type;
    }

    public String getProviderName() {
        return providerName;
    }
}
