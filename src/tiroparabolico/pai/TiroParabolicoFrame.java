package tiroparabolico.pai;
/**
 * @author Sabato Ceruso
 * mail: sab7093@gmail.com
 * Programación de aplicaciones interactivas.
 * Universiad de La Laguna, Santa Cruz de Tenerife, España.
 */
import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JFrame;

public class TiroParabolicoFrame extends JFrame{
	private TiroParabolicoPanel tiroParabolicoPanel;
	private MenuPanel menu;
	
	public TiroParabolicoFrame() {
		setTiroParabolicoPanel(new TiroParabolicoPanel());
		setMenu(new MenuPanel(this));
		this.add(getMenu(), BorderLayout.SOUTH);
		this.add(getTiroParabolicoPanel());
		getTiroParabolicoPanel().getSkyPanel().requestFocus();
	
		
	}
	

	public TiroParabolicoPanel getTiroParabolicoPanel() {
		return tiroParabolicoPanel;
	}

	private void setTiroParabolicoPanel(TiroParabolicoPanel tiroParabolicoPanel) {
		this.tiroParabolicoPanel = tiroParabolicoPanel;
	}


	public MenuPanel getMenu() {
		return menu;
	}


	public void setMenu(MenuPanel menu) {
		this.menu = menu;
	}
	
}
