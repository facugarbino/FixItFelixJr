package ventanas.extra;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import graficador.modelo.Dibujable;
import graficador.modelo.InformacionDibujable;
import utils.Contador;
import utils.Posicion;
import ventanas.Ventana;

public class Nicelander implements Dibujable{

	private Color color;
	private Ventana ventana;
	private Contador timer;
	private boolean oculto;
	private Posicion posicion;
	private int tipo;

	public Nicelander(int tipo, Color color, Ventana ventana) {
		this.color = color;
		this.tipo = tipo;
		this.ventana = ventana;
		timer = new Contador(5000);
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				oculto=false;
			}
		}, 2500);
		oculto = true;
		Posicion posVentana = ventana.getPosicion();
		Posicion posPanel = Ventana.getPosiciones(2)[0];
		posicion = new Posicion(posVentana.getX()+posPanel.getX(),posVentana.getY()+posPanel.getY());
	}

	public int getTipo() {
		return tipo;
	}
	
	public Posicion getPosicion() {
		return posicion;
	}

	public boolean estaOculto() {
		return oculto;
	}

	public boolean ponerPastel() {
		if (timer.contar()) {
			ventana.ponerPastel(new Pastel(posicion));
			ventana.getSeccion().setNicelander(null);
			System.out.println("Nicelander pone pastel en " + ventana.getPosicion());
			return true;
		}
		return false;
	}

	@Override
	public InformacionDibujable getInformacionDibujable() {
		return new InformacionDibujable(ventana.getPosicion().getX(), ventana.getPosicion().getY(),'@', color);
	}
}
