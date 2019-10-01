package paquete;

public class Contador {
	private int limite;
	private int contador;

	public Contador(double limite) {
		this.limite = (int) (10000 * limite);
		contador = 0;
	}

	/**
	 * Resetea el timer para volver a contar otro ciclo
	 */
	public void resetear() {
		contador = 0;
	}

	/**
	 * 
	 * @return <b>true</b> si el timer cumplió
	 * su ciclo
	 * 
	 */
	public boolean contar() {
		return (++contador == limite);
	}
}
