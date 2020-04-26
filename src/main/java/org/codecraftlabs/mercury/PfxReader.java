package org.codecraftlabs.mercury;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.Set;

public class PfxReader {
    public Set<Certificate> getCertificates(String file, String password, SecurityProvider provider) throws PfxReaderException {
        if (file == null || file.isEmpty()) {
            throw new PfxReaderException("Missing PFX file");
        }

        try {
            Security.addProvider(new BouncyCastleProvider());
            KeyStore ks = KeyStore.getInstance(provider.getType(), provider.getProviderName());
            ks.load(new FileInputStream(file), password.toCharArray());

            Enumeration en = ks.aliases();

            while (en.hasMoreElements()) {
                String temp = (String) en.nextElement();
                if (ks.isCertificateEntry(temp)) {
                    X509Certificate cerCert = (X509Certificate) ks.getCertificate(temp);
                }
            }
        }
        catch( Exception e ){
            e.printStackTrace();
        }
        return null;
    }
}
