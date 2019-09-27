package paquete;

import java.awt.Color;
import java.util.*;

public abstract class Ventana {

	protected Posicion posicion;
	protected Seccion seccion;
	private Pastel pastel;
	protected Nicelander nicelander;
	protected List<Obstaculo> obstaculos = new ArrayList<>();
	protected List<Panel> paneles;
	private int cantMartillazos;
	private int panelesRotos;
	private int panelesReparados;

	/**
	 * @see Método llamado por Felix para moverse
	 * @param orientacion
	 * @return la ventana que se encuentra en dicha dirección, si es que no hay
	 *         obstáculos entre ellas, o null en caso contrario.
	 */
	public Ventana getVentana(Orientacion orientacion) {
		if (!tieneObstaculo(orientacion)) {
			Ventana v = seccion.getVentanaAledana(this, orientacion);
			if (v != null && !v.tieneObstaculo(orientacion.invertir()))
				return v;
		}
		return null;
	}

	public Seccion getSeccion() {
		return seccion;
	}

	public Posicion getPosicion() {
		return posicion;
	}

	/**
	 * @see Método llamado por Felix
	 * @return true si el martillazo reparó algún panel o false en caso contrario,
	 *         ya sea porque no hay paneles rotos o porque falta dar otro martillazo
	 */
	public boolean reparar() {
		if (!(panelesReparados == panelesRotos)) {
			cantMartillazos++;
			if (cantMartillazos == 2) {
				Iterator<Panel> i = paneles.iterator();
				while (i.hasNext() && !i.next().reparar())
					;
				panelesReparados++;
				if (panelesReparados == panelesRotos) {
					seccion.seArregloUnaVentana();
				}
			}
			return true;
		}
		return false;
	}

	public boolean hayPastel() {
		return pastel != null;
	}

	public void comerPastel() {
		pastel = null;
	}

	public boolean generarNicelander() {
		return false;
	}

	public void ponerPastel(Pastel comida) {
		this.pastel = comida;
	}

	private boolean tieneObstaculo(Orientacion o) {
		if (!obstaculos.isEmpty()) {
			Iterator<Obstaculo> i = obstaculos.iterator();
			while (i.hasNext()) {
				if (i.next().estasEn(o)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @param cantPaneles - cantidad de paneles que tiene la ventana
	 * @return una lista de paneles, aleatoramiente rotos
	 */
	protected List<Panel> getPanelesRotosRandom(int cantPaneles) {
		boolean algunoRoto = false;
		EstadoPanel p;
		List<Panel> lista = new ArrayList<>();

		for (int i = 0; i < cantPaneles; i++) {
			switch ((int) Math.floor(Math.random() * 3 + 1)) {
			case 1:
				p = new Roto();
				algunoRoto = true;
				break;
			case 2:
				p = new MedioRoto();
				algunoRoto = true;
				break;
			case 3:
				p = new Sano();
				break;
			default:
				p = new Roto();
			}
			lista.add(new Panel(p));
		}
		if (!algunoRoto) {
			return getPanelesRotosRandom(cantPaneles);
		}
		return lista;
	}

	public boolean estaRota() {
		return panelesRotos != panelesReparados;
	}
}
