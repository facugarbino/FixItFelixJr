package paquete;

import java.util.ArrayList;
import java.util.List;

public class Nivel {
	private final double velocidadNube = 100;
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
		this.nroNivel = 1;
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
		Mapa m = new Mapa(e);
		m.agregarComponente(new Nube(new Posicion(0, 60), velocidadNube));
		m.agregarComponente(new Nube(new Posicion(50, 130), velocidadNube));
		m.agregarComponente(new Nube(new Posicion(100, 245), velocidadNube));
		//Agrega un pajaro aleatoramiente en la seccion 2 (segundo o tercer piso)
		//y dos pajaros en la ultima seccion
		if (Math.random()<0.5) { 
			m.agregarComponente(new Pajaro(new Posicion(0,Seccion.ALTO*(4/3)), velocidadPajaro, Orientacion.DERECHA, m));
		} else {
			m.agregarComponente(new Pajaro(new Posicion(0,Seccion.ALTO*(5/3)), velocidadPajaro, Orientacion.DERECHA, m));
		}
		m.agregarComponente(new Pajaro(new Posicion(0,Seccion.ALTO*(7/3)), velocidadPajaro, Orientacion.DERECHA, m));
		m.agregarComponente(new Pajaro(new Posicion(Seccion.ANCHO,Seccion.ALTO*(8/3)), velocidadPajaro, Orientacion.DERECHA, m));
		
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
