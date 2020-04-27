package org.codecraftlabs.mercury;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.annotation.Nonnull;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Set;

public class PfxReader {
    @Nonnull
    public Set<Certificate> getCertificates(@Nonnull String file, @Nonnull String password, @Nonnull SecurityProvider provider) throws PfxReaderException {
        if (file.isEmpty()) {
            throw new PfxReaderException("Missing PFX file");
        }

        try {
            Security.addProvider(new BouncyCastleProvider());
            KeyStore ks = KeyStore.getInstance(provider.getType(), provider.getProviderName());
            ks.load(new FileInputStream(file), password.toCharArray());

            Enumeration<String> aliases = ks.aliases();

            while (aliases.hasMoreElements()) {
                String temp = aliases.nextElement();
                if (ks.isCertificateEntry(temp)) {
                    X509Certificate cerCert = (X509Certificate) ks.getCertificate(temp);
                    PublicKey publicKey = cerCert.getPublicKey();
                } else if (ks.isKeyEntry(temp)) {
                    PrivateKey privateKey = (PrivateKey) ks.getKey(temp, null);
                }
            }
        }
        catch(Exception exception){
            //
        }
        return Collections.emptySet();
    }

    public static void main(String[] args) throws PfxReaderException {
        PfxReader reader = new PfxReader();
        Set<Certificate> certs = reader.getCertificates("C:\\test.pfx", "test", SecurityProvider.BOUNCY_CASTLE);
        System.out.println(certs);
    }
}
