package utils;

/**
 * Se usa para el timing de las acciones que se deben llevar a cabo
 * repetidamente en el tiempo.
 * 
 * @author facu
 *
 */
public class Contador {

	/*
	 * Las sentencias comentadas son la forma anterior en que estaba implementada la
	 * clase Contador, pero posiblemente la cantidad de veces por segundo que se
	 * ejecutan los métodos pueda variar según la carga del procesador. Por eso,
	 * mejor usamos el tiempo del sistema para comparar si ya ha pasado cierta
	 * cantidad de milisegundos.
	 */

	// private int contador;
	private int limite;
	private long contador;

	public Contador(int limite) {
		// this.limite = (int) (10000 * limite);
		// contador = 0;
		
		this.limite = (int) (limite);
		//el 10 lo usamos como escala, podrían cambiarse
		//de acá todas las frecuencias a la vez
		contador = System.currentTimeMillis();
	}

	/**
	 * Resetea el timer para volver a contar otro ciclo
	 */
	public void resetear() {
		// contador = 0;
		contador = System.currentTimeMillis();
	}

	/**
	 * 
	 * @return <b>true</b> si el timer cumplió su ciclo
	 * 
	 */
	public boolean contar() {
		// return (++contador == limite);
		return (System.currentTimeMillis() - contador) > limite;
	}
}
