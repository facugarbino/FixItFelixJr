package componentes;

import juego.Juego;
import juego.Mapa;
import utils.Contador;
import utils.Orientacion;
import utils.Posicion;

public class Pajaro extends Componente {

	private final int ANCHO = 15;
	//sus coordenadas indican el centro
	public Pajaro(Posicion p, double frecuencia, Orientacion o, Mapa m) {
		posicion = p;
		timer = new Contador(frecuencia);
		orientacion = o;
		caracter = 'P';
		mapa = m;
	}

	/**
	 * Hace la lógica para que el pájaro se mueva,
	 * de acuerdo al sentido en que está volando, y 
	 * cambia de sentido cuando llega a los extremos
	 */
	protected void comoAvanzo() {
		if (orientacion == Orientacion.IZQUIERDA) {
			if (posicion.getX() > 0) {
				posicion.moverX(-1);
			} else {
				setOrientacion(Orientacion.DERECHA);
				comoAvanzo();
			}
		} else {
			if (posicion.getX() < (Mapa.ANCHO - (ANCHO))) {
				posicion.moverX(1);
			} else {
				setOrientacion(Orientacion.IZQUIERDA);
				comoAvanzo();
			}
		}
		if (mapa.estaFelix(posicion, ANCHO)) {
			Juego.getJuego().golpearFelix(this);
		}
	}

}
