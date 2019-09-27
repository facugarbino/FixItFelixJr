package paquete;

public class Posicion {
	private int x;
	private int y;

	public Posicion(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void moverX(int n) {
		x += n;
	}

	public void moverY(int n) {
		y += n;
	}

	public String toString() {
		return "(" + x + "," + y + ")";
	}
}
