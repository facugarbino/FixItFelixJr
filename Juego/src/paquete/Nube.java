package paquete;

import java.awt.Point;

public class Nube extends Componente {

	private int ANCHO = 30;

	public Nube(Point p, double frecuencia) {
		posicion = p;
		timer = new Timer(frecuencia);
		orientacion = Orientacion.DERECHA;

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
	}

}
