package tiroparabolico.pai;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javafx.geometry.Point2D;

public class GraphicAngleSelector extends AngleSelector{
	private CoordinatesTransformer transformer;
	private int distance;
	
	public GraphicAngleSelector(Point2D centro, Point2D extremo, int distance) {
		super(new Point2D(0.0,0.0), extremo);
		setTransformer(new CoordinatesTransformer((int)(centro.getX()), (int)(centro.getY()), SkyPanel.DEFAULT_SCALE));
		setExtremo(getTransformer().untransform(extremo));
		setDistance(distance);
		getTransformer().setEscala(1.0);
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		getTransformer().setEscala(1.0);
		Point2D p1 = getTransformer().transform(getExtremo());
		Point2D p2 = getTransformer().transform(new Point2D(Math.cos(Math.toRadians(getAngulo())) * getDistance(),
				Math.sin(Math.toRadians(getAngulo())) * getDistance()));
		
		g2.setStroke(new BasicStroke(3));
		g2.setColor(Color.BLACK);
		System.out.println(getAngulo());
		System.out.println("" + getExtremo().getX() + ", " + getExtremo().getY());
		g2.drawLine((int)p1.getX(), (int)p1.getY(), (int)p2.getX(), (int)p2.getY());
		
	}
	public CoordinatesTransformer getTransformer() {
		return transformer;
	}

	public void setTransformer(CoordinatesTransformer transformer) {
		this.transformer = transformer;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
	
}
