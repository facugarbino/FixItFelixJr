package paquete;

import java.util.*;

import taller2.modelo.Dibujable;
import taller2.modelo.InformacionDibujable;

public abstract class Ventana implements Dibujable{

	public static final int ANCHO = 10;
	public static final int ALTO = 20;
	
	protected Posicion posicion;
	protected Seccion seccion;
	private Pastel pastel;
	protected Nicelander nicelander;
	protected List<Obstaculo> obstaculos = new ArrayList<>();
	protected List<Panel> paneles;
	protected int cantMartillazos;
	protected int panelesRotos;
	protected int panelesReparados;
	Character caracter;
	

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
	

	public InformacionDibujable getInformacionDibujable(){
		return new InformacionDibujable(posicion.getX(),300-posicion.getY(), caracter);
		
	}
	/**
	 * @see Método llamado por Felix
	 * @return true si el martillazo reparó algún panel o false en caso contrario,
	 *         ya sea porque no hay paneles rotos o porque falta dar otro martillazo
	 */
	public boolean reparar() {
		if (estaRota()) {
			cantMartillazos++;
			if (cantMartillazos == 2) {
				Iterator<Panel> i = paneles.iterator();
				while (i.hasNext() && !i.next().reparar())
					;
				panelesReparados++;
				cantMartillazos=0;
				if (!estaRota()) {
					seccion.seArregloUnaVentana();
					caracter = '□';
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
		pastel = null;
	}

	public void generarNicelander() {
		
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
	 * @param cantPaneles cantidad de paneles que tiene la ventana
	 * @return una lista de paneles, aleatoramiente rotos
	 */
	protected List<Panel> getPanelesRotosRandom(int cantPaneles) {
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
			lista.add(new Panel(p));
		}
		if (!algunoRoto) {
			return getPanelesRotosRandom(cantPaneles);
		}
		panelesRotos = cuantosRotos;
		return lista;
	}

	public boolean estaRota() {
		return panelesRotos != panelesReparados;
	}
}
