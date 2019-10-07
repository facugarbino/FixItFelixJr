package ventanas.extra;

import java.awt.Color;

import graficador.modelo.Dibujable;
import graficador.modelo.InformacionDibujable;
import utils.Contador;
import ventanas.Ventana;

public class Nicelander implements Dibujable{

	private Color color;
	private Ventana ventana;
	private Contador timer;

	public Nicelander(Color color, Ventana ventana) {
		this.color = color;
		this.ventana = ventana;
		timer = new Contador(5000);
	}

	public boolean ponerPastel() {
		if (timer.contar()) {
			ventana.ponerPastel(new Pastel(ventana.getPosicion()));
			ventana.getSeccion().setNicelander(null);
			System.out.println("Nicelander pone pastel en " + ventana.getPosicion());
			return true;
		}
		return false;
	}

	@Override
	public InformacionDibujable getInformacionDibujable() {
		return new InformacionDibujable(ventana.getPosicion().getX(), ventana.getPosicion().getY(),'®', color);
	}
}
