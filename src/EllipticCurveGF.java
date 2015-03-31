import java.awt.Point;
import java.math.BigInteger;

public class EllipticCurveGF {

	/** y^2 = x^3 + ax + b mod p **/
	int a;
	int b;
	int p; // prime

	public EllipticCurveGF() {
		// default value
		a = 1;
		b = 6;
		p = 11;
	}

	public EllipticCurveGF(int a, int b, int p) {
		super();
		this.a = a;
		this.b = b;
		this.p = p;
	}

	// p1 + p2
	public Point penjumlahanPoin(Point p1, Point p2) {
		Point point = new Point();
		int grad = gradien(p1, p2);
		point.x = modulo((grad * grad - p1.x - p2.x), p);
		point.y = modulo((grad * (p1.x - point.x) - p1.y), p);
		return point;
	}

	// P1 - P2
	public Point penguranganPoin(Point p1, Point p2) {
		Point p2min = new Point();
		p2min.x = p2.x;
		p2min.y = modulo(-p2.y, p);
		return penjumlahanPoin(p1, p2min);
	}

	// 2P
	public Point penggandaanPoin(Point point) {
		Point point2 = new Point(point);
		if (point.y != 0) {
			int grad = gradienGanda(point);
			point2.x = modulo((grad * grad - 2 * point.x), p);
			point2.y = modulo((grad * (point.x - point2.x) - point.y), p);
		}
		return point2;
	}

	// kP, k>0
	public Point perkalianPoin(int k, Point point) {

		if (k == 1)
			return point;
		else if (k % 2 == 1)
			return penjumlahanPoin(point, perkalianPoin(k - 1, point));
		else
			return perkalianPoin(k / 2, penggandaanPoin(point));

	}

	public int gradien(Point p1, Point p2) {
		return modulo(((p1.y - p2.y) * inv_modulo((p1.x - p2.x), p)), p);
	}

	public int gradienGanda(Point point) {
		return modulo(
				((3 * point.x * point.x + a) * inv_modulo((2 * point.y), p)), p);
	}

	public int modulo(int n, int m) {
		return (n < 0) ? (n % m + m) % m : (n % m);
	}

	public int inv_modulo(int n, int m) {
		BigInteger bi1 = new BigInteger(Integer.toString(n));
		BigInteger bi2 = new BigInteger(Integer.toString(m));

		// perform modInverse operation on bi1 using bi2
		BigInteger bi3 = bi1.modInverse(bi2);
		return bi3.intValue();
	}
	
	public Point intToPoint (int n, int k){
		Point p = new Point();
		return p;
		
	}
	
	public int isInCurve(int x) {
		int y=p;
		boolean found = false;
		for (int i=0; i<p; i++) {
			if (modulo((i*i-(modulo((x*x*x + a*x + b),p)) ),p) == 0) {
				found = true;
				y=i;
				break;
			}
		}
		return y;
	}

	public static void main(String args[]) {
		Point p1 = new Point(2, 4);
		Point p2 = new Point(5, 9);
		EllipticCurveGF el = new EllipticCurveGF();
		System.out.println(el.penjumlahanPoin(p1, p2).toString());
		System.out.println(el.penggandaanPoin(p1).toString());
		System.out.println(el.perkalianPoin(3, p1));
		System.out.println(el.isInCurve(3));
	}
	
	

}
