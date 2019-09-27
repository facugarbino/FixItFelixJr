package paquete;

public class FelixJr extends Personaje {
	private int vidas;
	private Ventana ventanaActual;
	private boolean inmune;
	private long puntajeNivel;
	private long puntajeSeccion;

	public FelixJr(Posicion p, Ventana v) {
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
				System.out.println("Felix repara el ultimo panel de la seccion. Gana 500 puntos.");
			} else {
				puntajeSeccion += 100;
				System.out.println("Felix repara un panel. Gana 100 puntos.");
			}
		}
	}

	public void mover(Orientacion o) {
		Ventana v = ventanaActual.getVentana(o);
		if (v != null) {
			ventanaActual = v;
			switch (o) {
			case IZQUIERDA:
				posicion.moverX(-15);
				break;
			case DERECHA:
				posicion.moverX(15);
				break;
			case ABAJO:
				posicion.moverY(-30);
				break;
			case ARRIBA:
				posicion.moverY(30);
				break;
			}
			System.out.println("Felix se mueve a la posicion " + getPosicion());
			if (ventanaActual.estaRota()) {
				System.out.println(ventanaActual.getPosicion()+"La ventana esta rota");
			} else {
				System.out.println(ventanaActual.getPosicion()+"La ventana esta SANA");
			}
			if (ventanaActual.hayPastel()) {
				ventanaActual.comerPastel();
				inmunizar();
			}
		} else {
			System.out.println("Felix no se puede mover a la " + o + ". Hay un obstaculo");
		}
	}

	public void golpear(Ladrillo l) {
		vidas--;
		if (vidas > 0) {
			Juego.getJuego().reiniciarNivel();
			puntajeNivel = 0;
			puntajeSeccion = 0;
			System.out.println("Felix es golpeado por un ladrillo. Pierde una vida. (Quedan " + vidas + " )");
		} else {
			Juego.getJuego().perder(puntajeNivel + puntajeSeccion);
			System.out.println("Felix es golpeado por un ladrillo. Game over");
		}
	}

	public void golpear(Pajaro p) {
		Juego.getJuego().reiniciarSeccion();
		puntajeSeccion = 0;
		System.out.println("Felix es golpeado por un pajaro, reinicia la seccion");
	}

	public int getVidas() {
		return vidas;
	}

	private void inmunizar() {
		inmune = true;
		System.out.println("Felix come un pastel. Se inmuniza.");
	}

	public void chequearInmunizacion() {
		if (inmune && timer.contar()) {
			timer.resetear();
			inmune = false;
			System.out.println("Felix deja de ser inmune");
		}
	}

	public long getPuntaje() {
		return (puntajeNivel + puntajeSeccion);
	}

}
