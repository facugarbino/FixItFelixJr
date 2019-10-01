package paquete;

import java.util.ArrayList;

public class VentanaPrimerPiso extends Ventana {

	public static final int CANT_PANELES = 8;
	/**
	 * 
	 * @param posicion
	 * @param seccion
	 * @param estaRota
	 */
	public VentanaPrimerPiso(Posicion posicion, Seccion seccion, boolean estaRota) {
		this.posicion = posicion;
		this.seccion = seccion;
		panelesReparados = 0;
		cantMartillazos = 0;
		paneles = new ArrayList<>();
		if (estaRota) {
			paneles = getPanelesRotosRandom(CANT_PANELES);
		} else {
			for (int i = 0; i < CANT_PANELES; i++) {
				paneles.add(new Panel(new Sano()));
			}
		}
	}
}
