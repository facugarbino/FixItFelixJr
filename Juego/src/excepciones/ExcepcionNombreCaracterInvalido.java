package excepciones;

public class ExcepcionNombreCaracterInvalido extends ExcepcionNombreInvalido{
	public String getMessage() {
		return "El nombre no puede contener espacios en blanco o carácteres extraños.";
	}

}
