package tiroparabolico.pai;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import javafx.geometry.Point2D;

public class ProyectilGrafico extends Proyectil {

	private GraphicPoint grafico;
	private double escala;
	private Polygon recorrido;
	
	public final static int RADIO = 15;
	
	public ProyectilGrafico(Point2D pos) {
		super(new Point2D(0,0));
		setEscala(SkyPanel.DEFAULT_SCALE);
		setEscala(1);
		setGrafico(new GraphicPoint(new Point2D(0, 0), Color.RED, RADIO, 
				new CoordinatesTransformer((int)pos.getX(), (int)pos.getY(), getEscala())));
		setRecorrido(new Polygon());
		addPoint(getGrafico().getTransformer().transform(getPosActual()));
	}
	
	public void setPosition(Point2D pos) {
		getGrafico().setTransformer(new CoordinatesTransformer((int)
				(pos.getX() /*/ getGrafico().getTransformer().getEscala()*/), (int)(pos.getY()), getEscala()));
		getGrafico().setPoint(new Point2D(0.0, 0.0));
		setPosActual(new Point2D(0.0, 0.0));
		setPosInicial(new Point2D (0.0, 0.0));
		setVelInstantanea(getVelInicial());
		
		
	}
	public void paint(Graphics g) {
		getGrafico().setPoint(getPosActual());
		getGrafico().drawPoint(g);
		
		g.drawPolyline(getRecorrido().xpoints, getRecorrido().ypoints, getRecorrido().npoints);
	}

	@Override
	public void actualizarEstado(Double tiempo) {
		super.actualizarEstado(tiempo);
		addPoint(getGrafico().getTransformer().transform(getPosActual()));
	}
	private void addPoint(Point2D point) {
		getRecorrido().addPoint((int)point.getX(), (int) point.getY());
	}
	public boolean borderCollision(int width, int height) {
		return verticalBorderCollision(width) || horizontalCollision(height);
	}
	public boolean verticalBorderCollision(int width) {
		return getGrafico().getPoint().getX() + getGrafico().getRadius() >= width || 
				getGrafico().getPoint().getX() - getGrafico().getRadius() <= 0;
	}
	public boolean horizontalCollision(int height) {
		return getGrafico().getPoint().getY() + getGrafico().getRadius() >= height || 
				getGrafico().getPoint().getY() - getGrafico().getRadius() <= 0;
	}
	public GraphicPoint getGrafico() {
		return grafico;
	}

	public void setGrafico(GraphicPoint grafico) {
		this.grafico = grafico;
	}

	public double getEscala() {
		return escala;
	}

	public void setEscala(double escala) {
		this.escala = escala;
	}

	public Polygon getRecorrido() {
		return recorrido;
	}

	public void setRecorrido(Polygon recorrido) {
		this.recorrido = recorrido;
	}
	
	
}
