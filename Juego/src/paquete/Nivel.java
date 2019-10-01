package paquete;

import java.util.ArrayList;
import java.util.List;

public class Nivel {
	private final double velocidadNube = 5;
	private int nivelMax;
	private int nroNivel;
	private int cantVentanasRotas;
	private double frecuenciaLadrillo;
	private double velocidadLadrillo;
	private double velocidadPajaro;
	private int ventanasConObstaculo;
	private int tiempo;
	private int porcentaje;
	private int cantLadrillos;

	
	public Nivel(int nivelMax, int cantVentanasRotas, double frecuenciaLadrillo, double velocidadLadrillo,
			double velocidadPajaro, int ventanasConObstaculo, int tiempo, int porcentaje, int cantLadrillos) {
		this.nivelMax = nivelMax;
		this.cantVentanasRotas = cantVentanasRotas;
		this.frecuenciaLadrillo = frecuenciaLadrillo;
		this.velocidadLadrillo = velocidadLadrillo;
		this.velocidadPajaro = velocidadPajaro;
		this.ventanasConObstaculo = ventanasConObstaculo;
		this.tiempo = tiempo;
		this.porcentaje = porcentaje;
		this.cantLadrillos = cantLadrillos;
	}

	/**
	 * 
	 * @return un mapa nuevo con su edificio y secciones
	 * correspondientes al nivel actual
	 */
	public Mapa crearMapa() {
		ArrayList<Seccion> secciones = new ArrayList<>();
		secciones
				.add((Seccion) new PrimeraSeccion((int) (cantVentanasRotas * 0.2), (int) (ventanasConObstaculo * 0.1)));
		secciones.add(new Seccion((int) (cantVentanasRotas * 0.3), (int) (ventanasConObstaculo * 0.3), 2));
		secciones.add(new Seccion((int) (cantVentanasRotas * 0.5), (int) (ventanasConObstaculo * 0.6), 3));
		Edificio e = new Edificio(new Posicion(50, 0), secciones);
		Mapa m = new Mapa(200, 400, e);
		m.agregarComponente(new Nube(new Posicion(0, 60), velocidadNube));
		m.agregarComponente(new Nube(new Posicion(50, 130), velocidadNube));
		m.agregarComponente(new Nube(new Posicion(100, 245), velocidadNube));
		return m;
	}

	/**
	 * Actualiza los valores correspondientes al 
	 * próximo nivel
	 */
	public void avanzarDeNivel() {
		nroNivel++;
		cantVentanasRotas *= ((100 + porcentaje) / 100.0);
		frecuenciaLadrillo -= frecuenciaLadrillo * (porcentaje / 100.0);
		velocidadLadrillo *= ((100 + porcentaje) / 100.0);
		ventanasConObstaculo *= ((100 + porcentaje) / 100.0);
		tiempo -= tiempo * 0.1;
	}

	public int getCantVentanasRotas() {
		return cantVentanasRotas;
	}

	public double getFrecuenciaLadrillo() {
		return frecuenciaLadrillo;
	}

	public double getVelocidadLadrillo() {
		return velocidadLadrillo;
	}

	public double getVelocidadPajaro() {
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
		return nroNivel != nivelMax;
	}

	public int getCantLadrillos() {
		return cantLadrillos;
	}

}
