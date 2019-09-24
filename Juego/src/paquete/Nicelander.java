package paquete;

import java.awt.Color;

public class Nicelander {

	private Color color;
	private Ventana ventana;
	
	public Nicelander(Color color, Ventana ventana) {
		this.color = color;
		this.ventana = ventana;
	}
	
	public void ponerPastel() {
		ventana.ponerPastel(new Pastel(new Color((float) Math.random() * 255, (float) Math.random() * 255, (float) Math.random() * 255)));
	}
}
