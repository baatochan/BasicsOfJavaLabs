import java.awt.Graphics;

/**
 * Program EdytorGraficzny
 *
 * Klasa implementujca obsługę figury - linii.
 *
 * Autor: Bartosz Rodziewicz, 226105
 * Data: 27.11.2016
 *
 * Created by barto on 27.11.16.
 */
public class Line extends Figure {
	Point point1, point2;
	
	Line(){
		point1 = new Point();
		point2 = new Point();
	}
	
	Line(Point p1, Point p2){
		point1=p1;
		point2=p2;
	}
	
	@Override
	public boolean isInside(float px, float py){
		if(point1.isInside(px,py)) return true;
		if(point2.isInside(px,py)) return true;
		float x1=point1.x;
		float x2=point2.x;
		float y1=point1.y;
		float y2=point2.y;
		if((Math.abs((y2-y1)*px-(x2-x1)*py+x2*y1-y2*x1)/Math.sqrt((y2-y1)*(y2-y1)+(x2-x1)*(x2-x1))) <= 6) {
			float xmax = Math.max(x1,x2);
			float xmin = Math.min(x1,x2);
			float ymax = Math.max(y1,y2);
			float ymin = Math.min(y1,y2);
			if (px<=(xmax+6)&&px>=(xmin-6)&&py<=(ymax+6)&&py>=(ymin-6))	return true;
		}
		return false;
	}
	
	@Override
	String getName(){
		return "Line("+point1.toStringXY()+point2.toStringXY()+")";
	}
	
	@Override
	float getX() {return (point1.x+point2.x)/2;}
	
	@Override
	float getY() {return (point1.y+point2.y)/2;}
	
	@Override
	float computeArea() {return 0;}
	
	@Override
	float computePerimeter() {
		float a = point1.y-point2.y; //moze byc ujemne, bo potem i tak podnosze do kwadratu
		float b = point1.x-point2.x;
		
		return (float)Math.sqrt((a*a)+(b*b));
	}
	
	@Override
	void move(float dx, float dy){
		point1.move(dx,dy);
		point2.move(dx,dy);
	}
	
	@Override
	void scale(float s){
		Point sr1 = new Point((point1.x+point2.x)/2, (point1.y+point2.y)/2);
		point1.x*=s; point1.y*=s;
		point2.x*=s; point2.y*=s;
		Point sr2 = new Point((point1.x+point2.x)/2, (point1.y+point2.y)/2);
		float dx=sr1.x-sr2.x;
		float dy=sr1.y-sr2.y;
		point1.move(dx,dy);
		point2.move(dx,dy);
	}
	
	@Override
	void draw(Graphics g){
		setColor(g);
		g.drawLine((int)point1.x, (int)point1.y, (int)point2.x, (int)point2.y);
	}
}
