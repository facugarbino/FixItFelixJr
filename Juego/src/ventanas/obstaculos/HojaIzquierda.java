package ventanas.obstaculos;

import utils.Orientacion;

public class HojaIzquierda extends Obstaculo {

	public HojaIzquierda() {
		posicion = posicionHojaIzquierda;
	}
	public boolean estasEn(Orientacion o) {
		return o == Orientacion.IZQUIERDA;
	}

}
