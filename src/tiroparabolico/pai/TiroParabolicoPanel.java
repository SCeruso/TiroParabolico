package tiroparabolico.pai;
/**
 * @author Sabato Ceruso
 * mail: sab7093@gmail.com
 * Programación de aplicaciones interactivas.
 * Universiad de La Laguna, Santa Cruz de Tenerife, España.
 */
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JSlider;
/**
 * Panel para mostrar tanto la zona donde se movera el proyectil como
 * la zona que representa la tierra.
 * @author Sabato
 *
 */
public class TiroParabolicoPanel extends JPanel {
	private SkyPanel skyPanel;						// Panel donde se mostrara el cielo.
	private GroundPanel groundPanel;				// Panel donde se mostrara el suelo.
	   
	public TiroParabolicoPanel () {
		this.setLayout(new GridLayout(2,1));
		setSkyPanel(new SkyPanel());
		this.add(getSkyPanel());
		setGroundPanel(new GroundPanel());
		this.add(getGroundPanel());
		
	}
	
	protected void paintComponent(Graphics g){ 
		super.paintComponent(g.create());
		updateSizes();
	    
	}
	
	/**
	 * Redimensiona el panel del cielo.
	 */
	private void updateSizes() {
		getSkyPanel().setSize(new Dimension(getWidth(),  (int)(getHeight() * 0.8)));
	}
	   
	   /**
	    * Getters y setters***
	    * @return
	    */
	private GroundPanel getGroundPanel() {
		return groundPanel;
	}
	private void setGroundPanel(GroundPanel groundPanel) {
		this.groundPanel = groundPanel;
	}
	public SkyPanel getSkyPanel() {
		return skyPanel;
	}
	private void setSkyPanel(SkyPanel skyPanel) {
		this.skyPanel = skyPanel;
	}
	      
}
