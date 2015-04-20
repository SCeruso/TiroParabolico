package tiroparabolico.pai;




import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JSlider;

public class TiroParabolicoPanel extends JPanel {
	private SkyPanel skyPanel;
	private GroundPanel groundPanel;
	   
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
	private void updateSizes() {
		getSkyPanel().setSize(new Dimension(getWidth(),  (int)(getHeight() * 0.8)));
	}
	   
	   
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
