package tiroparabolico.pai;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javafx.geometry.Point2D;

import javax.swing.JPanel;
import javax.swing.Timer;

public class SkyPanel extends JPanel {
	private SkyPainter sky;
	private ProyectilGrafico proyectil;
	private Timer temporizador;
	private GraphicAngleSelector selector;
	private ArrayList<ProyectilGrafico> proyectiles;
	private double escala;
	private boolean primerDisparo;
	public final static double DEFAULT_SCALE = 1.0;
	public final static double DEFAULT_TIME_UNIT = 0.2; 
	
	public SkyPanel () {
		DragHandler drag = new DragHandler();
		setPrimerDisparo(true);
		setProyectiles(new ArrayList<ProyectilGrafico>());
		setSky(new SkyPainter(getWidth(), getHeight()));
		setProyectil(new ProyectilGrafico(new Point2D(100, 200)));
		getProyectil().setVelInicial(45.0, 100.0);
		setTemporizador(new Timer(10, new TimerHandler()));
		setSelector(new GraphicAngleSelector(getProyectil().getGrafico().getPoint(), 
				new Point2D(200, 100), 4 * getProyectil().getGrafico().getRadius()));
		setEscala(DEFAULT_SCALE);
		
		
		this.addMouseListener(drag);
		this.addMouseMotionListener(drag);
	}
	protected void paintComponent(Graphics g){ 
		super.paintComponent(g.create());
		updateSizes();
		getSky().paint(g.create());	
		for (int i = 0; i < getProyectiles().size(); i++) 
			getProyectiles().get(i).paint(g.create());
		getProyectil().paint(g.create());
		getSelector().paint(g.create());
	}
	
	public void reset() {
		getProyectiles().clear();
		setProyectil(new ProyectilGrafico(new Point2D(100, 200)));
		getProyectil().setVelInicial(45.0, 100.0);
		setSelector(new GraphicAngleSelector(getProyectil().getGrafico().getPoint(), 
				new Point2D(200, 100), 4 * getProyectil().getGrafico().getRadius()));
		setPrimerDisparo(true);
		setEscala(DEFAULT_SCALE);
		repaint();
	}
	
	public void setRecorrido(boolean b) {
		getProyectil().setVerRecorrido(b);
		for (int i = 0; i < getProyectiles().size(); i++)
			getProyectiles().get(i).setVerRecorrido(b);
		repaint();
	}
	
	private void updateSizes() {
		getSky().setWidth(getWidth());
		getSky().setHeight((int)(getHeight() * 0.8));
	}
	
	private void calcularAlcance() {
		double i = DEFAULT_TIME_UNIT;
		Double x = 0.0;
		Double aux = 0.0;
		
		while( aux < this.getHeight()) {
			aux = getProyectil().getGrafico().getTransformer().transform(getProyectil().getPosicion(i)).getY();
			i = i + DEFAULT_TIME_UNIT;
		}
		
		x = getProyectil().getGrafico().getTransformer().transform(getProyectil().getPosicion(i - DEFAULT_TIME_UNIT)).getX();
	
		if (x > this.getWidth()) 
			this.setEscala((this.getWidth() - getProyectil().getGrafico().getPoint().getX())/x);
		
		else if (x < 0)
			this.setEscala((1/((this.getWidth() - x + getProyectil().getGrafico().getPoint().getX()) / this.getWidth())));
		
	}
	
	private SkyPainter getSky() {
		return sky;
	}
	
	public void setColorTrayectoria(Color color) {
		for(int i = 0; i < getProyectiles().size(); i++)
			getProyectiles().get(i).setColorTrayectoria(color);
		getProyectil().setColorTrayectoria(color);
		repaint();
	}
	
	public void setColorProyectil(Color color) {
		for(int i = 0; i < getProyectiles().size(); i++)
			getProyectiles().get(i).setColorProyectil(color);
		getProyectil().setColorProyectil(color);
		repaint();
	}
	
	public void setVelocidad(Integer modulo) {
		getProyectil().setVelInicial(getSelector().getAngulo(), (double) modulo);
	}
	private void setSky(SkyPainter sky) {
		this.sky = sky;
	}
	public ProyectilGrafico getProyectil() {
		return proyectil;
	}
	public void setProyectil(ProyectilGrafico proyectil) {
		this.proyectil = proyectil;
	}
	
	public Timer getTemporizador() {
		return temporizador;
	}
	public void setTemporizador(Timer temporizador) {
		this.temporizador = temporizador;
	}
	
	public GraphicAngleSelector getSelector() {
		return selector;
	}
	public void setSelector(GraphicAngleSelector selector) {
		this.selector = selector;
	}

	public ArrayList<ProyectilGrafico> getProyectiles() {
		return proyectiles;
	}
	public void setProyectiles(ArrayList<ProyectilGrafico> proyectiles) {
		this.proyectiles = proyectiles;
	}
	

	public boolean isPrimerDisparo() {
		return primerDisparo;
	}
	public void setPrimerDisparo(boolean primerDisparo) {
		this.primerDisparo = primerDisparo;
	}

	
	public double getEscala() {
		return escala;
	}
	public void setEscala(double escala) {
		this.escala = escala;
		getProyectil().setEscala(escala);
	}

	class TimerHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//Cuidado que vamos a usar la altura!!
			if (primerDisparo) {
				primerDisparo = !primerDisparo;
				calcularAlcance();
			}
			if (getProyectil().groundCollision(getHeight())) {
				Color color1 = getProyectil().getColorProyectil();
				Color color2 = getProyectil().getColorTrayectoria();
				
				getTemporizador().stop();
				getProyectiles().add(getProyectil());
				setProyectil(new ProyectilGrafico(getSelector().getTransformer().transform(getSelector().getCentro())));
				getProyectil().setVelInicial(getSelector().getAngulo(), 100.0);
				getProyectil().setVerRecorrido(getProyectiles().get(getProyectiles().size() - 1).isVerRecorrido());
				getProyectil().setEscala(getEscala());
				setColorProyectil(color1);
				setColorTrayectoria(color2);
				
			}
		
			else
				getProyectil().actualizarEstado(DEFAULT_TIME_UNIT);

			repaint();
		}
		
	}
	class DragHandler implements MouseMotionListener, MouseListener {
		boolean dragging = false;
		boolean draggingAngle = false;
		@Override
		public void mouseDragged(MouseEvent e) {
			if (!draggingAngle && (dragging || getProyectil().getGrafico().isClicked(new Point2D(e.getX(), e.getY())))) {
				getProyectil().setPosition(new Point2D(e.getX(), e.getY()));
				getSelector().setTransformer(new CoordinatesTransformer((int)e.getX(), (int)e.getY(), DEFAULT_SCALE));
				dragging = true;
				repaint();
			}
			else if (!getTemporizador().isRunning() && !dragging) {
				draggingAngle = true;
				getSelector().setAngulo(new Point2D(e.getX(), e.getY()));
				getProyectil().setAngulo(getSelector().getAngulo());
				repaint();
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			dragging = false;
			draggingAngle = false;
			
		}
		
	}
}
