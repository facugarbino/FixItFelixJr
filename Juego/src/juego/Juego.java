package juego;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import animaciones.AnimacionFinalDeNivel;
import animaciones.AnimacionGolpeFelix;
import animaciones.AnimacionSubidaRalph;
import animaciones.AnimacionSubidaRalphRompiendo;
import componentes.*;
import controlador.Audio;
import controlador.JuegoMain;
import excepciones.*;
import personajes.*;
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
	private static Juego INSTANCE;
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
	private int nivelAComenzar;
	private int cantLadrillosDeEstaSeccion;
	private boolean graficarPausa;
	private Set<Character> conjuntoValidos;
	private boolean graficarGameOver;

	/**
	 * 
	 * @return la única de instancia del juego y, si no existe, la genera
	 */
	public static Juego getInstance() {
		if (INSTANCE == null) {
			reiniciarJuego();
		}
		return INSTANCE;
	}

	public static void reiniciarJuego() {
		INSTANCE = new Juego();
		System.out.println("Comienza el juego.");
	}

	public void setJugador(String nombre) {
		jugador = new Jugador(nombre, jugador.getPuntaje());
		System.out.println("Juega: " + nombre);
	}

	private Juego() {
		// nivel = new Nivel(10, 15, 1000, 20, 50, 5, 600, 10, 40);
		nivel = new Nivel(10, 25, 9000, 15, 40, 6, 300, 10, 40);
		// nivelMax, cantVentanasRotas, frecuenciaLadrillo, velocidadLadrillo,
		// velocidadPajaro, ventanasConObstaculo, tiempo, porcentaje, cantLadrillos
		jugador = new Jugador("anonimo");
		ranking = JuegoMain.getRanking();
		primeraVez = true;
		nivelAComenzar = 1;
		// pasarDeNivel(); deben llamarlo de afuera
		pausa = false;
		conjuntoValidos = new HashSet<Character>();
		conjuntoValidos.addAll(Arrays.asList(new Character[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
				'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '.', '-', ',', '!', '?',
				'"', '(', ')', '\'', '[', ']', '@', '*', '&', '%', '$', '#', '>', '<', '=', '1', '2', '3',
				'4', '5', '6', '7', '8', '9', '0' }));

	}

	public void setNivelAComenzar(int nivel) {
		this.nivelAComenzar = nivel;
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
		ralph.pausar();
		seccionActual.pausar();
		felix.pausar();
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
		 * Agregamos este TRY porque cuando Felix choca con un pajaro, nos tira un error
		 * de concurrencia...
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
			for (int i = 1; i < nivelAComenzar; i++) {
				nivel.avanzarDeNivel();
			}
			reiniciarNivel(3);
			primeraVez = false;
		} else {
			Audio.getInstance().levelUp();
			jugador.sumarPuntos(felix.getPuntaje());
			if (nivel.hayOtroNivel()) {
				nivel.avanzarDeNivel();
				reiniciarNivel(3);
				(new Timer()).schedule(new TimerTask() {
					public void run() {
						JuegoMain.getPantallaJuego().scrollHacia(seccionActual.getPosicion());						
					}
				}, 2500);
				(new AnimacionFinalDeNivel()).run();
				(new AnimacionSubidaRalphRompiendo()).run();
			} else {
				ganar();
			}
		}
	}
	public void pasarDeNivelConHack() {
		jugador.sumarPuntos(felix.getPuntaje());
		if (nivel.hayOtroNivel()) {
			nivel.avanzarDeNivel();
			reiniciarNivel(3);
			JuegoMain.getPantallaJuego().scrollHacia(seccionActual.getPosicion());
		} else {
			ganar();
		}
	}

	public boolean yaGano() {
		return yaGano;
	}

	private void ganar() {
		// Esto se hace para que no se grafique un puntaje erróneo al ganar
		Audio.getInstance().fondo(false);
		felix.sacarPuntaje();
		pausa = true;
		yaGano = true;
		System.out.println("¡FELICITACIONES! Ganaste el juego.");
	}

	public void agregarRanking() {
		if (ranking.entraEnElTop(jugador.getPuntaje())) {
			String nombreJugador;
			boolean nombreInvalido = false;
			do {
				try {
					nombreJugador = preguntarNombre();
					setJugador(nombreJugador);
					ranking.agregarHighScore(new HighScore(jugador));
					nombreInvalido = false;
				} catch (ExcepcionNombreInvalido e) {
					nombreInvalido = true;
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			} while (nombreInvalido);
		}
	}

	private String preguntarNombre() throws ExcepcionNombreInvalido {
		String nombre = JOptionPane.showInputDialog(null, "Inserte su Nombre: ", "Fix it Felix Jr.",
				JOptionPane.QUESTION_MESSAGE);
		// Esto es por si le dan a CANCEL
		if (nombre == null) {
			throw new ExcepcionNombreCorto();
		}
		nombre = nombre.toLowerCase();
		for (int i = 0; i < nombre.length(); i++) {
			if (!conjuntoValidos.contains(nombre.charAt(i))) {
				throw new ExcepcionNombreCaracterInvalido();
			}
		}
		if (ranking.existe(nombre)) {
			throw new ExcepcionNombreUsado();
		} else {
			if (nombre.length() < 2) {
				throw new ExcepcionNombreCorto();
			} else if (nombre.length() > 20) {
				nombre = nombre.substring(0, 20);
			}
		}
		return nombre;
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

	private void agregarNubes(int nroSeccion) {
		if (nroSeccion == 1) {
			mapa.agregarComponente(new Nube(new Posicion(0, 200), nivel.getVelocidadNube()));
		} else {
			if (nroSeccion == 2) {
				mapa.agregarComponente(new Nube(new Posicion(Edificio.ANCHO / 2, 400), nivel.getVelocidadNube()));
				agregarNubes(1);
			} else {
				mapa.agregarComponente(new Nube(new Posicion(Edificio.ANCHO, 600), nivel.getVelocidadNube()));
				agregarNubes(2);
			}
		}
	}

	public void avanzarSeccion() {
		cantLadrillosDeEstaSeccion = ralph.getCantLadrillos();
		int nroSeccion = seccionActual.getNroSeccion();
		if (nroSeccion < 3) {
			Audio.getInstance().seccionUp();
			System.out.println("Felix Jr. avanza de seccion");
			// Crear pajaros acá
			agregarPajaros(nroSeccion + 1);
			mapa.borrarComponentesDeSeccion(seccionActual.getNroSeccion());
			agregarNubes(nroSeccion);
			seccionActual = mapa.getEdificio().avanzarSeccion();
			tiempo = nivel.getTiempo();
			felix.subirDeSeccion(seccionActual);
			ralph.subirDeSeccion();
			pausar();
			Thread t = JuegoMain.getPantallaJuego().scrollHacia(seccionActual.getPosicion());
			new AnimacionSubidaRalph().run();
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			pausar();
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
		if (vidasDeFelix < 3) {
			Thread t2 = new Thread(new AnimacionGolpeFelix());
			t2.start();
			Thread t = JuegoMain.getPantallaJuego().scrollHacia(seccionActual.getPosicion());
			try {
				t.join();
				t2.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
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
		Audio.getInstance().perdio();
		Audio.getInstance().fondo(false);
		jugador.sumarPuntos(puntajeFelix);
		pausa = true;
		System.out.println("GAME OVER");
		System.out.println("Perdió con " + jugador.getPuntaje() + " puntos.");

	}

	/**
	 * Se ejecuta cuando un pájaro golpea a Felix y debe reiniciarse la sección
	 * donde se encuentra
	 */
	public void reiniciarSeccion() {
		Thread t = new Thread(new AnimacionGolpeFelix());
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		mapa.borrarComponentesDeSeccion(seccionActual.getNroSeccion());
		mapa.getEdificio().reemplazarSeccion(seccionActual);
		seccionActual = mapa.getEdificio().getSeccionActual();
		agregarPajaros(seccionActual.getNroSeccion());
		agregarNubes(seccionActual.getNroSeccion());
		Ventana v = seccionActual.getVentanaInicial();
		felix.setVentana(v);
		felix.setPosicion(v.getPosicion().copia());
		ralph.setCantLadrillos(cantLadrillosDeEstaSeccion);
	}

	public Mapa getMapa() {
		return mapa;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void graficarPausar() {
		graficarPausa = !graficarPausa;
	}

	public void graficarGameOver() {
		graficarGameOver = !graficarGameOver;
	}
	
	public boolean deboGraficarPausa() {
		return graficarPausa;
	}
}
