package com.ec.alpha.utilidad;

import java.awt.RenderingHints.Key;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.google.common.base.Splitter;

public class GeneradorCertificados {

	// Extensión para ficheros encriptados
	private static final String SUFIJO_ENC = ".enc";

	// Extensión para ficheros desencriptados
	private static final String SUFIJO_DES = ".des";

	// Iteraciones para hashing
	private static final int ITERACIONES = 1000;

	private static void uso() {
		System.err.println("Uso: java RSAFicheros -c|-e|-d [fichero]");
		System.exit(1);
	}

	// Crea una clave RSA de 1024 bits y la almacena en dos ficheros
	// uno para la publica y otro para la privada (encriptada por password)
	public static void crearClave() throws Exception {
		Security.addProvider(new BouncyCastleProvider());
//	    KeyPairGenerator g = KeyPairGenerator.getInstance("ECDSA", "BC");
////		g.initialize(1024);
//	    ECGenParameterSpec spec = new ECGenParameterSpec("secp256r1");
//	    g.initialize(spec);
	    KeyPairGenerator g = KeyPairGenerator.getInstance("RSA");
		g.initialize(2048);
	    KeyPair keyPair = g.generateKeyPair();

	    byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
	    String publicKeyContent =  Base64.getEncoder().encodeToString(publicKeyBytes);
	    String publicKeyFormatted = "-----BEGIN PUBLIC KEY-----" + System.lineSeparator();
	    for (final String row:
	            Splitter
	                    .fixedLength(64)
	                    .split(publicKeyContent)
	            )
	    {
	        publicKeyFormatted += row + System.lineSeparator();
	    }
	    publicKeyFormatted += "-----END PUBLIC KEY-----";
	    
//	    privateKeyFormatted += "-----END PRIVATE KEY-----";
	    System.out.println("LLAVE PUBLICA publicKeyFormatted");
	    System.out.println(publicKeyFormatted);
//	    BufferedWriter writer = new BufferedWriter(new FileWriter("publickey.pem"));
//	    writer.write(publicKeyFormatted);
//	    writer.close();

	    byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
	    String privateKeyContent =  Base64.getEncoder().encodeToString(privateKeyBytes);
	    String privateKeyFormatted = "-----BEGIN PRIVATE KEY-----" + System.lineSeparator();
	    for (final String row:
	            Splitter
	                    .fixedLength(64)
	                    .split(privateKeyContent)
	            )
	    {
	        privateKeyFormatted += row + System.lineSeparator();
	    }
	    privateKeyFormatted += "-----END PRIVATE KEY-----";
	    System.out.println("LLAVE PRIVADA");
	    System.out.println(privateKeyFormatted);
	    
//	    BufferedWriter writer2 = new BufferedWriter(new FileWriter("privatekey.pem"));
//	    writer2.write(privateKeyFormatted);
//	    writer2.close();
	}

	// Encripta un fichero con una clave de sesión encriptada con
	// una clave publica RSA que sera leida de un fichero
	private static void encriptar(String fichEntrada) throws Exception {

		BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Fichero de la clave pública: ");
		String ficheroPublica = entrada.readLine();

		// Cargar la clave publica
		FileInputStream fis = new FileInputStream(ficheroPublica);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		int b = 0;
		while ((b = fis.read()) != -1) {
			baos.write(b);
		}
		fis.close();

		byte[] bytesClave = baos.toByteArray();
		baos.close();

		// Los bytes de clave pública en X.509 hay que transformarlos
		// en un objeto PublicKey utilizando un spec
		X509EncodedKeySpec specClave = new X509EncodedKeySpec(bytesClave);
		KeyFactory factoriaClave = KeyFactory.getInstance("RSA");
		PublicKey clavePublica = factoriaClave.generatePublic(specClave);

		// Crear e inicializar el cifrado usando la clave pública
		Cipher cifradorRSA = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cifradorRSA.init(Cipher.ENCRYPT_MODE, clavePublica);

		// Crear una clave Rijndael (simétrica) de 256 bits para encriptar el fichero.
		// Dicha clave será la clave de sesión (session-key)
		KeyGenerator generadorRijndael = KeyGenerator.getInstance("Rijndael");
		generadorRijndael.init(256);
		System.out.println("Generando la clave de sesión...");
		Key claveRijndael = (Key) generadorRijndael.generateKey();
		System.out.println("Clave generada.");

		// Encriptar la clave sesión con la clave pública del RSA.
		byte[] bytesClaveCodificados = cifradorRSA.doFinal(((java.security.Key) claveRijndael).getEncoded());

		// Abrir un fichero de salida donde escribiremos la salida de la encriptación
		String fichSalida = fichEntrada + SUFIJO_ENC;
		DataOutputStream salida = new DataOutputStream(new FileOutputStream(fichSalida));
		// Guardar la clave Rijndael (session-key) encriptada precedida de su longitud
		salida.writeInt(bytesClaveCodificados.length);
		salida.write(bytesClaveCodificados);

		// Procedemos ahora a encriptar el fichero
		// Lo primero es crear un IV para el cifrador en modo CBC
		SecureRandom aleatorio = new SecureRandom();
		byte[] iv = new byte[16];
		aleatorio.nextBytes(iv);

		// Write the IV out to the file.
		salida.write(iv);
		IvParameterSpec especificacion = new IvParameterSpec(iv);

		// Crear un cifrador Rijndael en modo CBC
		Cipher cifradorRijndael = Cipher.getInstance("Rijndael/CBC/PKCS5Padding");
		cifradorRijndael.init(Cipher.ENCRYPT_MODE, (java.security.Key) claveRijndael, especificacion);

		// Proceder a cifrar el fichero y generar la salida
		CipherOutputStream cos = new CipherOutputStream(salida, cifradorRijndael);

		System.out.println("Encriptando el fichero...");

		FileInputStream fis2 = new FileInputStream(fichEntrada);

		b = 0;
		while ((b = fis2.read()) != -1) {
			cos.write(b);
		}
		fis2.close();
		cos.close();
		System.out.println("Fichero encriptado.");
		return;
	}

	// Desencritpar el fichero con la parte privada del
	// par RSA. Dicha clave permite desencriptar la clave
	// de sesión (simétrica) y después usar esta clave
	// para desencriptar el fichero propiamente dicho.
	private static void desencriptar(String fichEntrada) throws Exception {

		// Leer el nombre del fichero que contiene la parte privada
		BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Fichero que contiene la clave privada ");
		String ficheroPrivada = entrada.readLine();

		// Pedir el password
		System.out.print("Password para la clave privada: ");
		String password = entrada.readLine();

		// Cargar los bytes de la clave encriptada con el password
		FileInputStream fis = new FileInputStream(ficheroPrivada);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		int b = 0;
		while ((b = fis.read()) != -1) {
			baos.write(b);
		}
		fis.close();

		byte[] bytesClave = baos.toByteArray();
		baos.close();

		// Aplicar PBE para obtener la clave
		bytesClave = desencriptarPBE(password.toCharArray(), bytesClave);

		// Como la clave obtenida está en PKCS#8 hay que transformarla
		// en un objeto PrivateKey,
		PKCS8EncodedKeySpec especificacion = new PKCS8EncodedKeySpec(bytesClave);
		KeyFactory factoriaRSA = KeyFactory.getInstance("RSA");
		PrivateKey clavePrivada = factoriaRSA.generatePrivate(especificacion);

		// Leer la clave sesión encriptada
		DataInputStream dis = new DataInputStream(new FileInputStream(fichEntrada));
		byte[] bytesClaveEncriptados = new byte[dis.readInt()];
		dis.readFully(bytesClaveEncriptados);

		// Crear e inicializar un cifrador RSA para proceder a desencriptar la clave.
		Cipher cifradorRSA = Cipher.getInstance("RSA/ECB/PKCS1Padding");

		// Desencriptar la clave sesión
		cifradorRSA.init(Cipher.DECRYPT_MODE, clavePrivada);
		byte[] bytesClaveRijndael = cifradorRSA.doFinal(bytesClaveEncriptados);

		// Transformar los bytes en una SecretKey
		SecretKey claveRijndael = new SecretKeySpec(bytesClaveRijndael, "Rijndael");

		// Una vez tenemos la clave simétrica ya podemos acometer el descifrado del
		// contenido. Pero antes hay que leer el IV del fichero.
		byte[] iv = new byte[16];
		dis.read(iv);
		IvParameterSpec especificacionIV = new IvParameterSpec(iv);

		// Inicializamos el descifrador
		Cipher cifradorRijndael = Cipher.getInstance("Rijndael/CBC/PKCS5Padding");
		cifradorRijndael.init(Cipher.DECRYPT_MODE, claveRijndael, especificacionIV);
		CipherInputStream cis = new CipherInputStream(dis, cifradorRijndael);

		System.out.println("Desencriptando el fichero...");
		FileOutputStream fos = new FileOutputStream(fichEntrada + SUFIJO_DES);

		// Desencriptar el fichero
		b = 0;
		while ((b = cis.read()) != -1) {
			fos.write(b);
		}
		cis.close();
		fos.close();
		System.out.println("Fichero desencriptado.");
		return;
	}

	// Utilidad para encriptar un array de bytes con un password.
	// El salto serán los 8 primeros bytes del array devuelto.
	private static byte[] encriptarPBE(char[] password, byte[] texto) throws Exception {

		// Crear el salto
		byte[] salto = new byte[8];
		Random aleatorio = new Random();
		aleatorio.nextBytes(salto);

		// Crear una clave y un cifrador PBE
		PBEKeySpec especificacion = new PBEKeySpec(password);
		SecretKeyFactory factoria = SecretKeyFactory.getInstance("PBEWithSHAAndTwofish-CBC");
		SecretKey clave = factoria.generateSecret(especificacion);
		PBEParameterSpec parametros = new PBEParameterSpec(salto, ITERACIONES);
		Cipher cifrador = Cipher.getInstance("PBEWithSHAAndTwofish-CBC");
		cifrador.init(Cipher.ENCRYPT_MODE, clave, parametros);

		// Encriptar el array
		byte[] textoCifrado = cifrador.doFinal(texto);

		// Escribir el salto seguido del texto cifrado y devolverlo.
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(salto);
		baos.write(textoCifrado);
		return baos.toByteArray();
	}

	// Utilidad para desencriptar un array de bytes con un password.
	// El salto serán los 8 primeros bytes del array que se pasa como texto cifrado.
	private static byte[] desencriptarPBE(char[] password, byte[] textoCifrado) throws Exception {

		// Leer el salto.
		byte[] salto = new byte[8];
		ByteArrayInputStream bais = new ByteArrayInputStream(textoCifrado);
		bais.read(salto, 0, 8);

		// Los bytes resultantes son el texto cifrado propiamente dicho.
		byte[] textoRestante = new byte[textoCifrado.length - 8];
		bais.read(textoRestante, 0, textoCifrado.length - 8);

		// Crear un descifrador PBE.
		PBEKeySpec especificacion = new PBEKeySpec(password);
		SecretKeyFactory factoria = SecretKeyFactory.getInstance("PBEWithSHAAndTwofish-CBC");
		SecretKey clave = factoria.generateSecret(especificacion);
		PBEParameterSpec parametros = new PBEParameterSpec(salto, ITERACIONES);
		Cipher cifrador = Cipher.getInstance("PBEWithSHAAndTwofish-CBC");

		// Realizar la desencriptación
		cifrador.init(Cipher.DECRYPT_MODE, clave, parametros);
		return cifrador.doFinal(textoRestante);
	}
}
