/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.alpha.utilidad;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.security.auth.x500.X500Principal;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

import sun.security.pkcs10.*;
import sun.security.x509.*;

public class GenerateCSR {

    private static PublicKey publicKey = null;
    private static PrivateKey privateKey = null;
    private static KeyPairGenerator keyGen = null;
    private static GenerateCSR gcsr = null;

    

    private GenerateCSR() {
        try {
            keyGen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        keyGen.initialize(2048, new SecureRandom());
        KeyPair keypair = keyGen.generateKeyPair();
        publicKey = keypair.getPublic();
        privateKey = keypair.getPrivate();
    }

    public static GenerateCSR getInstance() {
        if (gcsr == null) {
            gcsr = new GenerateCSR();
        }
        return gcsr;
    }

    public String getCSR(String CN, String OU, String O, String L, String S, String C) throws Exception { 
        byte[] csr = generatePKCS10(CN, OU, O, L, S,C);
        return new String(csr);
    }
    
    public String getCSR(String CN) throws Exception { 
        byte[] csr = generatePKCS10(CN);
        return new String(csr);
    }

    public String getCSR() throws Exception { 
        byte[] csr = generatePKCS10("AlphaSide");
        return new String(csr);
    }
        

    /**
     *
     * @param CN Common Name, is X.509 speak for the name that distinguishes the
     * Certificate best, and ties it to your Organization
     * @param OU Organizational unit
     * @param O Organization NAME
     * @param L Location
     * @param S State
     * @param C Country
     * @return
     * @throws Exception
     */
    private static byte[] generatePKCS10(String CN, String OU, String O, String L, String S, String C) throws Exception {
        // generate PKCS10 certificate request
        String sigAlg = "SHA256withRSA";
        PKCS10 pkcs10 = new PKCS10(publicKey);
        Signature signature = Signature.getInstance(sigAlg);
        signature.initSign(privateKey);
        // common, orgUnit, org, locality, state, country
        X500Principal principal = new X500Principal("CN="+CN+", OU="+OU+", O="+O+", L="+L+",S="+S+" ,C="+C);
        X500Name x500name = null;
        x500name = new X500Name(principal.getEncoded());
        pkcs10.encodeAndSign(x500name, signature);
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(bs);
        pkcs10.print(ps);
        byte[] c = bs.toByteArray();
        try {
            if (ps != null) {
                ps.close();
            }
            if (bs != null) {
                bs.close();
            }
        } catch (Throwable th) {
        }
        return c;
    }

    /**
     * @param CN Common Name, is X.509 speak for the name that distinguishes the
     * @return
     * @throws Exception
     */
    private static byte[] generatePKCS10(String CN) throws Exception {
        // generate PKCS10 certificate request
        String sigAlg = "MD5WithRSA";
        PKCS10 pkcs10 = new PKCS10(publicKey);
        Signature signature = Signature.getInstance(sigAlg);
        signature.initSign(privateKey);
        // common, orgUnit, org, locality, state, country
        X500Principal principal = new X500Principal("CN="+CN);
        X500Name x500name = null;
        x500name = new X500Name(principal.getEncoded());
        pkcs10.encodeAndSign(x500name, signature);
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(bs);
        pkcs10.print(ps);
        byte[] c = bs.toByteArray();
        try {
            if (ps != null) {
                ps.close();
            }
            if (bs != null) {
                bs.close();
            }
        } catch (Throwable th) {
        }
        return c;
    }    
    
    public PublicKey getPublicKey() {
        return publicKey;
    }

    
    public PrivateKey getPrivateKey() {
        return privateKey;
    }
    
    public String getPEMPrivateKey(){
        try {
            StringWriter stringWriter = new StringWriter();
            final PemWriter pemWriter = new PemWriter(stringWriter);
            final PemObject pemObject = new PemObject("PRIVATE KEY", gcsr.getPrivateKey().getEncoded());
            pemWriter.writeObject(pemObject);
            pemWriter.flush();
            return stringWriter.toString();
        } catch (IOException ex) {
            
            Logger.getLogger(GenerateCSR.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }

//    public static void main(String[] args) throws Exception {
//        GenerateCSR gcsr = GenerateCSR.getInstance();      
//        
//        System.out.println(gcsr.getCSR()); // CSR sin nada de info
//        System.out.println(gcsr.getCSR("Pablo")); //CSR solo con el CN
//        System.out.println(gcsr.getCSR("Pablo","Tecnologia","Alpha Technologies","Kennedy","Pichincha","EC")); //CSR con toda la data
//        
//        System.out.println(gcsr.getPEMPrivateKey());        
//
//
//    }

}
