package org.codecraftlabs.mercury;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.codecraftlabs.mercury.data.Alias;

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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PfxReader {
    public Set<Alias> getCertificates(String file, String password, SecurityProvider provider) throws PfxReaderException {
        if (file == null || file.isEmpty()) {
            throw new PfxReaderException("Missing PFX file");
        }

        String pfxPassword = password != null ? password : "";
        final Set<Alias> certificates = new HashSet<>();

        try {
            Security.addProvider(new BouncyCastleProvider());
            final KeyStore ks = KeyStore.getInstance(provider.getType(), provider.getProviderName());
            ks.load(new FileInputStream(file), pfxPassword.toCharArray());

            List<String> aliases = Collections.list(ks.aliases());
            for (String alias : aliases) {
                X509Certificate certificate = null;
                PrivateKey privateKey = null;
                if (ks.isCertificateEntry(alias)) {
                    certificate = (X509Certificate) ks.getCertificate(alias);
                }

                if (ks.isKeyEntry(alias)) {
                    privateKey = (PrivateKey) ks.getKey(alias, null);
                }

                Alias aliasItem = new Alias(alias, certificate, privateKey);
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
