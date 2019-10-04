package paquete;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import taller2.modelo.Dibujable;
import taller2.modelo.InformacionDibujable;

public class Mapa {
	private int ancho;
	private int alto;
	private List<Componente> componentes;
	private Edificio edificio;
	private List<Componente> componentesABorrar;

	public Mapa(int ancho, int alto, Edificio edificio) {
		this.ancho = ancho;
		this.alto = alto;
		this.edificio = edificio;
		componentes = new ArrayList<>();
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
	
	public List<Dibujable> getComponentesDibujables(){
		List<Dibujable> lista = new ArrayList<>();
		Iterator<Componente> ite = componentes.iterator();
		while (ite.hasNext()){
			lista.add(ite.next());
		}
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
	 * @param p posicion del componente
	 * @param diametro lado del cuadrado que forma 
	 * @return <b>true</b> si colisiona con Felix,
	 * <b>false</b> si no se chocan
	 */
	public boolean estaFelix(Posicion p, int diametro) {
		Posicion pFelix = Juego.getJuego().getFelix().getPosicion();
		int radio = diametro / 2;
		// return (estaEntre(felix.getPosicion().getX(), p.getX() - (radio / 2),
		// p.getX() + (radio / 2)))
		// && (estaEntre(felix.getPosicion().getY(), p.getY() - (radio / 2), p.getY() +
		// (radio / 2)));
		if (estaEntre(p.getX() - radio, pFelix.getX(), pFelix.getX() + 5)
				|| estaEntre(p.getX() + radio, pFelix.getX(), pFelix.getX() + 5)) {
			if (estaEntre(p.getY() - radio, pFelix.getY(), pFelix.getY() + 20)
					|| estaEntre(p.getY() + radio, pFelix.getY(), pFelix.getY() + 20)) {
				return true;
			}
		}
		return false;

	}

	private boolean estaEntre(int num, int a, int b) {
		return (num >= a && num <= b);
	}
}
