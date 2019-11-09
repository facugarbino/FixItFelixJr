package ventanas;

import java.awt.Color;
import java.util.ArrayList;

import juego.Seccion;
import utils.Contador;
import utils.Posicion;
import ventanas.extra.Nicelander;
import ventanas.extra.Pastel;
import ventanas.obstaculos.Macetero;
import ventanas.obstaculos.Moldura;
import ventanas.paneles.Panel;
import ventanas.paneles.Sano;

public class VentanaComun extends Ventana {

	private static final int CANT_PANELES = 2;
	private boolean yaHuboNicelander = false;
	Contador timer = new Contador((int)(Math.random() * 5000 + 5000));

	public VentanaComun(Posicion posicion, Seccion seccion, boolean estaRoto, boolean tieneObstaculo) {
		Posicion posiciones[] = getPosiciones(2);
		this.posicion = posicion;
		this.seccion = seccion;
		panelesReparados = 0;
		cantMartillazos = 0;
		paneles = new ArrayList<>();
		if (estaRoto) {
			//caracter = 'X';
			paneles = getPanelesRotosRandom(CANT_PANELES);
		} else {
			//caracter = '□';
			panelesRotos = 0;
			for (int i = 0; i < CANT_PANELES; i++) {
				paneles.add(new Panel(new Sano(), posiciones[i]));
			}
		}
		
		if (tieneObstaculo) {
			double random = Math.random();
			if (random < 0.33) {
				obstaculos.add(new Macetero());
			} else {
				if (random < 0.66) {
					obstaculos.add(new Moldura());
				} else {
					obstaculos.add(new Macetero());
					obstaculos.add(new Moldura());
				}
			}
		}
	}

	/**
	 * Decide si poner un Nicelander en el panel inferior de la ventana
	 * 
	 */
	public void generarNicelander() {
		if (!seccion.hayNicelander() && !yaHuboNicelander && elDeAbajoEstaRoto() && timer.contar()) {
			nicelander = new Nicelander( (int) (Math.random()*3+1) ,new Color((float) Math.random(), (float) Math.random(), (float) Math.random()),
					this);
			seccion.setNicelander(nicelander);
			yaHuboNicelander = true;
			System.out.println("Se asoma un Nicelander en la ventana " + this.posicion);
			//caracter = '®';
		} else {
			if (nicelander != null) {
				if (nicelander.ponerPastel()) {
					nicelander = null;
				}
			} else if (seccion.hayNicelander()) {
				timer.resetear();
			}
		}
	}

	public void resetTimer() {
		timer.resetear();
	}

	/**
	 * 
	 * @return si el panel inferior estÃ¡ completamente roto (si es elegible para
	 *         que aparezca un Nicelander)
	 */
	private boolean elDeAbajoEstaRoto() {
		return (paneles.get(0).estaRoto());
	}
}
