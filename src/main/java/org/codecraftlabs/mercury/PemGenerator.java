package org.codecraftlabs.mercury;

import javax.annotation.Nonnull;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;

import static java.lang.Math.min;
import static java.lang.System.lineSeparator;

public class PemGenerator {
    private static final int LENGTH = 64;
    private static final String BEGIN_PUBLIC_KEY = "-----BEGIN RSA PUBLIC KEY-----";
    private static final String END_PUBLIC_KEY = "-----END RSA PUBLIC KEY-----";
    private static final String BEGIN_PRIVATE_KEY = "-----BEGIN RSA PRIVATE KEY-----";
    private static final String END_PRIVATE_KEY = "-----END RSA PRIVATE KEY-----";
    private static final String BEGIN_CERTIFICATE_KEY = "-----BEGIN CERTIFICATE KEY-----";
    private static final String END_CERTIFICATE_KEY = "-----END CERTIFICATE KEY-----";

    @Nonnull
    public String export(@Nonnull PublicKey publicKey) {
        StringBuilder formattedContent = new StringBuilder();
        formattedContent.append(BEGIN_PUBLIC_KEY);
        formattedContent.append(lineSeparator());
        String content = encodeToString(publicKey.getEncoded());
        Collection<String> items = splitStringChunks(content, LENGTH);
        items.forEach(formattedContent::append);
        formattedContent.append(END_PUBLIC_KEY).append(lineSeparator());
        return formattedContent.toString();
    }

    @Nonnull
    public String export(@Nonnull PrivateKey key) {
        StringBuilder formattedContent = new StringBuilder(BEGIN_PRIVATE_KEY);
        formattedContent.append(lineSeparator());
        String content = encodeToString(key.getEncoded());
        Collection<String> items = splitStringChunks(content, LENGTH);
        items.forEach(formattedContent::append);
        formattedContent.append(END_PRIVATE_KEY).append(lineSeparator());
        return formattedContent.toString();
    }

    @Nonnull
    public String export(@Nonnull Certificate certificate) throws CertificateEncodingException {
        String content = Base64.getEncoder().encodeToString(certificate.getEncoded());
        StringBuilder formattedContent = new StringBuilder(BEGIN_CERTIFICATE_KEY + lineSeparator());
        Collection<String> items = splitStringChunks(content, LENGTH);
        items.forEach(item -> formattedContent.append(item).append(lineSeparator()));
        formattedContent.append(END_CERTIFICATE_KEY).append(lineSeparator());
        return formattedContent.toString();
    }

    @Nonnull
    private String encodeToString(@Nonnull byte[] encodedCertificate) {
        return Base64.getEncoder().encodeToString(encodedCertificate);
    }

    @Nonnull
    private Collection<String> splitStringChunks(String str, int size) {
        ArrayList<String> split = new ArrayList<>();
        for (int i = 0; i <= str.length() / size; i++) {
            split.add(str.substring(i * size, min((i + 1) * size, str.length())));
        }
        return split;
    }
}
