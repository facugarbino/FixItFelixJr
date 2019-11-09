package juego;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import componentes.Componente;
import componentes.Nube;
import graficador.modelo.Dibujable;
import utils.Posicion;

/**
 * Representa al mapa del nivel actual del juego
 * 
 * @author Garbino y Rodriguez Murphy
 *
 */
public class Mapa {

	public static final int ANCHO = 480;
	public static final int ALTO = 650;
	private List<Componente> componentes;
	private Edificio edificio;
	private List<Componente> componentesABorrar;

	public Mapa(Edificio edificio) {
		this.edificio = edificio;
		/*
		 * Usamos la clase CopyOnWriteArrayList para solucionar un problema de
		 * concurrencia que teníamos cuando un pájaro golpea a Felix y se reinicia
		 * la sección.
		 */
		componentes = new CopyOnWriteArrayList<>();
		// componentes = new ArrayList<>();
		componentesABorrar = new ArrayList<>();
	}

	public Edificio getEdificio() {
		return edificio;
	}

	public void agregarComponente(Componente c) {
		componentes.add(c);
	}

	public void borrarComponente(Componente c) {
		componentesABorrar.add(c);
	}

	public List<Dibujable> getComponentesDibujables() {
		List<Dibujable> lista = new ArrayList<>();
		lista.addAll(componentes);
		lista.addAll(edificio.getSeccion(1).getComponentesDibujables());
		lista.addAll(edificio.getSeccion(2).getComponentesDibujables());
		lista.addAll(edificio.getSeccion(3).getComponentesDibujables());
		return lista;
	}

	public void avanzarComponentes() {
		Iterator<Componente> it = componentes.iterator();
		while (it.hasNext()) {
			it.next().avanzar();
		}
		componentes.removeAll(componentesABorrar);
		componentesABorrar.clear();
	}

	/**
	 * 
	 * @param p        posicion del componente (su centro)
	 * @param diametro lado del cuadrado que forma
	 * @return <b>true</b> si colisiona con Felix, <b>false</b> si no se chocan
	 */
	public boolean estaFelix(Posicion p, int diametroX, int diametroY) {
		Posicion pFelix = Juego.getInstance().getFelix().getPosicion();
		int radioX = diametroX / 2;
		int radioY = diametroY / 2;
		if (estaEntre(p.getX() - radioX, pFelix.getX(), pFelix.getX() + 15)
				|| estaEntre(p.getX() + radioX, pFelix.getX(), pFelix.getX() + 15)) {
			if (estaEntre(p.getY() - radioY, pFelix.getY(), pFelix.getY() + 33)
					|| estaEntre(p.getY() + radioY, pFelix.getY(), pFelix.getY() + 33)) {
				return true;
			}
		}
		return false;

	}

	private boolean estaEntre(int num, int a, int b) {
		return (num >= a && num <= b);
	}

	/**
	 * Borra todos los componentes pertenecientes a esa sección
	 * 
	 * @param nroSeccion número de sección (1, 2 o 3)
	 */
	public void borrarComponentesDeSeccion(int nroSeccion) {
		Iterator<Componente> ite = componentes.iterator();
		Componente c;
		while (ite.hasNext()) {
			c = ite.next();
			if (c.getPosicion().getY() < nroSeccion * Seccion.ALTO) {
				borrarComponente(c);
			}
		}

	}

	public List<Componente> getComponentes() {
		return componentes;
	}
}
