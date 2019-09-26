package paquete;

import java.awt.Point;
import java.util.ArrayList;

public class VentanaConHojas extends Ventana {

	public static final int CANT_PANELES = 2;

	public VentanaConHojas(Point posicion, Seccion seccion, boolean estaRota) {
		this.posicion = posicion;
		this.seccion = seccion;
		paneles = new ArrayList<>();
		if (estaRota) {
			paneles = getPanelesRandom(CANT_PANELES);
		} else {
			for (int i = 0; i < CANT_PANELES; i++) {
				paneles.add(new Panel(new Sano()));
			}
		}
	}

}
