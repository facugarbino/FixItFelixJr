package paquete;
import java.util.ArrayList;
import java.util.Iterator;
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
		Iterator<Componente> it = componentes.iterator();
		while (it.hasNext()) {
			it.next().avanzar();
		}
	}

	public boolean estaFelix(Posicion p, int radio) {
		FelixJr felix = Juego.getJuego().getFelix();
		return (estaEntre(felix.getPosicion().getX(), p.getX() - (radio / 2), p.getX() + (radio / 2)))
				&& (estaEntre(felix.getPosicion().getY(), p.getY() - (radio / 2), p.getY() + (radio / 2)));

	}

	private boolean estaEntre(int num, int a, int b) {
		return (num >= a && num <= b);
	}
}
