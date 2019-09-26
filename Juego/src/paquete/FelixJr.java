package paquete;

import java.awt.Point;

public class FelixJr extends Personaje {
	private int vidas;
	private Ventana ventanaActual;
	private boolean inmune;
	private long puntajeNivel;
	private long puntajeSeccion;

	public FelixJr(Point p, Ventana v) {
		this.posicion = p;
		ventanaActual = v;
		puntajeNivel = 0;
		puntajeSeccion = 0;
	}

	public void darMartillazo() {
		if (ventanaActual.reparar()) {
			if (ventanaActual.getSeccion().estaSana()) {
				puntajeSeccion += 500;
				puntajeNivel += puntajeSeccion;
				puntajeSeccion = 0;

			} else {
				puntajeSeccion += 100;
			}
		}
	}

	public void mover(Orientacion o) {
		Ventana v = ventanaActual.getVentana(o);
		if (v != null) {
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
		vidas--;
		if (vidas > 0) {
			Juego.getJuego().reiniciarNivel();
			puntajeNivel = 0;
			puntajeSeccion = 0;
		} else {
			Juego.getJuego().perder(puntajeNivel + puntajeSeccion);
		}
	}

	public void golpear(Pajaro p) {
		Juego.getJuego().reiniciarSeccion();
		puntajeSeccion = 0;
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
			inmune = false;
		}
	}

}
