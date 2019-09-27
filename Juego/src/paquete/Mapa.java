package paquete;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Mapa {
	private int ancho;
	private int alto;
	private List<Componente> componentes;
	private Edificio edificio;

	public Mapa(int ancho, int alto, Edificio edificio) {
		this.ancho = ancho;
		this.alto = alto;
		this.edificio = edificio;
		componentes = new ArrayList<>();
	}

	public Edificio getEdificio() {
		return edificio;
	}

	public void agregarComponente(Componente c) {
		componentes.add(c);
	}

	public void avanzarComponentes() {
		for (Componente c : componentes) {
			c.avanzar();
		}
	}

	public boolean estaFelix(Point p, int radio) {
		FelixJr felix = Juego.getJuego().getFelix();
		return (estaEntre(felix.getPosicion().x, p.x - (radio / 2), p.x + (radio / 2)))
				&& (estaEntre(felix.getPosicion().y, p.y - (radio / 2), p.y + (radio / 2)));

	}

	private boolean estaEntre(int num, int a, int b) {
		return (num >= a && num <= b);
	}
}
