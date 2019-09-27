package paquete;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

public class VentanaComun extends Ventana {

	public static final int CANT_PANELES = 2;
	Contador timer = new Contador(Math.random() * 75 + 25);

	public VentanaComun(Posicion posicion, Seccion seccion, boolean estaRoto, boolean tieneObstaculo) {
		this.posicion = posicion;
		this.seccion = seccion;
		panelesReparados = 0;
		cantMartillazos = 0;
		paneles = new ArrayList<>();
		if (estaRoto) {
			paneles = getPanelesRotosRandom(CANT_PANELES);
		} else {
			panelesRotos = 0;
			for (int i = 0; i < CANT_PANELES; i++) {
				paneles.add(new Panel(new Sano()));
			}
		}
		if (tieneObstaculo) {
			double random = Math.random();
			if (random < (1 / 3)) {
				obstaculos.add(new Macetero());
			} else {
				if (random < (2 / 3)) {
					obstaculos.add(new Moldura());
				} else {
					obstaculos.add(new Macetero());
					obstaculos.add(new Moldura());
				}
			}
		}
	}

	public boolean generarNicelander() {

		if (elDeAbajoEstaRoto() && timer.contar()) {
			nicelander = new Nicelander(
					new Color((float) Math.random(), (float) Math.random(), (float) Math.random()),
					this);
			return true;
		}
		return false;
	}

	private boolean elDeAbajoEstaRoto() {
		return (paneles.get(0).estaRoto());
	}
}
