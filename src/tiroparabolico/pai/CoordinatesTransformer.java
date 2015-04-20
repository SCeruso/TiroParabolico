package tiroparabolico.pai;

import javafx.geometry.Point2D;

public class CoordinatesTransformer {
	private int coordX;
	private int coordY;
	private double escala;
	
	public CoordinatesTransformer(int coordX, int colsize, double escala) {
		setCoordX(coordX);
		setCoordY(colsize);
		setEscala(escala);
	}
	/**
	 * Transforma del sistema ficticio al real.
	 * @param point
	 * @return
	 */
	public Point2D transform (Point2D point) {
		return new Point2D ((int)( (getEscala() *point.getX() + getCoordX())), (int)(/*getEscala() **/-point.getY()  + getCoordY()));
	}
	/**
	 * Transforma del sistema real al ficticio.
	 * @param point
	 * @return
	 */
	public Point2D untransform(Point2D point) {
		return new Point2D ((int)( (point.getX() - getCoordX()) / getEscala()), (int)(-point.getY() + getCoordY()));
	}
	public int getCoordX() {
		return coordX;
	}

	public void setCoordX(int rowSize) {
		this.coordX = rowSize;
	}

	public int getCoordY() {
		return coordY;
	}

	public void setCoordY(int colSize) {
		this.coordY = colSize;
	}
	public double getEscala() {
		return escala;
	}
	public void setEscala(double escala) {
		this.escala = escala;
	}
	
	
}
