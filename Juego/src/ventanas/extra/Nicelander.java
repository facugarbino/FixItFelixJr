package ventanas.extra;

import java.awt.Color;

import utils.Contador;
import ventanas.Ventana;

public class Nicelander {

	private Color color;
	private Ventana ventana;
	private Contador timer;

	public Nicelander(Color color, Ventana ventana) {
		this.color = color;
		this.ventana = ventana;
		timer = new Contador(500);
	}

	public boolean ponerPastel() {
		if (timer.contar()) {
			ventana.ponerPastel(new Pastel());
			ventana.getSeccion().setNicelander(false);
			System.out.println("Nicelander pone pastel en " + ventana.getPosicion());
			return true;
		}
		return false;
	}
}
