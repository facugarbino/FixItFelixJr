package ventanas.extra;

import utils.Posicion;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JComponent;

import graficador.modelo.Dibujable;
import graficador.modelo.InformacionDibujable;

public class Pastel implements Dibujable {
	
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
	
	@Override
	public InformacionDibujable getInformacionDibujable() {
		return new InformacionDibujable(posicion.getX(),posicion.getY(),'ó', Color.BLACK);
	}

	public Posicion getPosicion() {
		return posicion;
	}
	
	public void comer() {
		timer.cancel();
	}
	

}
