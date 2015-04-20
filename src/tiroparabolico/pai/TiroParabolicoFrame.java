package tiroparabolico.pai;

import javax.swing.JFrame;

public class TiroParabolicoFrame extends JFrame{
	private TiroParabolicoPanel tiroParabolicoPanel;

	public TiroParabolicoFrame() {
		setTiroParabolicoPanel(new TiroParabolicoPanel());
		
		this.add(getTiroParabolicoPanel());
		getTiroParabolicoPanel().getSkyPanel().requestFocus();
	}
	
	
	private TiroParabolicoPanel getTiroParabolicoPanel() {
		return tiroParabolicoPanel;
	}

	private void setTiroParabolicoPanel(TiroParabolicoPanel tiroParabolicoPanel) {
		this.tiroParabolicoPanel = tiroParabolicoPanel;
	}
}
