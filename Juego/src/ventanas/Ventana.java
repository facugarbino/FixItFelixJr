package ventanas;

import java.awt.Color;
import java.util.*;

import controlador.JuegoMain;
import graficador.modelo.Dibujable;
import graficador.modelo.InformacionDibujable;
import juego.Juego;
import juego.Seccion;
import utils.Orientacion;
import utils.Posicion;
import ventanas.extra.Nicelander;
import ventanas.extra.Pastel;
import ventanas.obstaculos.Obstaculo;
import ventanas.paneles.EstadoPanel;
import ventanas.paneles.MedioRoto;
import ventanas.paneles.Panel;
import ventanas.paneles.Roto;
import ventanas.paneles.Sano;

/**
 * Clase abstracta que representa a una ventana de cualquier tipo.
 * 
 * @author Garbino y Rodriguez Murphy
 *
 */
public abstract class Ventana implements Dibujable {

	public static final int ANCHO = 24;
	public static final int ALTO = 39;

	protected Posicion posicion;
	protected Seccion seccion;
	private Pastel pastel;
	protected Nicelander nicelander;
	protected List<Obstaculo> obstaculos = new ArrayList<>();
	protected List<Panel> paneles;
	protected int cantMartillazos;
	protected int panelesRotos;
	protected int panelesReparados;
	Character caracter = '□';
//	private static Posicion[] posicion2 = new Posicion[] {
//			new Posicion(6,8), new Posicion(6,21)
//	};
//	private static Posicion[] posicion4 = new Posicion[] {
//			new Posicion(9,9),
//			new Posicion(22,9),
//			new Posicion(9,21),
//			new Posicion(22,21)
//	};
//	private static Posicion[] posicion5 = new Posicion[] {
//			new Posicion(7,4),
//			new Posicion(13,4),
//			new Posicion(22,4),
//			new Posicion(28,4),
//			new Posicion(7,13),
//			new Posicion(13,13),
//			new Posicion(22,13),
//			new Posicion(28,13)
//	};
	
	private static Posicion[] posicion2 = new Posicion[] {
			new Posicion(6,8), new Posicion(6,21)
	};
	private static Posicion[] posicion4 = new Posicion[] {
			new Posicion(9,9),
			new Posicion(22,9),
			new Posicion(9,21),
			new Posicion(22,21)
	};
	private static Posicion[] posicion5 = new Posicion[] {
			new Posicion(7,4),
			new Posicion(13,4),
			new Posicion(22,4),
			new Posicion(28,4),
			new Posicion(7,13),
			new Posicion(13,13),
			new Posicion(22,13),
			new Posicion(28,13)
	};
	

	/**
	 * Método llamado por Felix para moverse
	 * 
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

	public List<Obstaculo> getObstaculos() {
		return obstaculos;
	}

	public List<Panel> getPaneles() {
		return paneles;
	}

	public Seccion getSeccion() {
		return seccion;
	}

	public Posicion getPosicion() {
		return posicion;
	}

	public InformacionDibujable getInformacionDibujable() {
		return new InformacionDibujable(posicion.getX(), posicion.getY(), caracter,
				(estaRota()) ? Color.RED : Color.GREEN);

	}

	/**
	 * Método llamado por Felix cuando da un martillazo
	 * 
	 * @return true si el martillazo reparó algún panel o false en caso contrario,
	 *         ya sea porque no hay paneles rotos o porque falta dar otro martillazo
	 */
	public boolean reparar() {
		if (estaRota()) {
			cantMartillazos++;
			if (cantMartillazos == 2) {
				
				if (nicelander != null) {
					this.nicelander = null;
					seccion.setNicelander(null);
				}
				
				Iterator<Panel> i = paneles.iterator();
				while (i.hasNext() && !i.next().reparar())
					;
				panelesReparados++;
				cantMartillazos = 0;
				if (!estaRota()) {
					seccion.seArregloUnaVentana();
					// caracter = '□';
//					if (nicelander != null) {
//						this.nicelander = null;
//						seccion.setNicelander(null);
//					}
				}
				return true;
			}
		}
		return false;
	}

	public boolean hayPastel() {
		return pastel != null;
	}

	public void comerPastel() {
		seccion.borrarPastel(pastel);
		pastel = null;
	}

	public void generarNicelander() {
		/*
		 * Este método sólo lo sobreescribe VentanaComun, pues es la única que genera
		 * Nicelanders
		 * 
		 */
	}

	public void resetTimer() {
		/*
		 * Este método sólo lo sobreescribe VentanaComun, pues es la única que tiene
		 * timer para resetear
		 * 
		 */
	}

	public void ponerPastel(Pastel comida) {
		this.pastel = comida;
		seccion.agregarPastel(pastel);
		if (Juego.getInstance().getFelix().getVentana().equals(this)) {
			Juego.getInstance().getFelix().comerPastel();
		}
//		if (Juego.getInstance().getMapa().estaFelix(posicion, 1,1)) {
//			Juego.getInstance().getFelix().comerPastel();
//		}
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
	 * @param cantPaneles cantidad de paneles que tiene la ventana
	 * @return una lista de paneles, aleatoramiente rotos
	 */
	protected List<Panel> getPanelesRotosRandom(int cantPaneles) {
		Posicion posiciones[] = getPosiciones(cantPaneles);
		boolean algunoRoto = false;
		EstadoPanel p;
		List<Panel> lista = new ArrayList<>();
		int cuantosRotos = 0;

		for (int i = 0; i < cantPaneles; i++) {
			switch ((int) Math.floor(Math.random() * 3 + 1)) {
			case 1:
				p = new Roto();
				cuantosRotos++;
				algunoRoto = true;
				break;
			case 2:
				p = new MedioRoto();
				cuantosRotos++;
				algunoRoto = true;
				break;
			case 3:
				p = new Sano();
				break;
			default:
				p = new Roto();
			}
			lista.add(new Panel(p, posiciones[lista.size()]));
		}
		if (!algunoRoto) {
			return getPanelesRotosRandom(cantPaneles);
		}
		panelesRotos = cuantosRotos;
		return lista;
	}

	public static Posicion[] getPosiciones(int cantPaneles) {
		switch (cantPaneles) {
		case 2:{
			return posicion2;
			
		}
		case 4:{
			return posicion4;
			
		}
		case 8:{
			return posicion5;
		}
		}
		return null;
	}

	public boolean estaRota() {
		return panelesRotos != panelesReparados;
	}
}
