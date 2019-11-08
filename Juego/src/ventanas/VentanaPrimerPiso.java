package ventanas;

import java.util.ArrayList;

import juego.Seccion;
import utils.Posicion;
import ventanas.paneles.Panel;
import ventanas.paneles.Sano;

public class VentanaPrimerPiso extends Ventana {

	public static final int CANT_PANELES = 8;
	public static Posicion[] posiciones = getPosiciones(8);
	
	public VentanaPrimerPiso(Posicion posicion, Seccion seccion, boolean estaRota) {
		this.posicion = posicion;
		this.seccion = seccion;
		panelesReparados = 0;
		cantMartillazos = 0;
		paneles = new ArrayList<>();
		caracter = 'Π';
		if (estaRota) {
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
