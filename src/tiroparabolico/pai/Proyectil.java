package tiroparabolico.pai;

import javafx.geometry.Point2D;

public class Proyectil {
	public static final Double GRAVEDAD = 9.8;
	private Point2D posInicial;
	private Point2D velInicial;
	private Point2D posActual;
	private Point2D velInstantanea;
	
	public Proyectil (Point2D pos) {
		setPosActual(pos);
		setPosInicial(pos);
	}
	
	public void actualizarEstado(Double tiempo) {
		setPosActual(EcuacionesMovimientoParabolico.posicion(getPosActual(), getVelInstantanea(), 
						tiempo, GRAVEDAD));
		setVelInstantanea(EcuacionesMovimientoParabolico.velocidadInstantanea(getVelInstantanea(), 
						tiempo, GRAVEDAD));
	
	}
	public Point2D getPosicion(Double tiempo) {
		return EcuacionesMovimientoParabolico.posicion(getPosInicial(), getVelInicial(), tiempo, GRAVEDAD);
	}
	
	public void setVelInicial(Double angulo, Double modulo) {
		Double coordX = Math.cos(Math.toRadians(angulo)) * modulo;
		Double coordY = Math.sin(Math.toRadians(angulo)) * modulo;
		
		this.velInicial = new Point2D(coordX, coordY);
		setVelInstantanea(getVelInicial());
	}
	
	public void setAngulo(Double angulo) {
		Double mod = Math.sqrt(Math.pow(getVelInicial().getX(), 2) +  Math.pow(getVelInicial().getY(), 2));
		setVelInicial(angulo, mod);
	}
	/**
	 * Getters y Setter.
	 * @return
	 */
	public Point2D getPosInicial() {
		return posInicial;
	}
	public void setPosInicial(Point2D posInicial) {
		this.posInicial = posInicial;
	}
	public Point2D getVelInicial() {
		return velInicial;
	}
	public void setVelInicial(Point2D velInicial) {
		this.velInicial = velInicial;
	}
	public Point2D getPosActual() {
		return posActual;
	}
	public void setPosActual(Point2D posActual) {
		this.posActual = posActual;
	}
	public Point2D getVelInstantanea() {
		return velInstantanea;
	}
	public void setVelInstantanea(Point2D velInstantanea) {
		this.velInstantanea = velInstantanea;
	}

	
}
