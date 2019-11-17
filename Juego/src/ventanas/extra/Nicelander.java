package ventanas.extra;

import java.awt.Color;
import utils.Contador;
import utils.Posicion;
import ventanas.Ventana;

/**
 * Representa a los Nicelanders que se asoman por las
 * ventanas a dejarle un pastel a Felix
 * 
 * @author Garbino y Rodriguez Murphy
 *
 */
public class Nicelander  {

	private Ventana ventana;
	private Contador timer;
	private boolean oculto;
	private Posicion posicion;
	private int tipo;
	private Contador timer2;

	public Nicelander(int tipo, Color color, Ventana ventana) {
		this.tipo = tipo;
		this.ventana = ventana;

		timer = new Contador(5000);
		timer2 = new Contador(2500);
		oculto = true;
		Posicion posVentana = ventana.getPosicion();
		Posicion posPanel = Ventana.getPosicionPaneles(2)[0];
		posicion = new Posicion(posVentana.getX() + posPanel.getX(), posVentana.getY() + posPanel.getY());
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
		if (timer2.contar()) {
			oculto = false;
		}
		if (timer.contar()) {
			ventana.ponerPastel(new Pastel(posicion));
			ventana.getSeccion().setNicelander(null);
			System.out.println("Nicelander pone pastel en " + ventana.getPosicion());
			return true;
		}

		return false;
	}

	public void pausar() {
		timer.pausar();
		timer2.pausar();
	}
}
