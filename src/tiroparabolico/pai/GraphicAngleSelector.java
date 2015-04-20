package tiroparabolico.pai;
/**
 * @author Sabato Ceruso
 * mail: sab7093@gmail.com
 * Programación de aplicaciones interactivas.
 * Universiad de La Laguna, Santa Cruz de Tenerife, España.
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javafx.geometry.Point2D;
/**
 * Clase ocupada de dibujar el selector de angulos.
 * @author Sabato
 *
 */
public class GraphicAngleSelector extends AngleSelector{
	private CoordinatesTransformer transformer;				// Transformador de coordenadas.
	private int distance;									// Distancia con el centro a partirde la que dibujar.
	
	public GraphicAngleSelector(Point2D centro, Point2D extremo, int distance) {
		super(new Point2D(0.0,0.0), extremo);
		setTransformer(new CoordinatesTransformer((int)(centro.getX()), (int)(centro.getY()), SkyPanel.DEFAULT_SCALE));
		setExtremo(getTransformer().untransform(extremo));
		setDistance(distance);
		getTransformer().setEscala(1.0);
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		getTransformer().setEscala(SkyPanel.DEFAULT_SCALE);
		Point2D p1 = getTransformer().transform(getExtremo());
		Point2D p2 = getTransformer().transform(new Point2D(Math.cos(Math.toRadians(getAngulo())) * getDistance(),
				Math.sin(Math.toRadians(getAngulo())) * getDistance()));
		
		g2.setStroke(new BasicStroke(3));
		g2.setColor(Color.BLACK);
		g2.drawLine((int)p1.getX(), (int)p1.getY(), (int)p2.getX(), (int)p2.getY());
		
	}
	/**
	 * Getters y setters.
	 * @return
	 */
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
	
	public void setAngulo (Point2D p) {
		Point2D p2 =getTransformer().untransform(new Point2D(p.getX(), p.getY()));
		super.setAngulo(p2);
	}
}
