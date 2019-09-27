package paquete;

public class Juego {
	private static Juego juego;
	private Ranking ranking;
	private Jugador jugador;
	private Nivel nivel;
	private Mapa mapa;
	private FelixJr felix;
	private Ralph ralph;
	private int tiempo;
	private Contador timer;

	public static void crearJuego(String nombreJugador) {
		juego = new Juego(nombreJugador);
	}

	private Juego(String nombre) {
		nivel = new Nivel(10, 10, 10, 10, 10, 2, 200, 10, 40);
		jugador = new Jugador(nombre);
		ranking = new Ranking();
		ranking.agregarHighScore(new HighScore(new Jugador("Fabian")));
		pasarDeNivel();
	}

	public static Juego getJuego() {
		return juego;
	}

	public FelixJr getFelix() {
		return felix;
	}

	public void moverFelix(Orientacion o) {
		felix.mover(o);
	}

	public void golpearFelix(Ladrillo l) {
		felix.golpear(l);
	}

	public void golpearFelix(Pajaro p) {
		felix.golpear(p);
	}

	public void darMartillazo() {
		felix.darMartillazo();
	}

	public void hacerTodo() {
		//ralph.mover();
		//ralph.tirarLadrillo();
		//mapa.getEdificio().getSeccionActual().generarNicelanders();
		//mapa.avanzarComponentes();
		checkTiempo();
	}

	public void pasarDeNivel() {
		mapa = nivel.generarMapaSiguiente();
		felix = new FelixJr(new Posicion(50, 5), mapa.getEdificio().getSeccionActual().getVentanaInicial());
		ralph = new Ralph(nivel.getCantLadrillos(), nivel.getFrecuenciaLadrillo(), nivel.getVelocidadLadrillo());
		tiempo = nivel.getTiempo();
		timer = new Contador(50);
	}

	private void checkTiempo() {
		if (timer.contar()) {
			System.out.println("Quedan "+ tiempo + " segundos");
			timer.resetear();
			tiempo--;
			if (tiempo < 1) {
				perder(felix.getPuntaje());
			}
		}
	}

	public void reiniciarNivel() {
		mapa = nivel.regenerarMapa();
	}

	public void perder(long puntajeFelix) {
		jugador.sumarPuntos(puntajeFelix);
		HighScore hs = new HighScore(jugador);
		ranking.agregarHighScore(hs);

	}

	public void reiniciarSeccion() {
		Seccion s = mapa.getEdificio().getSeccionActual();
		if (s instanceof PrimeraSeccion) {
			mapa.getEdificio().reemplazarSeccion(new PrimeraSeccion((PrimeraSeccion) s), s);
		} else {
			mapa.getEdificio().reemplazarSeccion(new Seccion(s), s);
		}
	}

}
