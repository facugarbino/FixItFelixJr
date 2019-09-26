package paquete;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

public class VentanaComun extends Ventana {

	public static final int CANT_PANELES = 2;
	Timer timer = new Timer(Math.random() * 75 + 25);

	public VentanaComun(Point posicion, Seccion seccion, boolean estaRoto) {
		this.posicion = posicion;
		this.seccion = seccion;
		paneles = new ArrayList<>();
		if (estaRoto) {
			paneles = getPanelesRandom(CANT_PANELES);
		} else {
			for (int i = 0; i < CANT_PANELES; i++) {
				paneles.add(new Panel(new Sano()));
			}
		}
	}

	public boolean generarNicelander() {

		if (elDeAbajoEstaRoto() && timer.contar()) {
			nicelander = new Nicelander(
					new Color((float) Math.random() * 255, (float) Math.random() * 255, (float) Math.random() * 255),
					this);
			return true;
		}
		return false;
	}

	private boolean elDeAbajoEstaRoto() {
		return (paneles.get(0).estaRoto());
	}
}
