package paquete;

import java.util.ArrayList;

public class VentanaConHojas extends Ventana {

	public static final int CANT_PANELES = 2;
	private boolean cerrada;

	public VentanaConHojas(Posicion posicion, Seccion seccion, boolean tieneObstaculo) {
		this.posicion = posicion;
		this.seccion = seccion;
		boolean estaCerrada = !tieneObstaculo;
		paneles = new ArrayList<>();
		cerrada = estaCerrada;
		for (int i = 0; i < CANT_PANELES; i++) {
			paneles.add(new Panel(new Sano()));
		}
		if (!estaCerrada) {
			if (Math.random() < 0.5) {
				obstaculos.add(new HojaIzquierda());
			} else {
				obstaculos.add(new HojaDerecha());
			}
		}
	}

}
