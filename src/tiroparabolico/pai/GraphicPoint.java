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
/**
 * Clase ocupada de dibujar un punto siguiendo una transformacion de coordenadas.
 * @author Sabato
 *
 */
public class GraphicPoint {
	private Point2D point;								// Coordenadas reales del punto a dibujar (luego de aplicar transformacion).
	private Color color;								// Color.
	private int radius;									// Radio.
	private CoordinatesTransformer transformer;			// Transformador de coordenadas.
	
	public GraphicPoint(Point2D p, Color c, int r, CoordinatesTransformer ctransformer) {
		setTransformer(ctransformer);
		setPoint(p);
		setColor(c);
		setRadius(r);
		
	}

	/**
	 * True si el punto ha sido clickeado por las coordenadas del clickPos.
	 * @param clickPos
	 * @return
	 */
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
	
	/**
	 * Getters y setters.
	 * @return
	 */
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
