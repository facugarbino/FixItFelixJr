package ventanas;

import java.util.ArrayList;

import juego.Seccion;
import utils.Posicion;
import ventanas.obstaculos.HojaDerecha;
import ventanas.obstaculos.HojaIzquierda;
import ventanas.paneles.Panel;
import ventanas.paneles.Sano;

public class VentanaConHojas extends Ventana {

	public static final int CANT_PANELES = 2;
	private boolean cerrada;

	public VentanaConHojas(Posicion posicion, Seccion seccion, boolean tieneObstaculo) {
		this.posicion = posicion;
		this.seccion = seccion;
		panelesReparados = 0;
		cantMartillazos = 0;
		cerrada = !tieneObstaculo;
		paneles = new ArrayList<>();
		//caracter ='□';
		for (int i = 0; i < CANT_PANELES; i++) {
			paneles.add(new Panel(new Sano()));
		}
		if (!cerrada) {
			if (Math.random() < 0.5) {
				obstaculos.add(new HojaIzquierda());
			} else {
				obstaculos.add(new HojaDerecha());
			}
		} else {
			caracter = '■';
		}
	}

}
