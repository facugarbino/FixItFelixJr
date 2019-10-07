package graficador.modelo;

import java.awt.Color;

public class InformacionDibujable {
	private int x;

	private int y;

	private Character representacion;
	private Color color;

	public InformacionDibujable(int x, int y, Character representacion, Color color) {
		this.x = x;
		this.y = y;
		this.representacion = representacion;
		this.color = color;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Character getRepresentacion() {
		return representacion;
	}

	public Color getColor() {
		return color;
	}

	public void setRepresentacion(Character representacion) {
		this.representacion = representacion;
	}
}