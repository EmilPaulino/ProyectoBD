package logico;

import java.util.Date;

public class Medico extends Persona {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected int idEspecialidad;
	protected int exequatur;
	
	public Medico(String idPersona, String cedula, String nombre, String apellido, String telefono, String direccion,
			Date fechaNacimiento, int edad, String sexo, int idEspecialidad, int exequatur) {
		super(idPersona, cedula, nombre, apellido, telefono, direccion, fechaNacimiento, sexo);
		this.idEspecialidad = idEspecialidad;
		this.exequatur = exequatur;
	}

	public int getEspecialidad() {
		return idEspecialidad;
	}

	public void setEspecialidad(int idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}

	public int getExequatur() {
		return exequatur;
	}

	public void setExequatur(int exequatur) {
		this.exequatur = exequatur;
	}
	

}
