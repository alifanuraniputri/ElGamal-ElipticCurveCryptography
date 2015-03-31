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
	private int publicKey;
	private EllipticCurveGF ellipticCurveGF;
	
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
		byte[] arrayByte = new byte[poinInput.length*2];
		for (int i=0,j=1; j<arrayByte.length; i+2, j+2) {
			arrayByte[i] = 
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

	public int getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(int publicKey) {
		this.publicKey = publicKey;
	}
	public EllipticCurveGF getEllipticCurve() {
		return ellipticCurveGF;
	}

	public void setEllipticCurve(EllipticCurveGF ellipticCurveGF) {
		this.ellipticCurveGF = ellipticCurveGF;
	}
	
	
	
	
}
