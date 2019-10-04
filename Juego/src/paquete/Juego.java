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
	private Seccion seccionActual;
	private boolean primeraVez;

	/**
	 * crea la única instancia de Juego
	 * 
	 */
	public static void crearJuego(String nombreJugador) {
		juego = new Juego(nombreJugador);
		System.out.println("Comienza el juego. Juega: " + nombreJugador);
	}

	private Juego(String nombre) {
		// nivel = new Nivel(10, 15, 1000, 10, 10, 5, 600, 10, 40);
		nivel = new Nivel(10, 30, 10000, 50, 10, 6, 600, 10, 40);
		// nivelMax, cantVentanasRotas, frecuenciaLadrillo, velocidadLadrillo,
		// velocidadPajaro, ventanasConObstaculo, tiempo, porcentaje, cantLadrillos
		jugador = new Jugador(nombre);
		ranking = new Ranking();
		ranking.agregarHighScore(new HighScore(new Jugador("Fabian")));
		primeraVez = true;
		pasarDeNivel();
		pausa = false;

	}

	public boolean estaPausado() {
		return pausa;
	}

	public void pausar() {
		if (pausa) {
			System.out.println("JUEGO DESPAUSADO");
		} else {
			System.out.println("JUEGO PAUSADO");
		}
		pausa = !pausa;

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
	public Ralph getRalph(){
		return ralph;
	}

	public void darMartillazo() {
		felix.darMartillazo();
	}

	/**
	 * Se encarga de actualizar el estado del juego,
	 * se ejecuta constantemente
	 */
	public void hacerTodo() {
		ralph.mover();
		Ladrillo l = ralph.tirarLadrillo();
		if (l != null) {
			mapa.agregarComponente(l);
		}
		seccionActual.generarNicelanders();
		if (seccionActual.estaSana()) {
			avanzarSeccion();
		}
		mapa.avanzarComponentes();
		checkTiempo();
	}

	
	public void pasarDeNivel() {
		if (primeraVez) {
			reiniciarNivel(3);
		} else {
			jugador.sumarPuntos(felix.getPuntaje());
			if (nivel.hayOtroNivel()) {
				nivel.avanzarDeNivel();
				reiniciarNivel(3);
			} else {
				ganar();
			}
		}

	}

	private void ganar() {
		HighScore hs = new HighScore(jugador);
		ranking.agregarHighScore(hs);
		pausa = true;
		System.out.println("¡FELICITACIONES! Ganaste el juego.");

	}

	/**
	 * Hace la lógica para llevar la cuenta
	 * regresiva del tiempo
	 */
	private void checkTiempo() {
		if (timer.contar()) {
			// System.out.println("Tiempo: " + tiempo);
			timer.resetear();
			tiempo--;
			if (tiempo < 1) {
				perder(felix.getPuntaje());
			}
		}
	}

	
	public void avanzarSeccion() {
		if (seccionActual.getNroSeccion() < 3) {
			System.out.println("Felix Jr. avanza de seccion");
			seccionActual = mapa.getEdificio().avanzarSeccion();
			tiempo = nivel.getTiempo();
			felix.setPosicion(seccionActual.getVentanaInicial().getPosicion().copia());
			felix.setVentana(seccionActual.getVentanaInicial());
			ralph.subirDeSeccion();
		} else {
			pasarDeNivel();
			System.out.println("Felix Jr. pasa de nivel");
		}
	}

	/**
	 * "Reinicia" el nivel
	 * @param vidasDeFelix las vidas con que Felix Jr. aparecerá
	 */
	public void reiniciarNivel(int vidasDeFelix) {
		mapa = nivel.crearMapa();
		seccionActual = mapa.getEdificio().getSeccionActual();
		felix = new FelixJr(new Posicion(95, 10), seccionActual.getVentanaInicial(), vidasDeFelix);
		ralph = new Ralph(new Posicion(95, 110), nivel.getCantLadrillos(), nivel.getFrecuenciaLadrillo(),
				nivel.getVelocidadLadrillo());
		tiempo = nivel.getTiempo();
		timer = new Contador(50);
	}

	/**
	 * GAME OVER y agrega el puntaje al Ranking
	 * @param puntajeFelix - el puntaje que tenía Felix acumulado
	 */
	public void perder(long puntajeFelix) {
		jugador.sumarPuntos(puntajeFelix);
		HighScore hs = new HighScore(jugador);
		ranking.agregarHighScore(hs);
		pausa = true;
		System.out.println("GAME OVER");
		System.out.println("Perdió con " + jugador.getPuntaje() + " puntos.");

	}

	/**
	 * Se ejecuta cuando un pájaro golpea a Felix
	 * y debe reiniciarse la sección donde se encuentra
	 */
	public void reiniciarSeccion() {
		mapa.getEdificio().reemplazarSeccion(seccionActual);
	}

	public Mapa getMapa() {
		return mapa;
	}

}
