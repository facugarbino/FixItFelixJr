package ventanas.extra;

import utils.Posicion;
import java.util.Timer;
import java.util.TimerTask;

public class Pastel {
	
	private Posicion posicion;
	private boolean estado;
	private Timer timer;

	public Pastel(Posicion posicion) {
		this.posicion = posicion;
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				estado = !estado;
			}
		}, 0,300);
	}

	public boolean getEstado() {
		return estado;
	}

	public Posicion getPosicion() {
		return posicion;
	}
	
	public void comer() {
		timer.cancel();
	}
	

}
