package windows;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

public class Contador extends JLabel {

	Timer timer;
	int TRestante;
	JProgressBar Barra;
	VPrincipal vp;

	public Contador(int tiempo, VPrincipal vp) {
		super();
		TRestante=tiempo;
		this.setText(String.valueOf(TRestante));
		this.Barra=vp.pBTiempo;
		this.vp=vp;
		
	}

	public void start(int tiempo) {
		TRestante=tiempo;
		Barra.setMaximum(TRestante);
		Barra.setMinimum(0);
		Barra.setValue(TRestante);
		
		if(timer!=null) {			
			timer.restart();
		} else {
			ActionListener timerHandler = new TimerHandler();
			//Trigger the timer event every 1000 milliseconds
			timer = new Timer(1000,timerHandler);
			timer.start();
		}
	}

	public void stop() {
		timer.stop();
	}

	private class TimerHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//this event occurs within the Swing event-handler thread so
			//should be safe to modify visible/printable info of a Swing class

			setText(String.valueOf(--TRestante));
			Barra.setValue(TRestante);
			
			if(TRestante==0){
				timer.stop();
			//Mostrar diálogo de tiempo agotado deshabilitar el campo
				vp.mostrarDialogo(VPrincipal.MSJ_PERDIDO);
			}
			
		}
	}
}



