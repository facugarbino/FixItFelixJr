package utils;

public class Contador {
	private int limite;
	//private int contador;
	private long contador;

	public Contador(double limite) {
		//this.limite = (int) (10000 * limite);
		this.limite = (int) (10 * limite);
		//contador = 0;
		contador = System.currentTimeMillis();
	}

	/**
	 * Resetea el timer para volver a contar otro ciclo
	 */
	public void resetear() {
		//contador = 0;
		contador = System.currentTimeMillis();
	}

	/**
	 * 
	 * @return <b>true</b> si el timer cumplió
	 * su ciclo
	 * 
	 */
	public boolean contar() {
		return System.currentTimeMillis()-contador>limite;
		//return (++contador == limite);
	}
}
