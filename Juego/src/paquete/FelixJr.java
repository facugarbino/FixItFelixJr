package paquete;

import java.awt.Point;

public class FelixJr extends Personaje {
	private int vidas;
	private Ventana ventanaActual;
	private boolean inmune;
	private Jugador jugador;

	public FelixJr(Point p, Ventana v, Jugador j) {
		this.posicion=p;
		ventanaActual = v;
	}

	public void darMartillazo() {
		
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
			if (ventanaActual.hayPastel()) {
				ventanaActual.comerPastel();
				inmunizar();
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
