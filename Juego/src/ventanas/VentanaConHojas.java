package ventanas;

import java.util.ArrayList;

import juego.Seccion;
import utils.Orientacion;
import utils.Posicion;
import ventanas.obstaculos.HojaDerecha;
import ventanas.obstaculos.HojaIzquierda;
import ventanas.paneles.Panel;
import ventanas.paneles.Sano;

public class VentanaConHojas extends Ventana {

	public static final int CANT_PANELES = 2;
	private boolean cerrada;

	public VentanaConHojas(Posicion posicion, Seccion seccion, boolean tieneObstaculo) {
		Posicion posiciones[] = getPosicionPaneles(2);
		this.posicion = posicion;
		this.seccion = seccion;
		panelesReparados = 0;
		cantMartillazos = 0;
		cerrada = !tieneObstaculo;
		paneles = new ArrayList<>();
		for (int i = 0; i < CANT_PANELES; i++) {
			paneles.add(new Panel(new Sano(), posiciones[i]));
		}
		
		if (!cerrada) {
			if (Math.random() < 0.5) {
				obstaculos.add(new HojaIzquierda());
			} else {
				obstaculos.add(new HojaDerecha());
			}
		}
	}

	public boolean estaCerrada() {
		return cerrada;
	}

	public boolean tieneHojaIzquierda() {
		if (obstaculos.isEmpty()) {
			return false;
		} else {
			return obstaculos.get(0).estasEn(Orientacion.IZQUIERDA);
		}
	}

	public boolean tieneHojaDerecha() {
		if (obstaculos.isEmpty()) {
			return false;
		} else {
			return obstaculos.get(0).estasEn(Orientacion.DERECHA);
		}
	}

}
