package paquete;

import java.awt.Point;
import java.util.ArrayList;

public class VentanaPrimerPiso extends Ventana {

	public static final int CANT_PANELES = 8;

	public VentanaPrimerPiso(Point posicion, Seccion seccion, boolean estaRoto) {
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
