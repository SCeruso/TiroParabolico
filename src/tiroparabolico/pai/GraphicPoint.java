package tiroparabolico.pai;
/**
 * 
 * @author Sabato Ceruso
 * mail: sab7093@gmail.com
 * Programación de aplicaciones interactivas.
 * Universiad de La Laguna, Santa Cruz de Tenerife, España.
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javafx.geometry.Point2D;

public class GraphicPoint {
	private Point2D point;
	private Color color;
	private int radius;
	private CoordinatesTransformer transformer;
	
	public GraphicPoint(Point2D p, Color c, int r, CoordinatesTransformer ctransformer) {
		setTransformer(ctransformer);
		setPoint(p);
		setColor(c);
		setRadius(r);
		
	}

	public boolean isClicked(Point2D clickPos) {
		if (getRadius() > getPoint().distance(clickPos))
			return true;
		else 
			return false;
	}
	public void drawPoint(Graphics g) {
		g.setColor(color);
		g.fillOval((int)(getPoint().getX() - getRadius() * getTransformer().getEscala()), (int)(getPoint().getY() - getRadius() * getTransformer().getEscala()), 
				(int)(getRadius() * 2 * getTransformer().getEscala()), (int)(getRadius() * 2 * getTransformer().getEscala()));
	}
	public Point2D getPoint() {
		return point;
	}

	public void setPoint(Point2D point) {
		this.point = getTransformer().transform(point);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public CoordinatesTransformer getTransformer() {
		return transformer;
	}

	public void setTransformer(CoordinatesTransformer transformer) {
		this.transformer = transformer;
	}
	
}
