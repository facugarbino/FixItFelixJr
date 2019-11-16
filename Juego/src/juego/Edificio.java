package juego;

import java.util.List;
import utils.Posicion;
/**
 * Representa al edificio con sus 3 secciones
 * 
 * @author Garbino y Rodriguez Murphy
 *
 */
public class Edificio {

	public static final int ANCHO = 210;
	public static final int ALTO = 650;
	
//	public static final int ANCHO = 100;
//	public static final int ALTO = 3*ANCHO;
	private Posicion posicion;
	private List<Seccion> secciones;
	private Seccion seccionActual;

	public Edificio(Posicion posicion, List<Seccion> secciones) {
		this.posicion = posicion;
		this.secciones = secciones;
		this.seccionActual = secciones.get(0);
	}

	/**
	 * 
	 * @param i el número de seccion (1,2 o 3)
	 * @return la sección que se solicita
	 */
	public Seccion getSeccion(int i) {
		return secciones.get(i - 1);
	}

	public Seccion getSeccionActual() {
		return seccionActual;
	}

	/**
	 * 
	 * @return la sección nueva
	 */
	public Seccion avanzarSeccion() {
		this.seccionActual = secciones.get(secciones.indexOf(seccionActual) + 1);
		seccionActual.resetearTimer();
		return seccionActual;
	}

	public Posicion getPosicion() {
		return posicion;
	}

	/**
	 * Se regenera una sección nueva aleatoramiente, 
	 * pero con las mismas características 
	 * (nro. de ventanas rotas y con obstaculo)
	 * 
	 * @param vieja seccion a regenerar
	 */
	public void reemplazarSeccion(Seccion vieja) {
		Seccion nueva;
		if (vieja instanceof PrimeraSeccion) {
			nueva = new PrimeraSeccion(vieja);
			secciones.set(0, nueva);
		} else {
			nueva = new Seccion(vieja);
			secciones.set(vieja.getNroSeccion()-1, nueva);
		}
		this.seccionActual = nueva;
	}
}
