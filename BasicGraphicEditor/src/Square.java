import java.awt.Graphics;

/**
 * Program EdytorGraficzny
 *
 * Klasa implementujca obsługę figury - kwadratu.
 *
 * Autor: Bartosz Rodziewicz, 226105
 * Data: 27.11.2016
 *
 * Created by barto on 27.11.16.
 */
public class Square extends Figure{
	
	private Point point1, point2, point3, point4;
	
	Square(){
		point1 = new Point();
		point2 = new Point();
		float dx = (point2.x-point1.x);
		float dy = (point2.y-point1.y);
		point3 = new Point(point1.x, point1.y);
		point4 = new Point(point2.x, point2.y);
		point3.move(-dy,dx);
		point4.move(-dy,dx);
		//JOptionPane.showMessageDialog(null,"x"+dx+" "+dy+" "+point1.toStringXY()+" "+point2.toStringXY()+" "+point3.toStringXY()+" "+point4.toStringXY()+" ");
	}
	
	@Override
	public boolean isInside(float px, float py) {
		/*float pToP1 = (float)Math.sqrt((point1.x-px)*(point1.x-px)+(point1.y-py)*(point1.y-py));
		float pToP2 = (float)Math.sqrt((point2.x-px)*(point2.x-px)+(point2.y-py)*(point2.y-py));
		float pToP3 = (float)Math.sqrt((point3.x-px)*(point3.x-px)+(point3.y-py)*(point3.y-py));
		float pToP4 = (float)Math.sqrt((point4.x-px)*(point4.x-px)+(point4.y-py)*(point4.y-py));
		float a = (float)Math.sqrt((point1.x-point2.x)*(point1.x-point2.x)+(point1.y-point2.y)*(point1.y-point2.y));
		if(pToP1+pToP2+pToP3+pToP4<=2*a+a*Math.sqrt(2)) return true;*/
		Point p = new Point(px,py);
		Triangle triangle1 = new Triangle(p, point1,point2);
		Triangle triangle2 = new Triangle(p, point2,point3);
		Triangle triangle3 = new Triangle(p, point3,point4);
		Triangle triangle4 = new Triangle(p, point1,point4);
		return triangle1.computeArea() + triangle2.computeArea() + triangle3.computeArea() + triangle4.computeArea() <= computeArea();
	}
	@Override
	String getName() {
		return "Square("+point1.toStringXY()+point2.toStringXY()+point3.toStringXY()+point4.toStringXY()+")";
	}
	
	@Override
	float getX() {
		return (point1.x+point2.x+point3.x+point4.x)/4;
	}
	
	@Override
	float getY() {
		return (point1.y+point2.y+point3.y+point4.y)/4;
	}
	
	@Override
	float computeArea() {
		return (point1.x-point2.x)*(point1.x-point2.x)+(point1.y-point2.y)*(point1.y-point2.y);
	}
	
	@Override
	float computePerimeter() {
		return (float)(4*Math.sqrt(computeArea()));
	}
	
	@Override
	void move(float dx, float dy) {
		point1.move(dx,dy);
		point2.move(dx,dy);
		point3.move(dx,dy);
		point4.move(dx,dy);
	}
	
	@Override
	void scale(float s) {
		Point sr1 = new Point((point1.x+point2.x+point3.x+point4.x)/4, (point1.y+point2.y+point3.y+point4.y)/4);
		point1.x*=s; point1.y*=s;
		point2.x*=s; point2.y*=s;
		point3.x*=s; point3.y*=s;
		point4.x*=s; point4.y*=s;
		Point sr2 = new Point((point1.x+point2.x+point3.x+point4.x)/4, (point1.y+point2.y+point3.y+point4.y)/4);
		float dx=sr1.x-sr2.x;
		float dy=sr1.y-sr2.y;
		point1.move(dx,dy);
		point2.move(dx,dy);
		point3.move(dx,dy);
		point4.move(dx,dy);
	}
	
	@Override
	void draw(Graphics g) {
		setColor(g);
		g.drawLine((int)point1.x,(int)point1.y,(int)point2.x,(int)point2.y);
		g.drawLine((int)point1.x,(int)point1.y,(int)point3.x,(int)point3.y);
		g.drawLine((int)point2.x,(int)point2.y,(int)point4.x,(int)point4.y);
		g.drawLine((int)point3.x,(int)point3.y,(int)point4.x,(int)point4.y);
	}
}
