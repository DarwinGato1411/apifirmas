package com.ec.alpha.responsefirma;

import java.security.KeyFactory;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class Utilitario {
	// Create base64 encoded signature using SHA256/RSA.
    public static String signSHA256RSA(String input, String strPk) throws Exception {
        // Remove markers and new line characters in private key
        String realPK = strPk.replaceAll("—-END RSA PRIVATE KEY—-", "")
                             .replaceAll("—-BEGIN RSA PRIVATE KEY—-", "")
                             .replaceAll("\n", "");
 
        byte[] b1 = Base64.getDecoder().decode(realPK);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(b1);
        KeyFactory kf = KeyFactory.getInstance("RSA");
 
        Signature privateSignature = Signature.getInstance("SHA256withRSA");
        privateSignature.initSign(kf.generatePrivate(spec));
        privateSignature.update(input.getBytes("UTF-8"));
        byte[] s = privateSignature.sign();
        return Base64.getEncoder().encodeToString(s);
    }
}
