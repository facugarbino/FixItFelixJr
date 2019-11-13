package componentes;

import juego.Mapa;
import utils.Contador;
import utils.Posicion;

public class Puntaje extends Componente  {
	private int puntaje;
	private final int yFinal;
	protected Mapa mapa;
	
	public Puntaje(int puntaje, Posicion posicion, Mapa mapa) {
		this.posicion = posicion;
		this.puntaje = puntaje;
		timer = new Contador(10);
		yFinal=posicion.getY()+20;
		this.mapa=mapa;
	}
	public int getPuntaje() {
		return puntaje;
	}
	@Override
	protected void comoAvanzo() {
		posicion.moverY(1);
		if (posicion.getY()==yFinal) {
			mapa.borrarComponente(this);
		}
	}
}
