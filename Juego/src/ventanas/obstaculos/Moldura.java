package ventanas.obstaculos;

import utils.Orientacion;

public class Moldura extends Obstaculo {

	public Moldura() {
		posicion = posicionMoldura;
	}
	
	public boolean estasEn(Orientacion o) {
		return o == Orientacion.ARRIBA;
	}

}
