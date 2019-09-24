package paquete;

public class Panel {

	private EstadoPanel estado;

	public boolean reparar() {
		return estado.reparar();
	}

	public boolean estaRoto() {
		return estado.estaRoto();
	}
	
	public Panel(EstadoPanel estado) {
		this.estado = estado;
	}
}
