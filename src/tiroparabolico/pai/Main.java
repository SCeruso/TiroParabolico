package tiroparabolico.pai;

import javax.swing.JFrame;


public class Main {

	public static void main(String[] args) {
		TiroParabolicoFrame frame = new TiroParabolicoFrame();
	    frame.setTitle("Tiro Parabolico");
	    frame.setSize(500, 500);
	    frame.setLocationRelativeTo(null); // Center the frame
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);

	}

}
