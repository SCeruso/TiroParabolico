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
import java.awt.Polygon;

import javafx.geometry.Point2D;
/**
 * Clase para gestionar los aspectos graficos de los proyectiles.
 * @author Sabato
 *
 */
public class ProyectilGrafico extends Proyectil {

	private GraphicPoint grafico;					// Grafico a mostrar, en este caso un circulo.
	private double escala;							// Escala sobre la que se muestra.
	private Polygon recorrido;						// Recorrido por este proyectil.
	private boolean verRecorrido;					// True si hay que imprimir el recorrido.
	private Color colorProyectil;					// Color del proyectil.
	private Color colorTrayectoria;					// Color de la trayectoria.
	
	public final static int RADIO = 15;
	public final static double STROKE = 10;
	
	public ProyectilGrafico(Point2D pos) {
		super(new Point2D(0,0));
		setVerRecorrido(true);
		setGrafico(new GraphicPoint(new Point2D(0, 0), Color.RED, RADIO, 
				new CoordinatesTransformer((int)pos.getX(), (int)pos.getY(), getEscala())));
		setRecorrido(new Polygon());
		setEscala(SkyPanel.DEFAULT_SCALE);
		setColorProyectil(Color.RED);
		setColorTrayectoria(Color.BLUE);
	}
	/**
	 * Cambia la posicion de este proyectil; en realidad para hacer esto y mantener el centro del nuevo eje de coordenadas en la posicion inicial
	 * solo se cambia el objeto de transformar coordenadas.
	 * @param pos
	 */
	public void setPosition(Point2D pos) {
		getGrafico().setTransformer(new CoordinatesTransformer((int)
				(pos.getX() /*/ getGrafico().getTransformer().getEscala()*/), (int)(pos.getY()), getEscala()));
		getGrafico().setPoint(new Point2D(0.0, 0.0));
		setPosActual(new Point2D(0.0, 0.0));
		setPosInicial(new Point2D (0.0, 0.0));
		setVelInstantanea(getVelInicial());
		
		
	}
	public void paint(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		
		getGrafico().setPoint(getPosActual());
		if (isVerRecorrido()) {
			g.setStroke(new BasicStroke((float)(STROKE * getGrafico().getTransformer().getEscala())));
			g.setColor(getColorTrayectoria());
			g.drawPolyline(getRecorrido().xpoints, getRecorrido().ypoints, getRecorrido().npoints);
		}
		getGrafico().drawPoint(g.create());
		
	}

	@Override
	public void actualizarEstado(Double tiempo) {
		super.actualizarEstado(tiempo);
		addPoint(getGrafico().getTransformer().transform(getPosActual()));
	}
	/**
	 * A�ade un punto al recorrido.
	 * @param point
	 */
	private void addPoint(Point2D point) {
		getRecorrido().addPoint((int)point.getX(), (int) point.getY());
	}
	/**
	 * True si hay una colision con un borde.
	 * @param width
	 * @param height
	 * @return
	 */
	public boolean borderCollision(int width, int height) {
		return verticalBorderCollision(width) || horizontalCollision(height);
	}
	/**
	 * True si hay una colision vertical.
	 * @param width
	 * @return
	 */
	public boolean verticalBorderCollision(int width) {
		return getGrafico().getPoint().getX() + getGrafico().getRadius() >= width || 
				getGrafico().getPoint().getX() - getGrafico().getRadius() <= 0;
	}
	/**
	 * True si hay una colision horizontal.
	 * @param height
	 * @return
	 */
	public boolean horizontalCollision(int height) {
		return getGrafico().getPoint().getY() + getGrafico().getRadius() >= height || 
				getGrafico().getPoint().getY() - getGrafico().getRadius() <= 0;
	}
	/**
	 * True si hay una colision con la tierra.
	 * @param height
	 * @return
	 */
	public boolean groundCollision (int height) {
		return getGrafico().getPoint().getY() + getGrafico().getRadius() >= height;
	}
	
	/**
	 * Getters y setters.
	 * @return
	 */
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
		getGrafico().getTransformer().setEscala(escala);
	}

	public Polygon getRecorrido() {
		return recorrido;
	}

	public void setRecorrido(Polygon recorrido) {
		this.recorrido = recorrido;
	}

	public boolean isVerRecorrido() {
		return verRecorrido;
	}

	public void setVerRecorrido(boolean verRecorrido) {
		this.verRecorrido = verRecorrido;
	}

	public Color getColorProyectil() {
		return colorProyectil;
	}

	public void setColorProyectil(Color colorProyectil) {
		this.colorProyectil = colorProyectil;
		getGrafico().setColor(colorProyectil);
	}

	public Color getColorTrayectoria() {
		return colorTrayectoria;
	}

	public void setColorTrayectoria(Color colorTrayectoria) {
		this.colorTrayectoria = colorTrayectoria;
	}
	
	
}
