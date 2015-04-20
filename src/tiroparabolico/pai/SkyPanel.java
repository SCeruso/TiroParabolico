package tiroparabolico.pai;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javafx.geometry.Point2D;

import javax.swing.JPanel;
import javax.swing.Timer;

public class SkyPanel extends JPanel {
	private SkyPainter sky;
	private ProyectilGrafico proyectil;
	private Timer temporizador;
	private GraphicAngleSelector selector;
	public static double DEFAULT_SCALE = 1.0;
	
	public SkyPanel () {
		DragHandler drag = new DragHandler();
		setSky(new SkyPainter(getWidth(), getHeight()));
		setProyectil(new ProyectilGrafico(new Point2D(100, 200)));
		getProyectil().setVelInicial(45.0, 100.0);
		setTemporizador(new Timer(10, new TimerHandler()));
	//	getTemporizador().start();
		setSelector(new GraphicAngleSelector(getProyectil().getGrafico().getPoint(), 
				new Point2D(200, 100), getProyectil().getGrafico().getRadius()));
		this.addMouseListener(drag);
		this.addMouseMotionListener(drag);
	}
	protected void paintComponent(Graphics g){ 
		super.paintComponent(g.create());
		updateSizes();
		getSky().paint(g.create());
		getProyectil().paint(g.create());
		getSelector().paint(g.create());
	}
	private void updateSizes() {
		getSky().setWidth(getWidth());
		getSky().setHeight((int)(getHeight() * 0.8));
	}
	private SkyPainter getSky() {
		return sky;
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

	class TimerHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//Cuidado que vamos a usar la altura!!
			if (getProyectil().horizontalCollision(getHeight())) {
				getTemporizador().stop();
				System.out.println("Choque");
			}
		
			else
				getProyectil().actualizarEstado(0.2);

			repaint();
		}
		
	}
	class DragHandler implements MouseMotionListener, MouseListener {
		boolean dragging = false;
		boolean draggingAngle = false;
		@Override
		public void mouseDragged(MouseEvent e) {
			//System.out.println("Pressed");
			
			
			if (!draggingAngle && (dragging || getProyectil().getGrafico().isClicked(new Point2D(e.getX(), e.getY())))) {
				System.out.println("Catched");
				getProyectil().setPosition(new Point2D(e.getX(), e.getY()));
				getSelector().setTransformer(new CoordinatesTransformer((int)e.getX(), (int)e.getY(), DEFAULT_SCALE));
				//getSelector().getTransformer().setEscala(1.0);
				dragging = true;
				repaint();
			}
			else if (!getTemporizador().isRunning() && !dragging) {
				draggingAngle = true;
			
				Point2D p = getSelector().getTransformer().untransform(new Point2D(e.getX(), e.getY()));
				
				getSelector().setAngulo(Math.toDegrees(Math.atan(p.getY() / p.getX())));
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
			if (getTemporizador().isRunning())
				getTemporizador().stop();
			else
				getTemporizador().start();
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
	class MouseHandler implements MouseListener {
		private int i = 0;
		@Override
		public void mouseClicked(MouseEvent arg0) {
			System.out.println("" + arg0.getX() + ", " + arg0.getY());
			repaint();
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
