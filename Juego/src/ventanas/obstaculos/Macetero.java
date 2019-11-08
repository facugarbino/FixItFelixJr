package ventanas.obstaculos;

import utils.Orientacion;

public class Macetero extends Obstaculo {
	public Macetero() {
		posicion = posicionMacetero;
	}

	public boolean estasEn(Orientacion o) {
		return o == Orientacion.ABAJO;
	}

}
