package ventanas.paneles;

import utils.Posicion;

public class Panel {

	private EstadoPanel estado;
	private Posicion posicion;

//	public Panel(EstadoPanel estado) {
//		this.estado = estado;
//	}
	public Panel(EstadoPanel estado, Posicion posicion) {
		this.estado = estado;
		this.posicion = posicion;
	}
	
	/**
	 * 
	 * @return <b>true</b> si el panel estaba roto, y lo convierte en un panel sano,
	 *         o <b>false</b> en caso de que ya estuviera sano
	 */
	public boolean reparar() {
		if (estado.reparar()) {
			estado = new Sano();
			return true;
		}
		return false;
	}

	public Posicion getPosicion() {
		return posicion;
	}
	
	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	public boolean estaRoto() {
		return estado.estaRoto();
	}
	
	public EstadoPanel getEstado() {
		return estado;
	}
}
