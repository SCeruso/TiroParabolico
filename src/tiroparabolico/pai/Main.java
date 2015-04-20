package tiroparabolico.pai;
/**
 * @author Sabato Ceruso
 * mail: sab7093@gmail.com
 * Programación de aplicaciones interactivas.
 * Universiad de La Laguna, Santa Cruz de Tenerife, España.
 */
import javax.swing.JFrame;


public class Main {

	public static void main(String[] args) {
		TiroParabolicoFrame frame = new TiroParabolicoFrame();
	    frame.setTitle("Tiro Parabolico");
	    //frame.setSize(800, 500);
	    frame.setLocationRelativeTo(null); // Center the frame
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    frame.setVisible(true);

	}

}
