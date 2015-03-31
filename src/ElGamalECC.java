import java.awt.Point;
import java.util.Random;


public class ElGamalECC {

	/* Cryptography */
	private Byte input[];
	private Byte output[];
	private Point poinInput[];
	private Tuple tupleOutput[];
	private Point poinOutput[];
	private int[] inputASCIIs;
	private int[] outputASCIIs;
	private int privateKey;
	private Point publicKey;
	private EllipticCurveGF ellipticCurveGF;
	private Point titikBasis;

	
	public ElGamalECC() {
		ellipticCurveGF = new EllipticCurveGF();
		
	}
	
	
	public void encrypt() {
		Random rand = new Random();

		int  k = rand.nextInt(ellipticCurveGF.getP()) + 1;
	    tupleOutput = new Tuple[this.poinInput.length];
	    System.out.println(titikBasis);
	    System.out.println(Integer.toString(k));
		for (int i=0; i< poinInput.length; i++) {
			tupleOutput[i].setP1(ellipticCurveGF.perkalianPoin(k, titikBasis));
			tupleOutput[i].setP2(ellipticCurveGF.penjumlahanPoin(poinInput[i], ellipticCurveGF.perkalianPoin(k, publicKey)));
		}
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
	
	public byte[] fullPointToByte(Point[] poinInput) {
		int j = 0, k = 1;
		byte[] arrayByte = new byte[poinInput.length*2];
		for (int i=0; i<poinInput.length; i++) {
			arrayByte[j] = (byte)poinInput[i].x;
			arrayByte[k] = (byte)poinInput[i].y;
			j=+2; k=+2;
		}
		return arrayByte;
	}
	
	/*public byte[] byteTo FullPoint(byte[] input) {
		int j = 0, k = 1;
		byte[] poin = new byte[input.length/2];
		for (int i=0; i<poinInput.length; i++) {
			arrayByte[j] = (byte)poinInput[i].x;
			arrayByte[k] = (byte)poinInput[i].y;
			j=+2; k=+2;
		}
		return arrayByte;
	}*/
	
	
	public Point[] getPoinInput() {
		return poinInput;
	}

	public void setPoinInput(Point[] PoinInput) {
		this.poinInput = new Point[PoinInput.length];
		System.arraycopy( PoinInput, 0, this.poinInput, 0, PoinInput.length );
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


	public Tuple[] getTupleOutput() {
		return tupleOutput;
	}


	public void setTupleOutput(Tuple[] tupleOutput) {
		this.tupleOutput = tupleOutput;
	}


	public Point[] getPoinOutput() {
		return poinOutput;
	}


	public void setPoinOutput(Point[] poinOutput) {
		this.poinOutput = poinOutput;
	}


	public EllipticCurveGF getEllipticCurveGF() {
		return ellipticCurveGF;
	}


	public void setEllipticCurveGF(EllipticCurveGF ellipticCurveGF) {
		this.ellipticCurveGF = ellipticCurveGF;
	}

	
	
}
