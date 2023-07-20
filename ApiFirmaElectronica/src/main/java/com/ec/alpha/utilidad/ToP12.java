/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.alpha.utilidad;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;

/**
 *
 * @author ppltm
 */
public class ToP12 {

    public byte[] convertPEMToPKCS12FromFile(final String keyFile, final String certChainFile, final String password, final String alias) {
        FileReader reader = null;
        try {

            PrivateKey key = getPrivateKeyFromPem(keyFile);
            X509Certificate certificado = readCertificate(certChainFile);

            // Put them into a PKCS12 keystore and write it to a byte[]
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            KeyStore ks = KeyStore.getInstance("PKCS12");
            ks.load(null);
            ks.setKeyEntry(alias, (Key) key, password.toCharArray(), new java.security.cert.Certificate[]{certificado});
            ks.store(bos, password.toCharArray());
            bos.close();

            return bos.toByteArray();

        } catch (Exception ex) {
            Logger.getLogger(ToP12.class.getName()).log(Level.SEVERE, null, ex);
            return "".getBytes();
        }

    }

    public byte[] convertPEMToPKCS12FromString(final String keystring, final String cert, final String root, final String intermediate, final String password, final String alias) {
        FileReader reader = null;
        try {

            PrivateKey key = getPrivateKeyFromPEMString(keystring);
            String chain = cert + "\n" + root + "\n" + intermediate;

            X509Certificate certificado = readCertificateFromString(chain);

            // Put them into a PKCS12 keystore and write it to a byte[]
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            KeyStore ks = KeyStore.getInstance("PKCS12");
            ks.load(null);
            ks.setKeyEntry(alias, (Key) key, password.toCharArray(), new java.security.cert.Certificate[]{certificado});
            ks.store(bos, password.toCharArray());
            bos.close();

            return bos.toByteArray();

        } catch (Exception ex) {
            Logger.getLogger(ToP12.class.getName()).log(Level.SEVERE, null, ex);
            return "".getBytes();
        }

    }

    private X509Certificate readCertificate(String certificatePath) throws IOException, CertificateException {
        FileInputStream fis = new FileInputStream(certificatePath);
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        return (X509Certificate) certificateFactory.generateCertificate(fis);

    }

    private X509Certificate readCertificateFromString(String chain) throws IOException, CertificateException {
        InputStream sis = new ByteArrayInputStream(chain.getBytes());
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        return (X509Certificate) certificateFactory.generateCertificate(sis);
    }

    public PrivateKey getPrivateKeyFromPem(String path_to_file) {
        PrivateKey key = null;
        try {
            KeyFactory kf = KeyFactory.getInstance("RSA");
            InputStream in = new FileInputStream("c:\\cert\\firma.key");
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
            byte[] data = new byte[16384];
            while ((nRead = in.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }

            String pem = new String(data, StandardCharsets.ISO_8859_1);
            Pattern parse = Pattern.compile("(?m)(?s)^---*BEGIN.*---*$(.*)^---*END.*---*$.*");
            String encoded = parse.matcher(pem).replaceFirst("$1");
            key = kf.generatePrivate(new PKCS8EncodedKeySpec(Base64.getMimeDecoder().decode(encoded)));

            return key;
        } catch (Exception ex) {
            ex.getMessage();
            return key;
        }

    }

    public PrivateKey getPrivateKeyFromPEMString(String keystring) {
        PrivateKey key = null;
        try {
            KeyFactory kf = KeyFactory.getInstance("RSA");

            String pem = new String(keystring.getBytes(), StandardCharsets.ISO_8859_1);
            Pattern parse = Pattern.compile("(?m)(?s)^---*BEGIN.*---*$(.*)^---*END.*---*$.*");
            String encoded = parse.matcher(pem).replaceFirst("$1");
            key = kf.generatePrivate(new PKCS8EncodedKeySpec(Base64.getMimeDecoder().decode(encoded)));

            return key;
        } catch (Exception ex) {
            ex.getMessage();
            return key;
        }

    }

    public void saveP12ToFile(byte data[], String archivo_p12) {

        try {
            FileOutputStream fos = new FileOutputStream(archivo_p12);
            fos.write(data);
        } catch (Exception ex) {
            Logger.getLogger(ToP12.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public  boolean byteToFile(byte[] arrayBytes, String rutaArchivo) {
        /* 164 */ boolean respuesta = false;
        try {
            /* 166 */ File file = new File(rutaArchivo);
            /* 167 */ file.createNewFile();
            /* 168 */ FileInputStream fileInputStream = new FileInputStream(rutaArchivo);
            /* 169 */ ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(arrayBytes);
            /* 170 */ OutputStream outputStream = new FileOutputStream(rutaArchivo);
            int data;
            /* 173 */ while ((data = byteArrayInputStream.read()) != -1) {
                /* 174 */ outputStream.write(data);
            }

            /* 177 */ fileInputStream.close();
            /* 178 */ outputStream.close();
            /* 179 */ respuesta = true;
        } catch (IOException ex) {
            /* 181 */ Logger.getLogger(ToP12.class.getName()).log(Level.SEVERE, null, ex);
        }
        /* 183 */ return respuesta;
    }


//    public static void main(String args[]) {
//        try {
//            ToP12 t = new ToP12();
//            //byte bites[] = t.convertPEMToPKCS12FromFile("c:\\cert\\firma.key", "c:\\cert\\firma.crt", "1234","Steven Chiriboga");
//            byte bytes[] = t.convertPEMToPKCS12FromString(Info.privateStringKey, Info.cerficado, Info.rootCrt, Info.intermediateCrt, "1234", "Steven Chiriboga");
//            t.saveP12ToFile(bytes, "c:\\cert\\cert.p12");
//        } catch (Exception ex) {
//            ex.getMessage();
//        }
//
//    }

}
