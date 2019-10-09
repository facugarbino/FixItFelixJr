package ventanas.paneles;

/**
 * Clase abstracta que representa a alguno de los 3 
 * estados posibles de un panel.
 * @author Garbino y Rodríguez Murphy
 *
 */
public abstract class EstadoPanel {

	public boolean reparar() {
		return true;
	}

	public boolean estaRoto() {
		return false;
	}
}
