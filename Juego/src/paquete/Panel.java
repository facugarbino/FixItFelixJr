package paquete;

public class Panel {

	private EstadoPanel estado;

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
