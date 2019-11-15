package animaciones;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import juego.Juego;
import personajes.FelixJr;

public class AnimacionGolpeFelix implements Runnable {

	private FelixJr felix = Juego.getInstance().getFelix();
	private boolean termino = false;
	private Timer timer; 
	public void run() {
		Juego.getInstance().pausar();
		timer = new Timer();
		felix.asustarse();
		timer = new Timer();
		int aDondeDeboLlegar = Juego.getInstance().getMapa().getEdificio().getSeccionActual().getPosicion().getY()-20;
		timer.schedule(new TimerTask() {
			boolean bajar=false;
			int cuantoSubi = 0;
			@Override
			public void run() {
				if (bajar) {
					felix.getPosicion().moverY(-2);
				} else {
					felix.getPosicion().moverY(1);
					if (cuantoSubi++==15) {
						bajar=true;
					}
				}
				if (felix.getPosicion().getY()<aDondeDeboLlegar) {
					timer.cancel();
					termino=true;
					felix.asustarse();
				}
			}
		}, 0,10);
		while(!termino) {
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Juego.getInstance().pausar();
	}

}
