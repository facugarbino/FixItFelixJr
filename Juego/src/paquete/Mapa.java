package paquete;

import java.awt.Point;
import java.util.List;

public class Mapa {
	private int ancho;
	private int alto;
	private FelixJr felix;
	private Ralph ralph;
	private List<Componente> componentes;
	private Edificio edificio;
	
	public Mapa(int ancho, int alto, Nivel n) {
		ralph = new Ralph(n.getCantLadrillos(), n.getFrecuenciaLadrillo(), n.getVelocidadLadrillo());
	}
	
	public Edificio getEdificio() {
		return edificio;
	}
	public boolean felixMartilla() { 
		return felix.darMartillazo();
	}
	public void agregarComponente(Componente c) {
		componentes.add(c);
		
	}
	public void avanzarComponentes() {
		for (Componente c : componentes) {
			c.avanzar();
		}
	}
	public void moverRalph() {
		
	}
	public void moverFelix(Orientacion o) {
		felix.mover(o);
	}
	public boolean estaFelix(Point p, int radio) {
		return (estaEntre(felix.posicion.x, p.x - (radio/2), p.x + (radio/2)))
				&& (estaEntre(felix.posicion.y, p.y - (radio/2), p.y + (radio/2)));
		
	}
	public void golpearFelix(Ladrillo l) {
		felix.golpear(l);
	}
	public void golpearFelix(Pajaro p) {
		felix.golpear(p);
		
	}
	public boolean estaEntre(int num, int a, int b ) {
		return (num >= a && num <=b);
	}
}
