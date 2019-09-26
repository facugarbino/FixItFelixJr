package paquete;

import java.awt.Point;
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
	private boolean primeraVez = true;

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

	public Mapa regenerarMapa() {
		List<Seccion> secciones = new ArrayList<Seccion>();
		secciones.add(new PrimeraSeccion((int) (cantVentanasRotas * 0.2), (int) (ventanasConObstaculo * 0.1)));
		secciones.add(new Seccion((int) (cantVentanasRotas * 0.2), (int) (ventanasConObstaculo * 0.1)));
		secciones.add(new Seccion((int) (cantVentanasRotas * 0.2), (int) (ventanasConObstaculo * 0.1)));
		Edificio e = new Edificio(new Point(50, 0), secciones);
		Mapa m = new Mapa(200, 400, e);
		m.agregarComponente(new Nube(new Point(0, 60), velocidadNube));
		m.agregarComponente(new Nube(new Point(50, 130), velocidadNube));
		m.agregarComponente(new Nube(new Point(100, 245), velocidadNube));
		return m;
	}

	public Mapa generarMapaSiguiente() {
		if (!primeraVez)
			actualizarValores();
		else
			primeraVez = false;
		return regenerarMapa();
	}

	private void actualizarValores() {
		nroNivel++;
		cantVentanasRotas *= ((100 + porcentaje) / 100.0);
		frecuenciaLadrillo *= ((100 + porcentaje) / 100.0);
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
