import java.awt.Graphics;

/**
 * Program EdytorGraficzny
 *
 * Klasa implementujca obsługę figury - elipsę.
 *
 * Autor: Bartosz Rodziewicz, 226105
 * Data: 27.11.2016
 *
 * Created by barto on 27.11.16.
 */
public class Ellipse extends Point{
	//Point center;
	private float a, b;
	
	Ellipse() {
		//center = new Point();
		super();
		a = random.nextFloat()*100;
		b = random.nextFloat()*100;
	}
	
	Ellipse(float x, float y, float a, float b) {
		//center = new Point(x,y);
		super(x,y);
		this.a = a;
		this.b = b;
	}
	
	@Override
	public boolean isInside(float px, float py) {
		return (Math.pow(x - px, 2) / (a * a)) + ((Math.pow(y - py, 2) / (b * b))) <= 1;
	}
	
	@Override
	String getName() {
		return "Ellipse(" + x + ", " + y + ", a="+a+", b="+b+")";
	}
	
	@Override
	float computeArea() {
		return (float)(a*b*Math.PI);
	}
	
	@Override
	float computePerimeter() {
		return (float)(Math.PI*((1.5*(a+b))-Math.sqrt(a*b)));
	}
	
	@Override
	void scale(float s) {
		a*=s;
		b*=s;
	}
	
	@Override
	void draw(Graphics g) {
		setColor(g);
		g.drawOval((int)(x-a),(int)(y-b), (int)(2*a), (int)(2*b));
	}
}
