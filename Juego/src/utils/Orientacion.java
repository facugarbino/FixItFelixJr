package utils;

public enum Orientacion {
	IZQUIERDA, DERECHA, ARRIBA, ABAJO;

	/**
	 * 
	 * @return una orientacion con la misma
	 * dirección, pero sentido contrario
	 * 
	 */
	public Orientacion invertir() {
		switch (this) {
		case IZQUIERDA:
			return DERECHA;
		case DERECHA:
			return IZQUIERDA;
		case ABAJO:
			return ARRIBA;
		case ARRIBA:
			return ABAJO;
		default:
			return null;
		}
	}
}
