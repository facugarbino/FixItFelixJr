package componentes;

import juego.Mapa;
import personajes.FelixJr;
import utils.Contador;
import utils.Posicion;

public class Puntaje extends Componente  {
	private int puntaje;
	private final int yFinal;
	protected Mapa mapa;
	
	public Puntaje(int puntaje, Posicion posicion, Mapa mapa) {
		this.posicion = posicion;
		posicion.moverX(FelixJr.ANCHO);
		this.puntaje = puntaje;
		timer = new Contador(15);
		yFinal=posicion.getY()+30;
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
