package paquete;

import java.awt.Color;
import java.util.ArrayList;

public class VentanaComun extends Ventana {

	private static final int CANT_PANELES = 2;
	private boolean yaHuboNicelander = false;
	Contador timer = new Contador(Math.random() * 75 + 25);

	public VentanaComun(Posicion posicion, Seccion seccion, boolean estaRoto, boolean tieneObstaculo) {
		this.posicion = posicion;
		this.seccion = seccion;
		panelesReparados = 0;
		cantMartillazos = 0;
		paneles = new ArrayList<>();
		if (estaRoto) {
			caracter = 'X';
			paneles = getPanelesRotosRandom(CANT_PANELES);
		} else {
			caracter ='□';
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

	/**
	 * Decide si poner un Nicelander en el panel
	 * inferior de la ventana
	 * 
	 */
	public void generarNicelander() {
		if (!yaHuboNicelander && elDeAbajoEstaRoto() && timer.contar()) {
			nicelander = new Nicelander(
					new Color((float) Math.random(), (float) Math.random(), (float) Math.random()),
					this);
			System.out.println("Se asoma un Nicelander en la ventana "+ this.posicion);
			yaHuboNicelander = true;
		}
	}

	/**
	 * 
	 * @return si el panel inferior estÃ¡ completamente
	 * roto (si es elegible para que aparezca un Nicelander)
	 */
	private boolean elDeAbajoEstaRoto() {
		return (paneles.get(0).estaRoto());
	}
}
