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
	private boolean pausa;

	public static void crearJuego(String nombreJugador) {
		juego = new Juego(nombreJugador);
		System.out.println("Comienza el juego. Juega: " + nombreJugador);
	}

	private Juego(String nombre) {
		nivel = new Nivel(10, 15, 10, 10, 10, 5, 200, 10, 40);
		jugador = new Jugador(nombre);
		ranking = new Ranking();
		ranking.agregarHighScore(new HighScore(new Jugador("Fabian")));
		pasarDeNivel();
		pausa = false;
	}

	public boolean estaPausado() {
		return !pausa;
	}

	public void pausar() {
		if (pausa) {
			System.out.println("JUEGO DESPAUSADO");
		} else {
			System.out.println("JUEGO PAUSADO");
		}
		pausa = !pausa;

	}

	public void resetearTiempo() {
		tiempo = nivel.getTiempo();
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
		ralph.mover();
		Ladrillo l = ralph.tirarLadrillo();
		if (l!=null) {
			mapa.agregarComponente(l);
		}
		mapa.getEdificio().getSeccionActual().generarNicelanders();
		mapa.avanzarComponentes();
		checkTiempo();
	}

	public void pasarDeNivel() {
		mapa = nivel.generarMapaSiguiente();
		felix = new FelixJr(new Posicion(95, 10), mapa.getEdificio().getSeccionActual().getVentanaInicial());
		ralph = new Ralph(nivel.getCantLadrillos(), nivel.getFrecuenciaLadrillo(), nivel.getVelocidadLadrillo());
		tiempo = nivel.getTiempo();
		timer = new Contador(50);
	}

	private void checkTiempo() {
		if (timer.contar()) {
			System.out.println("Tiempo: " + tiempo);
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
		pausa = true;
		System.out.println("GAME OVER");
		System.out.println("Perdió con " + jugador.getPuntaje() + " puntos.");

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
