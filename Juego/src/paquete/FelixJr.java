package paquete;

import java.awt.Point;

public class FelixJr extends Personaje {
	private int vidas;
	private Ventana ventanaActual;
	private boolean inmune;

	public FelixJr(Point p, Ventana v) {
		this.posicion=p;
		ventanaActual = v;
	}

	public boolean darMartillazo() {
		return ventanaActual.reparar();
	}

	public void mover(Orientacion o) {
		Ventana v = ventanaActual.getVentana(o);
		if (v!=null) {
			ventanaActual = v;
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
		inmune = true;
	}

	public void chequearInmunizacion() {
		if (inmune && timer.contar()) {
			timer.resetear();
			inmune=false;
		}
	}

}
