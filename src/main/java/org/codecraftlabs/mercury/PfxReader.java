package org.codecraftlabs.mercury;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.codecraftlabs.mercury.data.Alias;

import javax.annotation.Nonnull;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PfxReader {
    @Nonnull
    public Set<Alias> getCertificates(@Nonnull String file, @Nonnull String password, @Nonnull SecurityProvider provider) throws PfxReaderException {
        if (file.isEmpty()) {
            throw new PfxReaderException("Missing PFX file");
        }

        final Set<Alias> certificates = new HashSet<>();

        try {
            Security.addProvider(new BouncyCastleProvider());
            final KeyStore ks = KeyStore.getInstance(provider.getType(), provider.getProviderName());
            ks.load(new FileInputStream(file), password.toCharArray());

            List<String> aliases = Collections.list(ks.aliases());
            for (String alias : aliases) {
                Alias aliasItem = new Alias(alias);
                if (ks.isCertificateEntry(alias)) {
                    X509Certificate cerCert = (X509Certificate) ks.getCertificate(alias);
                    aliasItem.setCertificate(cerCert);
                } else if (ks.isKeyEntry(alias)) {
                    PrivateKey privateKey = (PrivateKey) ks.getKey(alias, null);
                    aliasItem.setPrivateKey(privateKey);
                }
                certificates.add(aliasItem);
            }

        } catch (KeyStoreException | NoSuchAlgorithmException | NoSuchProviderException | UnrecoverableKeyException | CertificateException exception) {
            throw new PfxReaderException("Error when processing PFX file", exception);
        } catch (IOException exception) {
            throw new PfxReaderException("Error when opening PFX file", exception);
        }
        return certificates;
    }
}
