import java.awt.Point;
import java.awt.PointerInfo;
import java.util.Random;


public class ElGamalECC {

	/* Cryptography */
	private Byte input[];
	private Byte output[];
	private Point poinInput[];
	private Point poinOutput[];
	private Tuple tuples[];
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
		printInput();
		Random rand = new Random();

		int  k = rand.nextInt(ellipticCurveGF.getP()) + 1;
	    tuples = new Tuple[this.poinInput.length];
	    System.out.println(titikBasis);
	    System.out.println(Integer.toString(k));
		for (int i=0; i< poinInput.length; i++) {
			tuples[i] = new Tuple();
			tuples[i].setP1(ellipticCurveGF.perkalianPoin(k, titikBasis));
			tuples[i].setP2(ellipticCurveGF.penjumlahanPoin(poinInput[i], ellipticCurveGF.perkalianPoin(k, publicKey)));
		}
	}
	
	public void decrypt() {
		poinOutput = new Point[tuples.length];
		for (int i=0; i< tuples.length; i++) {
			poinOutput[i] = new Point();
			Point point = new Point();
			System.out.println("p1: "+tuples[i].getP1());
			point = ellipticCurveGF.perkalianPoin(privateKey, tuples[i].getP1());
			poinOutput[i] = ellipticCurveGF.penguranganPoin(tuples[i].getP2(), point);
		}
		printOutput();
		
		Point p1 = new Point(1, 2);
		System.out.println(ellipticCurveGF.perkalianPoin(2, p1).toString());
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
	
	public Byte[] fullPointToByte(Point[] poinInput) {
		int j = 0, k = 1;
		Byte[] arrayByte = new Byte[poinInput.length*2];
		for (int i=0; i<poinInput.length; i++) {
			arrayByte[j] = (byte)poinInput[i].x;
			arrayByte[k] = (byte)poinInput[i].y;
			j=+2; k=+2;
		}
		return arrayByte;
	}
	
	public Point[] byteToFullPoint(Byte[] input) {
		int k = 0;
		Point[] poin = new Point[input.length/2];
		for (int i=0, j=1; j<input.length; i=+2, j=+2) {
			poin[k].x = (int)input[i];
			poin[k].y = (int)input[j];
			k++;
		}
		return poin;
	}
	
	
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
		return tuples;
	}


	public void setTupleOutput(Tuple[] tupleOutput) {
		this.tuples = tupleOutput;
	}


	public EllipticCurveGF getEllipticCurveGF() {
		return ellipticCurveGF;
	}


	public void setEllipticCurveGF(EllipticCurveGF ellipticCurveGF) {
		this.ellipticCurveGF = ellipticCurveGF;
	}


	public Point[] getPoinOutput() {
		return poinOutput;
	}


	public void setPoinOutput(Point[] poinOutput) {
		this.poinOutput = poinOutput;
	}

	public void printInput() {
		System.out.println("_________INPUT_______");
		for (int i=0; i<poinInput.length; i++) {
			System.out.println(poinInput[i].toString());
		}
		System.out.println("_________INPUT_______");
	}
	
	public void printOutput() {
		System.out.println("_________OUTPUT_______");
		for (int i=0; i<poinOutput.length; i++) {
			System.out.println(poinOutput[i].toString());
		}
		System.out.println("_________OUTPUT_______");
	}
	
}
