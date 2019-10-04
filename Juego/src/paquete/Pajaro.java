package paquete;


public class Pajaro extends Componente {

	final int ANCHO = 15;
	//sus coordenadas indican el centro
	public Pajaro(Posicion p, int frecuencia, Orientacion o) {
		posicion = p;
		timer = new Contador(frecuencia);
		orientacion = o;
		caracter = 'P';
	}

	/**
	 * Hace la lógica para que el pájaro se mueva,
	 * de acuerdo al sentido en que está volando, y 
	 * cambia de sentido cuando llega a los extremos
	 */
	public void comoAvanzo() {
		if (orientacion == Orientacion.IZQUIERDA) {
			if (posicion.getX() > 0) {
				posicion.moverX(-1);
			} else {
				setOrientacion(Orientacion.DERECHA);
				comoAvanzo();
			}
		} else {
			if (posicion.getX() < (200 - (ANCHO))) {
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
