import java.awt.Point;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.util.logging.*;


public class ElGamalECC {

	/* Cryptography */
	private Byte input[];
	private Byte output[];
	private Point PoinInput[];
	private Point PoinOutput[];
	private int[] inputASCIIs;
	private int[] outputASCIIs;
	private int privateKey;
	private Point publicKey;
	private EllipticCurveGF ellipticCurveGF;
	private Point titikBasis;

	
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
	
	/*public byte[] fullPointToByte(Point[] poinInput) {
		int j = 0, k = 1;
		byte[] arrayByte = new byte[poinInput.length*2];
		for (int i=0; i<poinInput.length; i++) {
			arrayByte[j] = 
		}
	}*/
	
	public Point[] getPoinInput() {
		return PoinInput;
	}

	public void setPoinInput(Point[] PoinInput) {
		this.PoinInput = PoinInput;
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

	public EllipticCurveGF getEllipticCurve() {
		return ellipticCurveGF;
	}

	public void setEllipticCurve(EllipticCurveGF ellipticCurveGF) {
		this.ellipticCurveGF = ellipticCurveGF;
	}

	
}
