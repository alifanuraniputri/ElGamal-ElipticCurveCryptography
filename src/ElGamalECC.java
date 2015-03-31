import java.awt.Point;
import java.io.*;
import java.nio.file.Files;
import java.util.logging.*;


public class ElGamalECC {

	/* Cryptography */
	private String input;
	private String output;
	private int[] inputASCIIs;
	private int[] outputASCIIs;
	
	
	public ElGamalECC() {
		
	}
	
	public void encodeInput() {
		inputASCIIs = new int[input.length()];
		for (int i=0;i<input.length();i++){
				inputASCIIs[i]= (int) input.charAt(i);
		}
		// TODO array of ASCII to point
	}
	
	public void decodeInput() {
		output="";
		for (int i=0;i<outputASCIIs.length;i++) {
			output=output+Character.toString((char)(outputASCIIs[i]));
		}
		// TODO point to Array of ASCII
	}
	
	public void encrypt() {
		// TODO all encryption
	}
	
	public void decrypt() {
		// TODO all decryption
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
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
	
	
	
	
}
