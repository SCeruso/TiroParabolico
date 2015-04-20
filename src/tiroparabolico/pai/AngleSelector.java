package tiroparabolico.pai;

import javafx.geometry.Point2D;

public class AngleSelector {
	private Point2D extremo;
	private Point2D centro;
	private int modulo;
	private Double angulo;
	
	public AngleSelector(Point2D centro, Point2D extremo) {
		setCentro(centro);
		setExtremo(extremo);
		
	}
	public AngleSelector(Point2D centro, Double angle, int modulo) {
		setCentro(centro);
		setModulo(modulo);
		setAngulo(angle);
	}
	
	public void updateExtremo() {
		extremo = new Point2D(getModulo() * Math.cos(Math.toRadians(getAngulo())), 
				getModulo() * Math.sin(Math.toRadians(getAngulo())));
	}
	public void updateAngle() {
		Double x = getExtremo().getX() - getCentro().getX();
		Double y = getExtremo().getY() - getCentro().getY();
		
		angulo = (Math.toDegrees(Math.atan(y / x)));
	}
	public Point2D getExtremo() {
		return extremo;
	}
	public void setExtremo(Point2D extremo) {
		this.extremo = extremo;
		updateAngle();
		setModulo((int) getCentro().distance(extremo));
	}
	public Point2D getCentro() {
		return centro;
	}
	public void setCentro(Point2D centro) {
		this.centro = centro;
	}
	public Double getAngulo() {
		return angulo;
	}
	public void setAngulo(Double angulo) {
		this.angulo = angulo;
		updateExtremo();
	}
	
	public void setAngulo(Point2D p) {
		Double newAngle = Math.toDegrees(Math.atan(p.getY() / p.getX()));
		if (p.getX() < 0 && p.getY() > 0)
			newAngle = 180 + newAngle;
		else if (p.getX() < 0 && p.getY() < 0)
			newAngle = 180 + newAngle;
		setAngulo(newAngle);
	}
	public int getModulo() {
		return modulo;
	}
	public void setModulo(int modulo) {
		this.modulo = modulo;
	}
	
	
}
