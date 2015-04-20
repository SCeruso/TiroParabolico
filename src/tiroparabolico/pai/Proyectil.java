package tiroparabolico.pai;
/**
 * @author Sabato Ceruso
 * mail: sab7093@gmail.com
 * Programación de aplicaciones interactivas.
 * Universiad de La Laguna, Santa Cruz de Tenerife, España.
 */
import javafx.geometry.Point2D;

public class Proyectil {
	public static final Double GRAVEDAD = 9.8;
	private Point2D posInicial;								// Coordenadas de la posicion inicial.
	private Point2D velInicial;								// Vector de velocidad inicial.
	private Point2D posActual;								// Coordenadas posicion actual.
	private Point2D velInstantanea;							// Vector velocidad actual.
	
	public Proyectil (Point2D pos) {
		setPosActual(pos);
		setPosInicial(pos);
	}
	/**
	 * Mueve el proyectil en tantos segundos como se le indique con el parametro tiempo
	 * y actualiza su posicion y velocidad instantanea.
	 *
	 * @param tiempo
	 */
	public void actualizarEstado(Double tiempo) {
		setPosActual(EcuacionesMovimientoParabolico.posicion(getPosActual(), getVelInstantanea(), 
						tiempo, GRAVEDAD));
		setVelInstantanea(EcuacionesMovimientoParabolico.velocidadInstantanea(getVelInstantanea(), 
						tiempo, GRAVEDAD));
	
	}
	/**
	 * Obtiene la posicion que tendria este proyectil en el instante t = tiempo.
	 * @param tiempo
	 * @return
	 */
	public Point2D getPosicion(Double tiempo) {
		return EcuacionesMovimientoParabolico.posicion(getPosInicial(), getVelInicial(), tiempo, GRAVEDAD);
	}
	/**
	 * Cambia la velociodad inicial a partir de un angulo y el modulo.
	 * @param angulo
	 * @param modulo
	 */
	public void setVelInicial(Double angulo, Double modulo) {
		Double coordX = Math.cos(Math.toRadians(angulo)) * modulo;
		Double coordY = Math.sin(Math.toRadians(angulo)) * modulo;
		
		this.velInicial = new Point2D(coordX, coordY);
		setVelInstantanea(getVelInicial());
	}
	/**
	 * Cambia el angulo de la velocidad inicial.
	 * @param angulo
	 */
	public void setAngulo(Double angulo) {
		Double mod = Math.sqrt(Math.pow(getVelInicial().getX(), 2) +  Math.pow(getVelInicial().getY(), 2));
		setVelInicial(angulo, mod);
	}
	/**
	 * Obtiene el modulo de la velocidad inicial.
	 * @return
	 */
	public Double getModuloVelInicial() {
		return getVelInicial().magnitude();
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
