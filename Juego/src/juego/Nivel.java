package juego;

import java.util.ArrayList;
import componentes.Nube;
import utils.Posicion;

/**
 * Se encarga de actualizar la dificultad y generar un mapa nuevo para cada
 * nivel.
 * 
 * @author Garbino y Rodriguez Murphy
 *
 */
public class Nivel {
	private final int velocidadNube = 250;
	private int nivelMax;
	private int nroNivel;
	private int cantVentanasRotas;
	private int frecuenciaLadrillo;
	private int velocidadLadrillo;
	private int velocidadPajaro;
	private int ventanasConObstaculo;
	private int tiempo;
	private int porcentaje;
	private int cantLadrillos;

	public Nivel(int nivelMax, int cantVentanasRotas, int frecuenciaLadrillo, int velocidadLadrillo,
			int velocidadPajaro, int ventanasConObstaculo, int tiempo, int porcentaje, int cantLadrillos) {
		this.nivelMax = nivelMax;
		this.cantVentanasRotas = cantVentanasRotas;
		this.frecuenciaLadrillo = frecuenciaLadrillo;
		this.velocidadLadrillo = velocidadLadrillo;
		this.velocidadPajaro = velocidadPajaro;
		this.ventanasConObstaculo = ventanasConObstaculo;
		this.tiempo = tiempo;
		this.porcentaje = porcentaje;
		this.cantLadrillos = cantLadrillos;
		this.nroNivel = 1;
	}

	/**
	 * 
	 * @return un mapa nuevo con su edificio y secciones correspondientes al nivel
	 *         actual
	 */
	public Mapa crearMapa() {
		ArrayList<Seccion> secciones = new ArrayList<>();
		int sec3 = (int) (cantVentanasRotas * 0.5);
		int sec2 = (int) (cantVentanasRotas * 0.3);
		int sec1 = (int) (cantVentanasRotas * 0.2);
		if (sec3 > 15)
			sec2 += (sec3 - 15);
		if (sec2 > 15)
			sec1 += (sec2 - 15);

		secciones.add((Seccion) new PrimeraSeccion(sec1, (int) (ventanasConObstaculo * 0.1)));
		secciones.add(new Seccion(sec2, (int) (ventanasConObstaculo * 0.3), 2));
		secciones.add(new Seccion(sec3, (int) (ventanasConObstaculo * 0.6), 3));
		Edificio e = new Edificio(new Posicion(Edificio.ANCHO/2, 0), secciones);
		Mapa m = new Mapa(e);
		m.agregarComponente(new Nube(new Posicion(0, 200), velocidadNube));
		m.agregarComponente(new Nube(new Posicion(Edificio.ANCHO, 400), velocidadNube));
		m.agregarComponente(new Nube(new Posicion(Edificio.ANCHO*2, 600), velocidadNube));
		// Agrega un pajaro aleatoramiente en la seccion 2 (segundo o tercer piso)
		// y dos pajaros en la ultima seccion
//		if (Math.random() < 0.5) {
//			m.agregarComponente(new Pajaro(new Posicion(0, (int) (Seccion.ALTO * (4.0 / 3)) + 10), velocidadPajaro,
//					Orientacion.DERECHA, m));
//		} else {
//			m.agregarComponente(new Pajaro(new Posicion(0, (int) (Seccion.ALTO * (5.0 / 3)) + 10), velocidadPajaro,
//					Orientacion.DERECHA, m));
//		}
//		m.agregarComponente(new Pajaro(new Posicion(0, (int) (Seccion.ALTO * (7.0 / 3)) + 10), velocidadPajaro,
//				Orientacion.DERECHA, m));
//		m.agregarComponente(new Pajaro(new Posicion(Seccion.ANCHO, (int) (Seccion.ALTO * (8.0 / 3)) + 10),
//				velocidadPajaro, Orientacion.DERECHA, m));
		return m;
	}

	/**
	 * Actualiza los valores correspondientes al próximo nivel
	 */
	public void avanzarDeNivel() {
		nroNivel++;
		cantVentanasRotas *= ((100 + porcentaje) / 100.0);
		frecuenciaLadrillo -= frecuenciaLadrillo * (porcentaje / 100.0);
		velocidadLadrillo -= velocidadLadrillo * (porcentaje / 100.0);
		velocidadPajaro -= velocidadPajaro * (porcentaje / 100.0);
		ventanasConObstaculo *= ((100 + porcentaje) / 100.0);
		cantLadrillos += 6;
		tiempo -= tiempo * 0.1;
	}

	public int getCantVentanasRotas() {
		return cantVentanasRotas;
	}

	public int getFrecuenciaLadrillo() {
		return frecuenciaLadrillo;
	}

	public int getVelocidadLadrillo() {
		return velocidadLadrillo;
	}

	public int getVelocidadPajaro() {
		return velocidadPajaro;
	}

	public int getVentanasConObstaculo() {
		return ventanasConObstaculo;
	}

	public int getTiempo() {
		return tiempo;
	}

	public int getNroNivel() {
		return nroNivel;
	}

	public boolean hayOtroNivel() {
		return nroNivel < nivelMax;
	}

	public int getCantLadrillos() {
		return cantLadrillos;
	}

	public int getVelocidadNube() {
		return velocidadNube;
	}

}
