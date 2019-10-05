package juego;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import componentes.Componente;
import graficador.modelo.Dibujable;
import graficador.modelo.InformacionDibujable;
import utils.Posicion;

public class Mapa {
	public static final int ANCHO = 200;
	public static final int ALTO = 400;
	private List<Componente> componentes;
	private Edificio edificio;
	private List<Componente> componentesABorrar;

	public Mapa(Edificio edificio) {
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