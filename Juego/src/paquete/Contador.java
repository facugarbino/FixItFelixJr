package paquete;

public class Contador {
	private int limite;
	private int contador;

	public Contador(double limite) {
		this.limite = (int) (10000 * limite);
		contador = 0;
	}

	/**
	 * resetea el timer para volver a contar otro ciclo
	 */
	public void resetear() {
		contador = 0;
	}

	/**
	 * 
	 * @return <b>true</b> si el timer cumplió
	 * su ciclo
	 * @see se debe usar <b>resetear()</b> para seguir
	 * contando ciclos
	 */
	public boolean contar() {
		return (++contador == limite);
	}
}
