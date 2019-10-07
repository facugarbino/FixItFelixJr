package juego;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import graficador.modelo.Dibujable;
import utils.Orientacion;
import utils.Posicion;
import ventanas.Ventana;
import ventanas.VentanaComun;
import ventanas.VentanaConHojas;
import ventanas.extra.Nicelander;
import ventanas.extra.Pastel;

/**
 * Representa a una sección del edificio Niceland, que incluye 15 ventanas.
 * 
 * @author Garbino y Rodriguez Murphy
 *
 */
public class Seccion {
	public static final int ANCHO = Edificio.ANCHO;
	public static final int ALTO = Edificio.ANCHO;
	private static final int FILAS = 3;
	private static final int COLUMNAS = 5;
	protected int ventanasRotas;
	protected int ventanasConObstaculo;
	private int ventanasReparadas;
	private int nroSeccion;
	protected Ventana[][] ventanas;
	protected Nicelander nicelander;
	protected List<Pastel> pasteles;

	/**
	 * Constructor usado para reiniciar una sección (debe generarse aleatoriamente
	 * otra sección con la misma cantidad de ventanas rotas y obstáculos)
	 * 
	 * @param s sección a reiniciar
	 */
	public Seccion(Seccion s) {
		this(s.ventanasRotas, s.ventanasConObstaculo, s.nroSeccion);

	}

	/**
	 * Constructor que elige aleatoramiente una distribución de ventanas que cumple
	 * con la cantidad solicitad de ventanas rotas y con obstáculo
	 * 
	 * @param ventanasRotas        cantidad de ventanas que deben estar rotas
	 * @param ventanasConObstaculo cantidad de ventanas que deben tener obstaculo
	 * @param nroSeccion           número de la sección (1,2 o 3)
	 */
	public Seccion(int ventanasRotas, int ventanasConObstaculo, int nroSeccion) {
		// chequeamos que no se guarde más de 15 así no se bugea al ganar
		if (ventanasRotas > 15)
			ventanasRotas = 15;

		this.ventanasRotas = ventanasRotas;
		this.ventanasConObstaculo = ventanasConObstaculo;
		this.nroSeccion = nroSeccion;
		pasteles = new ArrayList<>();
		nicelander = null;
		ventanasReparadas = 0;
		boolean[][] conObstaculo = getMatrizRandom(ventanasConObstaculo);
		boolean[][] rotas = getMatrizRandom(ventanasRotas);
		ventanas = new Ventana[FILAS][COLUMNAS];
		for (int i = 0; i < FILAS; i++) {
			for (int j = 0; j < COLUMNAS; j++) {
				Posicion Posicion = new Posicion(50 + 15 * (j + 1), 10 + (nroSeccion - 1) * 100 + (2 - i) * 30);
				// Esta posicion luego la genereremos con valores CONSTANTES para poder
				// modificar más simplemente
				// el tamaño/posición del edificio, el tamaño de las ventanas, etc.
				ventanas[i][j] = ventanaRandom(Posicion, rotas[i][j], conObstaculo[i][j]);
			}
		}

	}

	public boolean hayNicelander() {
		return nicelander != null;
	}

	public int getNroSeccion() {
		return nroSeccion;
	}

	/**
	 * 
	 * @param posicion
	 * @param rota
	 * @param tieneObstaculo
	 * @return una ventana aleatoria (común o con hojas) según sus parámetros de
	 *         entrada
	 */
	private Ventana ventanaRandom(Posicion posicion, boolean rota, boolean tieneObstaculo) {
		double random = Math.random();
		/*
		 * System.out.println( "La ventana en " + posicion + (rota ? "está rota" :
		 * "está sana") + (tieneObstaculo ? " y tiene obstáculo" : ""));
		 */
		if (random < 0.2 && !rota) {
			// Con hojas
			return new VentanaConHojas(posicion, this, tieneObstaculo);
		} else {
			return new VentanaComun(posicion, this, rota, tieneObstaculo);
		}
	}

	/**
	 * Se llama una vez que se avanza de sección, para que las ventanas reseteen sus
	 * timers
	 */
	public void resetearTimer() {
		for (int i = 0; i < FILAS; i++) {
			for (int j = 0; j < COLUMNAS; j++) {
				ventanas[i][j].resetTimer();
			}
		}
	}

	/**
	 * Llamado por juego en cada iteración, que delega a cada ventana la decisión de
	 * que se coloque un Nicelander o no
	 * 
	 */
	public void generarNicelanders() {
		for (int i = 0; i < FILAS; i++) {
			for (int j = 0; j < COLUMNAS; j++) {
				ventanas[i][j].generarNicelander();
			}
		}
	}

	/**
	 * 
	 * @param v ventana
	 * @return <b>true</b> si la ventana pertenece a la sección, o <b>false</b> en
	 *         caso contrario
	 */
	public boolean esDeEstaSeccion(Ventana v) {
		for (int i = 0; i < FILAS; i++)
			for (int j = 0; j < COLUMNAS; j++)
				if (ventanas[i][j].equals(v))
					return true;
		return false;
	}

	/**
	 * 
	 * @param ventana
	 * @param orientacion
	 * @return la <b>ventana</b> que se encuentra en la direccion indicada a cierta
	 *         ventana, dentro de la sección
	 */
	public Ventana getVentanaAledana(Ventana ventana, Orientacion orientacion) {
		Posicion p = getXY(ventana);
		int x = p.getX();
		int y = p.getY();
		switch (orientacion) {
		case IZQUIERDA:
			if (x > 0)
				return ventanas[y][x - 1];
			break;
		case DERECHA:
			if (x < 4)
				return ventanas[y][x + 1];
			break;
		case ABAJO:
			if (y < 2)
				return ventanas[y + 1][x];
			break;
		case ARRIBA:
			if (y > 0)
				return ventanas[y - 1][x];
			break;
		}
		return null;
	}

	public Posicion getXY(Ventana v) {
		for (int i = 0; i < FILAS; i++) {
			for (int j = 0; j < COLUMNAS; j++) {
				if (ventanas[i][j].equals(v))
					return new Posicion(j, i);
			}
		}
		return null;
	}

	public void seArregloUnaVentana() {
		ventanasReparadas++;
	}

	public boolean estaSana() {
		return ventanasReparadas == ventanasRotas;
	}

	public Ventana getVentanaInicial() {
		return ventanas[2][2];
	}

	/**
	 * 
	 * @param n
	 * @return una matriz de booleanos de 3x5 con <b>n</b> elementos en <b>true</b>
	 */
	private boolean[][] getMatrizRandom(int n) {
		// la cantidad de trues tiene q ser n
		boolean[][] matriz = new boolean[FILAS][COLUMNAS];
		int hits = 0;
		if (n > 15)
			n = 15;
		while (hits < n) {
			int i = (int) (Math.random() * FILAS);
			int j = (int) (Math.random() * COLUMNAS);
			if (matriz[i][j] == false) {
				matriz[i][j] = true;
				hits++;
			}
		}
		return matriz;
	}

	public List<Dibujable> getComponentesDibujables() {
		List<Dibujable> lista = new ArrayList<>();
		for (int i = 0; i < FILAS; i++) {
			for (int j = 0; j < COLUMNAS; j++) {
				lista.add(ventanas[i][j]);
			}
		}
		if (nicelander!=null)
				lista.add(nicelander);
		lista.addAll(pasteles);
		return lista;
	}

	public void setNicelander(Nicelander nicelander) {
		this.nicelander = nicelander;
	}

	public void agregarPastel(Pastel pastel) {
		pasteles.add(pastel);
	}

	public void borrarPastel(Pastel pastel) {
		pasteles.remove(pastel);
	}
}
