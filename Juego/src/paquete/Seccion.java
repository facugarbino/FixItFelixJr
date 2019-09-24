package paquete;

import java.util.List;

public class Seccion {
	private int ventanasRotas;
	private int ventanasConObstaculo;
	private int ventanasReparadas;
	private Ventana[][] ventanas;

	public Seccion(int ventanasRotas, int ventanasConObstaculo, int nroSeccion) {
		this.ventanasRotas = ventanasRotas;
		this.ventanasConObstaculo = ventanasConObstaculo;
		ventanasReparadas = 0;
		ventanas = new Ventana[3][5];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {
				 
			}
		}
	}

	public boolean esDeEstaSeccion(Ventana v) {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 5; j++)
				if (ventanas[i][j].equals(v))
					return true;
		return false;
	}

	public Ventana getVentanaAledana(Ventana v, Orientacion o) {
		XXXX
	}

	public void seArregloUnaVentana() {
		ventanasReparadas++;
	}

	public boolean estaSana() {
		return ventanasReparadas == ventanasRotas;
	}
}
