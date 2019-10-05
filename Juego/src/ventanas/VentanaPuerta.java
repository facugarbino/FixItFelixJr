package ventanas;

import java.util.ArrayList;

import juego.Seccion;
import utils.Posicion;
import ventanas.paneles.Panel;
import ventanas.paneles.Sano;

public class VentanaPuerta extends Ventana {

	public static final int CANT_PANELES = 4;

	public VentanaPuerta(Posicion posicion, Seccion seccion, boolean estaRoto) {
		this.posicion = posicion;
		this.seccion = seccion;
		panelesReparados = 0;
		cantMartillazos = 0;
		paneles = new ArrayList<>();
		if (estaRoto) {
			caracter ='X';
			paneles = getPanelesRotosRandom(CANT_PANELES);
		} else {
			caracter ='□';
			for (int i = 0; i < CANT_PANELES; i++) {
				paneles.add(new Panel(new Sano()));
			}
		}
	}

}