package com.elaborato.algoritmorsa.rsaUtil;

import java.math.BigInteger;
import java.util.Random;
import java.io.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import javax.swing.*;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


public class RSA {
	/**
	 * Bit length of each prime number.
	 * (Lunghezza del bit di ogni numero primo)
	 */
	private int primeSize;

	/**
	 * Two distinct large prime numbers p and q.
	 * (Due grandi numeri primi distinti p e q)
	 */
	private BigInteger p, q;

	/**
	 * Modulus N.
	 * (Modulo N)
	 */
	private BigInteger N;

	/**
	 * r = ( p – 1 ) * ( q – 1 )
	 */
	private BigInteger r;

	/**
	 * Public exponent E and Private exponent D
	 * (Esponente pubblico E ed esponente privato D)
	 */
	private BigInteger E, D;
	

	

	private String publicKey;
	private String privateKey;
	private String randomNumber;


	/**
	 * Constructor.
	 * (Costruttore)
	 * @param primeSize Bit length of each prime number.
	 */
	public RSA(int primeSize) {

		this.primeSize=primeSize;
        // Generate two distinct large prime numbers p and q.
		// (Genera due grandi numeri primi distinti p e q)
		generatePrimeNumbers();

        // Generate Public and Private Keys.
		// (Generare chiavi pubbliche e private)
		generatePublicPrivateKeys();

		BigInteger publicKeyB = getE();
		BigInteger privateKeyB = getD();
		BigInteger randomNumberB = getN();
		publicKey = publicKeyB.toString();
		privateKey = privateKeyB.toString();
		randomNumber = randomNumberB.toString();
	}

	/**
	 * Generate two distinct large prime numbers p and q.
	 * (Genera due grandi numeri primi distinti p e q)
	 */
	public void generatePrimeNumbers() {
		// Generates a random integer prime number 
		// (Genera un numero Primo intero randomico)
		
		//Constructs a randomly generated positive BigInteger that is probably prime, 
		//with the specified bitLength
		p = new BigInteger(primeSize, 10, new Random());

		
		// Generates a random integer prime number other than the first number generated
		// (Genera un numero primo intero casuale diverso dal primo numero generato)
		do {
			q = new BigInteger(primeSize, 10, new Random());
		} while (q.compareTo(p) == 0);
	}

	/**
	 * Generate Public and Private Keys.
	 * (Generare chiavi pubbliche e private)
	 */
	public void generatePublicPrivateKeys() {
        // N = p * q
		N = p.multiply(q);

        // r = ( p – 1 ) * ( q – 1 )
		
		// subtract returns a BigInteger whose value is (p - 1)
		r = p.subtract(BigInteger.valueOf(1));
		r = r.multiply(q.subtract(BigInteger.valueOf(1))); // (p-1)(q-1)

		//
		// Public exponent E and Private exponent D
		// (Esponente pubblico E ed esponente privato D)
		
        // Choose E, coprime to and less than r
		// (Scegliere E, coprimo a e minore di r)
		do {
			E = new BigInteger(2 * primeSize, new Random());
		} while ((E.compareTo(r) != -1) || (E.gcd(r).compareTo(BigInteger.valueOf(1)) != 0));

        // Compute D, the inverse of E mod r
		// (Calcola D, l'inverso di E mod r)
		// modInverse returns a BigInteger whose value is (E ^ -1 mod m)
		D = E.modInverse(r);

	}
 
	/**
	 * Get prime number p.
	 * @return Prime number p.
	 */
	public BigInteger getp() {
		return (p);
	}

	/**
	 * Get prime number q.
	 * @return Prime number q.
	 */
	public BigInteger getq() {
		return (q);
	}

	/**
	 * Get r.
	 * @return r.
	 */
	public BigInteger getr() {
		return (r);
	}

	/**
	 * Get modulus N.
	 * @return Modulus N.
	 */
	public BigInteger getN() {
		return (N);
	}

	/**
	 * Get Public exponent E.
	 * @return Public exponent E.
	 */
	public BigInteger getE() {
		return (E);
	}

	/**
	 * Get Private exponent D.
	 * @return Private exponent D.
	 */
	public BigInteger getD() {
		return (D);
	}

	/** Encryption */
	public String RSAencrypt(String info) {

		E = new BigInteger(publicKey);
		N = new BigInteger(randomNumber);
		StringBuffer sb1 = new StringBuffer();
		String str = "";
		try {

		BigInteger[] ciphertext = encrypt(info);
		String st[] = new String[1000];
		int m[] = new int[1000];
			for (int i = 0; i < ciphertext.length; i++) {
				m[i] = ciphertext[i].intValue();
				st[i] = String.valueOf(m[i]);
				sb1.append(st[i]);
				sb1.append(" ");
				str = sb1.toString();

			}
		} catch (Exception e) {
			System.out.println(e);
			return str;
		}

		return str;
	}

	public BigInteger[] encrypt(String message) {
		int i;
		byte[] temp = new byte[1];
		byte[] digits = new byte[8];
		try {
			// Convert a String message in array of byte
			digits = message.getBytes();
		} catch (Exception e) {
			System.out.println(e);
		}
		BigInteger[] bigdigits = new BigInteger[digits.length];
		for (i = 0; i < bigdigits.length; i++) {
			temp[0] = digits[i];
			bigdigits[i] = new BigInteger(temp);
		}
		
		// modPow Returns a BigInteger whose value is ( bigdigits[i] ^ E mod m).
		BigInteger[] encrypted = new BigInteger[bigdigits.length];
		for (i = 0; i < bigdigits.length; i++)
			encrypted[i] = bigdigits[i].modPow(E, N);

		return (encrypted);
	}

	/** Decription */
	public String RSAdecrypt(String Dataencript) throws NumberFormatException {
		D = new BigInteger(privateKey);
		N = new BigInteger(randomNumber);
		 String sarray1[] = new String[100000];
		int k1 = 0;
		StringTokenizer st = new StringTokenizer(Dataencript);
		while (st.hasMoreTokens()) {
			sarray1[k1] = st.nextToken(" ");
			k1++;
		}

		BigInteger[] ciphertext1 = new BigInteger[100000];
		String recoveredPlaintext = "";

		for (int i = 0; i < sarray1.length; i++) {
			if(sarray1[i] != null) {
				ciphertext1[i] = new BigInteger(sarray1[i]);
			}
			
		}
		recoveredPlaintext = decrypt(ciphertext1, D, N, k1);
		return recoveredPlaintext;
	}

	public String decrypt(BigInteger[] encrypted, BigInteger D, BigInteger N, int size) {
		int i;
		String rs = "";
		BigInteger[] decrypted = new BigInteger[size];
		// modPow Returns a BigInteger whose value is ( encrypted[i] ^ D mod m).
		for (i = 0; i < decrypted.length; i++) {
			if(encrypted[i] != null) {
				decrypted[i] = encrypted[i].modPow(D, N);
			}
			
		}
		char[] charArray = new char[decrypted.length];
		byte[] byteArray = new byte[decrypted.length];
		for (i = 0; i < charArray.length; i++) {
			if(decrypted[i] != null) {
				charArray[i] = (char) (decrypted[i].intValue());
				Integer iv = new Integer(0);
				iv = decrypted[i].intValue();
				byteArray[i] = iv.byteValue();
			}
			
		}
		try {
			rs = new String(byteArray);
		} catch (Exception e) {
			System.out.println(e);
		}
		return (rs);
	}

	/**
	 * KeyGeneration Main program for Unit Testing.
	 */
/*	public static void main(String[] args) throws IOException {
		RSA akg = new RSA(10);
		String encryptedData = akg.RSAencrypt("Paolo");
		System.out.println("encript"+encryptedData);
		String decryptedMessage = akg.RSAdecrypt(encryptedData);
		System.out.println("decript"+decryptedMessage);

		 
	}*/

}
