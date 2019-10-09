package juego;

import componentes.Ladrillo;
import componentes.Nube;
import componentes.Pajaro;
import personajes.FelixJr;
import personajes.Ralph;
import ranking.HighScore;
import ranking.Ranking;
import utils.Contador;
import utils.Orientacion;
import utils.Posicion;
import ventanas.Ventana;

/**
 * Se encarga del funcionamiento general del juego. Hace falta llamar a su
 * método actualizar() indefinidamente para que funcione. Es un singleton. La
 * única instancia se obtiene con el método getInstance()
 * 
 * @author Garbino y Rodriguez Murphy
 *
 */
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
	private Seccion seccionActual;
	private boolean pausa;
	private boolean primeraVez;
	private boolean yaGano;

	/**
	 * 
	 * @return la única de instancia del juuego y, si no existe, la genera
	 */
	public static Juego getInstance() {
		if (juego == null) {
			juego = new Juego();
			System.out.println("Comienza el juego.");
		}
		return juego;
	}

	public void setJugador(String nombre) {
		jugador = new Jugador(nombre);
		System.out.println("Juega: " + nombre);
	}

	private Juego() {
		// nivel = new Nivel(10, 15, 1000, 10, 10, 5, 600, 10, 40);
		nivel = new Nivel(10, 25, 10000, 50, 130, 6, 300, 10, 40);
		// nivelMax, cantVentanasRotas, frecuenciaLadrillo, velocidadLadrillo,
		// velocidadPajaro, ventanasConObstaculo, tiempo, porcentaje, cantLadrillos
		jugador = new Jugador("Anonimo");
		ranking = new Ranking();
		primeraVez = true;
		pasarDeNivel();
		pausa = false;

	}

	public Ranking getRanking() {
		return ranking;
	}

	public int getTiempo() {
		return tiempo;
	}

	public int getNroNivel() {
		return nivel.getNroNivel();
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

	public Ralph getRalph() {
		return ralph;
	}

	public void darMartillazo() {
		felix.darMartillazo();
	}

	/**
	 * Se encarga de actualizar el estado del juego, se ejecuta constantemente
	 */
	public void actualizar() {
		ralph.mover();
		Ladrillo l = ralph.tirarLadrillo();
		if (l != null) {
			mapa.agregarComponente(l);
		}
		seccionActual.generarNicelanders();
		if (seccionActual.estaSana()) {
			avanzarSeccion();
		}
		/*
		 * Agregamos este TRY porque cuando Felix choca con un pajaro, nos
		 * tira un error de concurrencia...
		 * 
		 * try { mapa.avanzarComponentes(); } catch(Exception e) { e.printStackTrace();}
		 * 
		 * Al final, lo solucionamos con el CopyOnWriteArrayList
		 */
		mapa.avanzarComponentes();
		checkTiempo();
		felix.chequearInmunizacion();

	}

	public void pasarDeNivel() {
		if (primeraVez) {
			reiniciarNivel(3);
			primeraVez = false;
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

	public boolean yaGano() {
		return yaGano;
	}

	private void ganar() {
		agregarRanking();
		pausa = true;
		yaGano = true;
		System.out.println("¡FELICITACIONES! Ganaste el juego.");
	}

	private void agregarRanking() {
		HighScore hs = new HighScore(jugador);
		ranking.agregarHighScore(hs);
	}

	/**
	 * Hace la lógica para llevar la cuenta regresiva del tiempo
	 */
	private void checkTiempo() {
		if (timer.contar()) {
			// System.out.println("Tiempo: " + tiempo);
			timer.resetear();
			tiempo--;
			if (tiempo < 0) {
				if (felix.getVidas() > 1) {
					reiniciarNivel(felix.getVidas() - 1);
				} else {
					perder(felix.getPuntaje());
				}
			}
		}
	}

	private void agregarPajaros(int nroSeccion) {
		if (nroSeccion == 2) {
			if (Math.random() < 0.5) {
				mapa.agregarComponente(new Pajaro(new Posicion(0, (int) (Seccion.ALTO * (4.0 / 3)) + 10),
						nivel.getVelocidadPajaro(), Orientacion.DERECHA, mapa));
			} else {
				mapa.agregarComponente(new Pajaro(new Posicion(0, (int) (Seccion.ALTO * (5.0 / 3)) + 10),
						nivel.getVelocidadPajaro(), Orientacion.DERECHA, mapa));
			}
		} else {
			mapa.agregarComponente(new Pajaro(new Posicion(0, (int) (Seccion.ALTO * (7.0 / 3)) + 10),
					nivel.getVelocidadPajaro(), Orientacion.DERECHA, mapa));
			mapa.agregarComponente(new Pajaro(new Posicion(Seccion.ANCHO, (int) (Seccion.ALTO * (8.0 / 3)) + 10),
					nivel.getVelocidadPajaro(), Orientacion.DERECHA, mapa));
		}
	}

	public void avanzarSeccion() {
		int nroSeccion = seccionActual.getNroSeccion();
		if (nroSeccion < 3) {
			System.out.println("Felix Jr. avanza de seccion");
			// Crear pajaros acá
			agregarPajaros(nroSeccion + 1);
			mapa.borrarComponentesDeSeccion(seccionActual.getNroSeccion());
			seccionActual = mapa.getEdificio().avanzarSeccion();
			tiempo = nivel.getTiempo();
			felix.subirDeSeccion(seccionActual);
			ralph.subirDeSeccion();
		} else {
			pasarDeNivel();
			System.out.println("Felix Jr. pasa de nivel");
		}
	}

	/**
	 * "Reinicia" el nivel
	 * 
	 * @param vidasDeFelix las vidas con que Felix Jr. aparecerá
	 */
	public void reiniciarNivel(int vidasDeFelix) {
		mapa = nivel.crearMapa();
		seccionActual = mapa.getEdificio().getSeccionActual();
		felix = new FelixJr(seccionActual.getVentanaInicial().getPosicion().copia(), seccionActual.getVentanaInicial(),
				vidasDeFelix);
		ralph = new Ralph(seccionActual.getVentanaInicial().getPosicion().copia(), nivel.getCantLadrillos(),
				nivel.getFrecuenciaLadrillo(), nivel.getVelocidadLadrillo());

		ralph.getPosicion().moverY(Seccion.ALTO);
		tiempo = nivel.getTiempo();
		timer = new Contador(500);
	}

	/**
	 * GAME OVER y agrega el puntaje al Ranking
	 * 
	 * @param puntajeFelix - el puntaje que tenía Felix acumulado
	 */
	public void perder(long puntajeFelix) {
		jugador.sumarPuntos(puntajeFelix);
		agregarRanking();
		pausa = true;
		System.out.println("GAME OVER");
		System.out.println("Perdió con " + jugador.getPuntaje() + " puntos.");

	}

	/**
	 * Se ejecuta cuando un pájaro golpea a Felix y debe reiniciarse la sección
	 * donde se encuentra
	 */
	public void reiniciarSeccion() {
		mapa.borrarComponentesDeSeccion(seccionActual.getNroSeccion());
		mapa.getEdificio().reemplazarSeccion(seccionActual);
		seccionActual = mapa.getEdificio().getSeccionActual();
		agregarPajaros(seccionActual.getNroSeccion());
		switch (seccionActual.getNroSeccion()){
			case 1: mapa.agregarComponente(new Nube(new Posicion(0, 60), nivel.getVelocidadNube())); break;
			case 2: mapa.agregarComponente(new Nube(new Posicion(0, 130), nivel.getVelocidadNube())); break;
			case 3: mapa.agregarComponente(new Nube(new Posicion(0, 245), nivel.getVelocidadNube())); break;
		}
		Ventana v = seccionActual.getVentanaInicial();
		felix.setVentana(v);
		felix.setPosicion(v.getPosicion().copia());
	}

	public Mapa getMapa() {
		return mapa;
	}

	public Jugador getJugador() {
		return jugador;
	}
}
