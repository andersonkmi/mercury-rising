package org.codecraftlabs.mercury;

import org.codecraftlabs.mercury.util.StringUtil;

import javax.annotation.Nonnull;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.util.Base64;
import java.util.Collection;

import static org.codecraftlabs.mercury.util.AppConstants.BEGIN_CERTIFICATE_KEY;
import static org.codecraftlabs.mercury.util.AppConstants.BEGIN_PRIVATE_KEY;
import static org.codecraftlabs.mercury.util.AppConstants.BEGIN_PUBLIC_KEY;
import static org.codecraftlabs.mercury.util.AppConstants.END_CERTIFICATE_KEY;
import static org.codecraftlabs.mercury.util.AppConstants.END_PRIVATE_KEY;
import static org.codecraftlabs.mercury.util.AppConstants.END_PUBLIC_KEY;
import static org.codecraftlabs.mercury.util.AppConstants.LENGTH;

public class PemGenerator {
    @Nonnull
    public String export(@Nonnull PublicKey publicKey) {
        StringBuilder formattedContent = new StringBuilder(BEGIN_PUBLIC_KEY + System.lineSeparator());
        String content = encodeToString(publicKey.getEncoded());
        Collection<String> items = StringUtil.splitStringChunks(content, LENGTH);
        items.forEach(formattedContent::append);
        formattedContent.append(END_PUBLIC_KEY).append(System.lineSeparator());
        return formattedContent.toString();
    }

    @Nonnull
    public String export(@Nonnull PrivateKey key) {
        StringBuilder formattedContent = new StringBuilder(BEGIN_PRIVATE_KEY + System.lineSeparator());
        String content = encodeToString(key.getEncoded());
        Collection<String> items = StringUtil.splitStringChunks(content, LENGTH);
        items.forEach(formattedContent::append);
        formattedContent.append(END_PRIVATE_KEY).append(System.lineSeparator());
        return formattedContent.toString();
    }

    @Nonnull
    public String export(@Nonnull Certificate certificate) throws CertificateEncodingException {
        String content = Base64.getEncoder().encodeToString(certificate.getEncoded());
        StringBuilder formattedContent = new StringBuilder(BEGIN_CERTIFICATE_KEY + System.lineSeparator());
        Collection<String> items = StringUtil.splitStringChunks(content, LENGTH);
        items.forEach(item -> formattedContent.append(item).append(System.lineSeparator()));
        formattedContent.append(END_CERTIFICATE_KEY).append(System.lineSeparator());
        return formattedContent.toString();
    }

    @Nonnull
    private String encodeToString(@Nonnull byte[] encodedCertificate) {
        return Base64.getEncoder().encodeToString(encodedCertificate);
    }
}
