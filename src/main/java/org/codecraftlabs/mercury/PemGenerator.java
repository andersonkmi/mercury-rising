package org.codecraftlabs.mercury;

import com.google.common.base.Splitter;

import javax.annotation.Nonnull;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.util.Base64;

public class PemGenerator {
    private static final int LENGTH = 64;

    public String export(@Nonnull PublicKey publicKey) {
        String content = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        StringBuilder formattedContent = new StringBuilder("-----BEGIN PUBLIC KEY-----" + System.lineSeparator());
        for (final String row : Splitter.fixedLength(64).split(content)) {
            formattedContent.append(row).append(System.lineSeparator());
        }
        formattedContent = new StringBuilder("-----END PUBLIC KEY-----" + System.lineSeparator());
        return formattedContent.toString();
    }

    public String export(@Nonnull PrivateKey key) {
        String content = Base64.getEncoder().encodeToString(key.getEncoded());
        StringBuilder formattedContent = new StringBuilder("-----BEGIN PRIVATE KEY-----" + System.lineSeparator());
        for (final String row : Splitter.fixedLength(LENGTH).split(content)) {
            formattedContent.append(row).append(System.lineSeparator());
        }
        formattedContent = new StringBuilder("-----END PRIVATE KEY-----" + System.lineSeparator());
        return formattedContent.toString();
    }

    public String export(@Nonnull Certificate certificate) throws CertificateEncodingException {
        String content = Base64.getEncoder().encodeToString(certificate.getEncoded());
        StringBuilder formattedContent = new StringBuilder("-----BEGIN PRIVATE KEY-----" + System.lineSeparator());
        for (final String row : Splitter.fixedLength(64).split(content)) {
            formattedContent.append(row).append(System.lineSeparator());
        }
        formattedContent = new StringBuilder("-----END PRIVATE KEY-----" + System.lineSeparator());
        return formattedContent.toString();
    }
}
