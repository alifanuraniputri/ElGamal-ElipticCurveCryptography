import java.awt.Point;
import java.io.*;
import java.nio.file.Files;
import java.util.logging.*;


public class ElGamalECC {

	/* Cryptography */
	private Byte input[];
	private Byte output[];
	private int[] inputASCIIs;
	private int[] outputASCIIs;
	private int privateKey;
	Point publicKey;
	EllipticCurveGF elipticCurveGF;
	Point titikBasis;
	
	public ElGamalECC() {
		
	}
	
	
	public void encodeInput() {
		// TODO array of ASCII to point byte array to point
	}
	
	public void decodeInput() {
		// TODO array point to byte array
	}
	
	public void encrypt() {
		// TODO all encryption
	}
	
	public void decrypt() {
		// TODO all decryption
	}

	public int[] getInputASCIIs() {
		return inputASCIIs;
	}

	public void setInputASCIIs(int[] inputASCIIs) {
		this.inputASCIIs = inputASCIIs;
	}

	public int[] getOutputASCIIs() {
		return outputASCIIs;
	}

	public void setOutputASCIIs(int[] outputASCIIs) {
		this.outputASCIIs = outputASCIIs;
	}

	public Byte[] getInput() {
		return input;
	}

	public void setInput(Byte[] input) {
		this.input = input;
	}

	public Byte[] getOutput() {
		return output;
	}

	public void setOutput(Byte[] output) {
		this.output = output;
	}

	public int getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(int privateKey) {
		this.privateKey = privateKey;
	}


	public EllipticCurveGF getElipticCurveGF() {
		return elipticCurveGF;
	}


	public void setElipticCurveGF(EllipticCurveGF elipticCurveGF) {
		this.elipticCurveGF = elipticCurveGF;
	}


	public Point getTitikBasis() {
		return titikBasis;
	}


	public void setTitikBasis(Point titikBasis) {
		this.titikBasis = titikBasis;
	}


	public Point getPublicKey() {
		return publicKey;
	}


	public void setPublicKey(Point publicKey) {
		this.publicKey = publicKey;
	}


	
}
