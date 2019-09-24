package paquete;

import java.awt.Color;
import java.awt.Point;
import java.util.*;

public abstract class Ventana {

	protected Point posicion;
	protected Seccion seccion;
	private Pastel pastel;
	private Nicelander nicelander;
	private List<Obstaculo> obstaculos = new ArrayList<>();
	protected List<Panel> paneles;
	private int cantMartillazos;
	private int panelesRotos;
	private int panelesReparados;

	public Ventana getVentana(Orientacion orientacion) {
		if (!tieneObstaculo(orientacion)) {
			return seccion.getVentanaAledana(this, orientacion);
		} else {
			return null;
		}
	}
	
	public Point getPosicion() {
		return posicion;
	}

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

	public boolean generarNiceLander() {
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
		Ventana v = seccion.getVentanaAledana(this, o);
		if (v != null) {
			return tieneObstaculo(o.invertir());
		}
		return false;
	}

	protected List<Panel> getPanelesRandom(int cantPaneles) {
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
			return getPanelesRandom(cantPaneles);
		}
		return lista;
	}

}
