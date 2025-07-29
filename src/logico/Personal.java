package logico;

import java.util.Date;

public class Personal extends Persona {

	protected int idCargo;
	
	 public Personal(String idPersona, String cedula, String nombre, String apellido, 
			 		 String telefono, String direccion, Date fechaNacimiento, 
			 		 char sexo, int idCargo) 
	 {
		 super(idPersona, cedula, nombre, apellido, telefono, direccion, fechaNacimiento, sexo);
		 this.idCargo = idCargo;
	 }

	public int getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(int idCargo) {
		this.idCargo = idCargo;
	}
}
