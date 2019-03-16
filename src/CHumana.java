import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.ParseException;

import windows.VPrincipal;


public class CHumana {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VPrincipal vp;
		try {
			vp = new VPrincipal();
			vp.setSize(new Dimension(320,320));
			vp.setVisible(true);
			vp.setResizable(false);

			// Get the size of the screen
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

			// Determine the new location of the window
			// Move the window
			vp.setLocation((dim.width-vp.getSize().width)/2, (dim.height-vp.getSize().height)/2);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		//		System.out.println( Util.shortRange((short)2,(short) 3));


	}

}
