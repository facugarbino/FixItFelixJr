package paquete;

import java.awt.Point;

public class FelixJr extends Personaje {
	private int vidas;
	private Ventana ventanaActual;

	public FelixJr(Point p, Ventana v) {

	}

	public void darMartillazo() {

	}

	public void mover(Orientacion o) {
		if (!ventanaActual.tieneObstaculo(o)) {
			v = ventanaActual.getVentana(o);
			switch (o) {
			case IZQUIERDA:
				posicion.x -= 10;
				break;
			case DERECHA:
				posicion.x += 10;
				break;
			case ABAJO:
				posicion.y -= 10;
				break;
			case ARRIBA:
				posicion.y += 10;
				break;
			}
		}
	}

	public void golpear(Ladrillo l) {

	}

	public void golpear(Pajaro p) {

	}

	public int getVidas() {
		return vidas;
	}

	private void inmunizar() {

	}

	public void chequearInmunizacion() {

	}

}
