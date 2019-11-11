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
	private boolean hojaDerecha;
	private boolean hojaIzquierda;


	public VentanaConHojas(Posicion posicion, Seccion seccion, boolean tieneObstaculo) {
		Posicion posiciones[] = getPosiciones(2);
		this.posicion = posicion;
		this.seccion = seccion;
		panelesReparados = 0;
		cantMartillazos = 0;
		cerrada = !tieneObstaculo;
		paneles = new ArrayList<>();
		// caracter ='□';
		for (int i = 0; i < CANT_PANELES; i++) {
			paneles.add(new Panel(new Sano(), posiciones[i]));
		}
		
		if (!cerrada) {
			if (Math.random() < 0.5) {
				obstaculos.add(new HojaIzquierda());
				hojaIzquierda=true;
			} else {
				obstaculos.add(new HojaDerecha());
				hojaDerecha=true;
			}
		} else {
			caracter = '■';
		}
	}

	public boolean estaCerrada() {
		return cerrada;
	}

	public boolean tieneHojaIzquierda() {
		return hojaIzquierda;
	}

	public boolean tieneHojaDerecha() {
		return hojaDerecha;
	}

}
