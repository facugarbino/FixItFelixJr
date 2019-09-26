package paquete;

import java.awt.Point;

public class Pajaro extends Componente {

	final int ANCHO = 15;

	public Pajaro(Point p, int frecuencia, Orientacion o) {
		posicion = p;
		timer = new Contador(frecuencia);
		orientacion = o;
	}

	public void comoAvanzo() {
		if (orientacion == Orientacion.IZQUIERDA) {
			if (posicion.x > 0) {
				posicion.x--;
			} else {
				setOrientacion(Orientacion.DERECHA);
				comoAvanzo();
			}
		} else {
			if (posicion.x < (200 - (ANCHO))) {
				posicion.x++;
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
