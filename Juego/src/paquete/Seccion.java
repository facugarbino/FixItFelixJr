package paquete;

import java.util.List;

public class Seccion {
	protected int ventanasRotas;
	protected int ventanasConObstaculo;
	private int ventanasReparadas;
	private int nroSeccion;
	protected Ventana[][] ventanas;

	/**
	 * @see constructor usado para reiniciar una sección (debe generarse
	 *      aleatoriamente otra sección con la misma cantidad de ventanas rotas y
	 *      obstáculos)
	 * @param s sección a reiniciar
	 */
	public Seccion(Seccion s) {
		this(s.ventanasRotas, s.ventanasConObstaculo, s.nroSeccion);

	}

	public Seccion(int ventanasRotas, int ventanasConObstaculo, int nroSeccion) {
		this.ventanasRotas = ventanasRotas;
		this.ventanasConObstaculo = ventanasConObstaculo;
		ventanasReparadas = 0;
		boolean[][] conObstaculo = getMatrizRandom(ventanasConObstaculo);
		boolean[][] rotas = getMatrizRandom(ventanasRotas);
		ventanas = new Ventana[3][5];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {
				Posicion Posicion = new Posicion(50 + 15 * (j + 1), 10 + (nroSeccion - 1) * 100 + (2-i)*30 );
				//System.out.print( "("+i+","+j+")"+": " );
				ventanas[i][j] = ventanaRandom(Posicion, rotas[i][j], conObstaculo[i][j]);
			}
		}

	}
	
	public int getNroSeccion() {
		return nroSeccion;
	}

	private Ventana ventanaRandom(Posicion posicion, boolean rota, boolean tieneObstaculo) {
		double random = Math.random();
		System.out.println(
				"La ventana en " + posicion + (rota ? "está rota" : "está sana") + (tieneObstaculo ? " y tiene obstáculo" : ""));
		if (random < 0.5) {
			// Con hojas
			return new VentanaConHojas(posicion, this, tieneObstaculo);
		} else {
			return new VentanaComun(posicion, this, rota, tieneObstaculo);
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
	 * @return <b>true</b> si la ventana pertenece a la sección , o <b>false</b> en
	 *         caso contrario
	 */
	public boolean esDeEstaSeccion(Ventana v) {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 5; j++)
				if (ventanas[i][j].equals(v))
					return true;
		return false;
	}

	public Ventana getVentanaAledana(Ventana v, Orientacion o) {
		Posicion p = getXY(v);
		int x = p.getX();
		int y = p.getY();
		switch (o) {
		case IZQUIERDA:
			if (x > 0)
				return ventanas[y][x - 1];
			break;
		case DERECHA:
			if (x < 4)
				return ventanas[y][x + 1];
			break;
		case ABAJO:
			if (y < 2)
				return ventanas[y + 1][x];
			break;
		case ARRIBA:
			if (y > 0)
				return ventanas[y - 1][x];
			break;
		}
		return null;
	}

	public Posicion getXY(Ventana v) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {
				if (ventanas[i][j].equals(v))
					return new Posicion(j, i);
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
		return ventanas[2][2];
	}

	private boolean[][] getMatrizRandom(int n) {
		// la cantidad de trues tiene q ser n
		boolean[][] matriz = new boolean[3][5];
		int hits = 0;
		while (hits < n) {
			int i = (int) (Math.random() * 3);
			int j = (int) (Math.random() * 5);
			if (!matriz[i][j]) {
				matriz[i][j] = true;
				hits++;
			}
		}
		return matriz;
	}
}
