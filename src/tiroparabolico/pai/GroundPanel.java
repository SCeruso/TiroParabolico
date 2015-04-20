package tiroparabolico.pai;
/**
 * @author Sabato Ceruso
 * mail: sab7093@gmail.com
 * Programación de aplicaciones interactivas.
 * Universiad de La Laguna, Santa Cruz de Tenerife, España.
 */
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Panel ocupado de dibujar el suelo.
 * @author Sabato
 *
 */
public class GroundPanel extends JPanel {
	private GroundPainter groundPainter;
	
	public GroundPanel() {
		setGroundPainter(new GroundPainter(getWidth(), getHeight()));
		
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		getGroundPainter().setWidth(getWidth());
		getGroundPainter().setHeight((int)(getHeight()));
		getGroundPainter().paint(g.create());
	}
	private GroundPainter getGroundPainter() {
		return groundPainter;
	}

	private void setGroundPainter(GroundPainter groundPainter) {
		this.groundPainter = groundPainter;
	}
	
	
}
