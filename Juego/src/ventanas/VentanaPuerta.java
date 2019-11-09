package ventanas;

import java.util.ArrayList;

import juego.Seccion;
import utils.Posicion;
import ventanas.paneles.Panel;
import ventanas.paneles.Sano;

public class VentanaPuerta extends Ventana {

	public static final int CANT_PANELES = 4;
	public static Posicion[] posiciones = {
			new Posicion(5,2),
			new Posicion(10,2),
			new Posicion(15,2),
			new Posicion(20,2)
	};

	public VentanaPuerta(Posicion posicion, Seccion seccion, boolean estaRoto) {
		this.posicion = posicion;
		this.seccion = seccion;
		panelesReparados = 0;
		cantMartillazos = 0;
		paneles = new ArrayList<>();
		caracter = 'Ω';
		if (estaRoto) {
			//caracter ='X';
			paneles = getPanelesRotosRandom(CANT_PANELES);
		} else {
			//caracter ='□';
			for (int i = 0; i < CANT_PANELES; i++) {
				paneles.add(new Panel(new Sano(), posiciones[i]));
			}
		}
	}

}