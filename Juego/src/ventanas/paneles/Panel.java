package ventanas.paneles;

public class Panel {

	private EstadoPanel estado;
	
	/**
	 * 
	 * @return <b>true</b> si el panel estaba roto,
	 * y lo convierte en un panel sano, o <b>false</b>
	 * en caso de que ya estuviera sano
	 */
	public boolean reparar() {
		if (estado.reparar()) {
			estado = new Sano();
			return true;
		}
		return false;
	}

	public boolean estaRoto() {
		return estado.estaRoto();
	}

	public Panel(EstadoPanel estado) {
		this.estado = estado;
	}
}
