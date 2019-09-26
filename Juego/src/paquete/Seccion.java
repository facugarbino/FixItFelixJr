package paquete;

import java.awt.Point;
import java.util.List;

public class Seccion {
	protected int ventanasRotas;
	protected int ventanasConObstaculo;
	private int ventanasReparadas;
	protected Ventana[][] ventanas;

	/**
	 * @see constructor usado para reiniciar una sección
	 * (debe generarse aleatoriamente otra sección con la 
	 * misma cantidad de ventanas rotas y obstáculos)
	 * @param s sección a reiniciar
	 */
	public Seccion(Seccion s) {
		this(s.ventanasRotas, s.ventanasConObstaculo);
		
	}
	
	public Seccion(int ventanasRotas, int ventanasConObstaculo) {
		this.ventanasRotas = ventanasRotas;
		this.ventanasConObstaculo = ventanasConObstaculo;
		ventanasReparadas = 0;
		ventanas = new Ventana[3][5];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {

			}
		}
	}

	public void generarNicelanders() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {
				ventanas[i][j].generarNicelander();
			}
		}
	}

	/**
	 * 
	 * @param v ventana
	 * @return <b>true</b> si la ventana pertenece a la
	 * sección , o <b>false</b> en caso contrario
	 */
	public boolean esDeEstaSeccion(Ventana v) {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 5; j++)
				if (ventanas[i][j].equals(v))
					return true;
		return false;
	}

	public Ventana getVentanaAledana(Ventana v, Orientacion o) {
		Point p = getXY(v);
		int x = p.x;
		int y = p.y;
		switch (o) {
		case IZQUIERDA:
			if (x > 0)
				return ventanas[x - 1][y];
			break;
		case DERECHA:
			if (x < 4)
				return ventanas[x + 1][y];
			break;
		case ARRIBA:
			if (y < 4)
				return ventanas[x][y + 1];
			break;
		case ABAJO:
			if (y > 0)
				return ventanas[x][y - 1];
			break;
		}
		return null;
	}

	public Point getXY(Ventana v) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {
				if (ventanas[i][j].equals(v))
					return new Point(i, j);
			}
		}
		return null;
	}

	public void seArregloUnaVentana() {
		ventanasReparadas++;
	}

	public boolean estaSana() {
		return ventanasReparadas == ventanasRotas;
	}
	
	public Ventana getVentanaInicial() {
		return ventanas[2][0];
	}
}
