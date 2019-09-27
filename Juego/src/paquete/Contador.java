package paquete;

public class Contador {
	private int limite;
	private int contador;

	public Contador(double limite) {
		this.limite = (int) (10000000 * limite);
		contador = 0;
	}

	public void resetear() {
		contador = 0;
	}

	public boolean contar() {
		return (++contador == limite);
	}
}
