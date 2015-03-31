import java.awt.Point;


public class Tuple {

	private Point p1;
	private Point p2;
	
	
	public Tuple() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Point getP1() {
		return p1;
	}
	public void setP1(Point p1) {
		this.p1 = p1;
	}
	public Point getP2() {
		return p2;
	}
	public void setP2(Point p2) {
		this.p2 = p2;
	}
	
	@Override
	public String toString() {
		return (p1.x + " " + p1.y + " " + p2.x + " " + p2.y);
	}
}
