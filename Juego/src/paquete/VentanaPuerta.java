package paquete;

import java.util.ArrayList;

public class VentanaPuerta extends Ventana {

	public static final int CANT_PANELES = 4;

	public VentanaPuerta(Posicion posicion, Seccion seccion, boolean estaRoto) {
		this.posicion = posicion;
		this.seccion = seccion;
		paneles = new ArrayList<>();
		if (estaRoto) {
			paneles = getPanelesRotosRandom(CANT_PANELES);
		} else {
			for (int i = 0; i < CANT_PANELES; i++) {
				paneles.add(new Panel(new Sano()));
			}
		}
	}

}