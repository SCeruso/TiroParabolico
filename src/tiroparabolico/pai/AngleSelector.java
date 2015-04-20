package tiroparabolico.pai;
/**
 * @author Sabato Ceruso
 * mail: sab7093@gmail.com
 * Programación de aplicaciones interactivas.
 * Universiad de La Laguna, Santa Cruz de Tenerife, España.
 */
import javafx.geometry.Point2D;
/**
 * Clase ocupada de gestionar el selector de angulos.
 * @author Sabato
 *
 */
public class AngleSelector {
	private Point2D extremo;				// Extremo del selector de angulos.
	private Point2D centro;					// Centro del selector.
	private int modulo;						// Tama�o del selector.
	private Double angulo;					// Angulo actual.
	
	public AngleSelector(Point2D centro, Point2D extremo) {
		setCentro(centro);
		setExtremo(extremo);
		
	}
	public AngleSelector(Point2D centro, Double angle, int modulo) {
		setCentro(centro);
		setModulo(modulo);
		setAngulo(angle);
	}
	/**
	 * Actualiza el extremo, es llamado cada vez que cambia el angulo.
	 */
	public void updateExtremo() {
		extremo = new Point2D(getModulo() * Math.cos(Math.toRadians(getAngulo())), 
				getModulo() * Math.sin(Math.toRadians(getAngulo())));
	}
	/**
	 * Actualiza el angulo, es llamado cada vez que cambia el extremo.
	 */
	public void updateAngle() {
		Double x = getExtremo().getX() - getCentro().getX();
		Double y = getExtremo().getY() - getCentro().getY();
		
		angulo = (Math.toDegrees(Math.atan(y / x)));
	}
	/**
	 * Getters y Setters.
	 * @return
	 */
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
	/**
	 * Coloca el angulo dibujado por la recta formada por el centro y el punto p
	 * con la paralela al eje x que pasa por el centro del selector.
	 * @param p
	 */
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
