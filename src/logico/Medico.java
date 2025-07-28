package logico;

import java.util.Date;

public class Medico extends Persona {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected String especialidad;
	protected int exequatur;
	
	public Medico(String idPersona, String cedula, String nombre, String apellido, String telefono, String direccion,
			Date fechaNacimiento, int edad, String sexo, String especialidad, int exequatur) {
		super(idPersona, cedula, nombre, apellido, telefono, direccion, fechaNacimiento, edad, sexo);
		this.especialidad = especialidad;
		this.exequatur = exequatur;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public int getExequatur() {
		return exequatur;
	}

	public void setExequatur(int exequatur) {
		this.exequatur = exequatur;
	}
	

}
