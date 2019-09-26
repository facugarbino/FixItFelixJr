package paquete;

import java.awt.Point;

public class Ladrillo extends Componente {

	private int ANCHO;

	public Ladrillo(Point p, double frecuencia) {
		posicion = p;
		timer = new Timer(frecuencia);

	}

	public void comoAvanzo() {
		if (timer.contar()) {
			timer.resetear();
			posicion.y--;
		}
		if (mapa.estaFelix(posicion, ANCHO)) {
			Juego.getJuego().golpearFelix(this);
		}
	}

}
