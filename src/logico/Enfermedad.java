package logico;

import java.io.Serializable;

public class Enfermedad implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int idEnfermedad;
	private String nombre;
	private String sintomas;
	private int idTipoEnfermedad;
	
	public Enfermedad(int idEnfermedad, String nombre, String sintomas, int idTipoEnfermedad) {
		super();
		this.idEnfermedad = idEnfermedad;
		this.nombre = nombre;
		this.sintomas = sintomas;
		this.idTipoEnfermedad = idTipoEnfermedad;
	}

	public int getIdEnfermedad() {
		return idEnfermedad;
	}

	public void setIdEnfermedad(int idEnfermedad) {
		this.idEnfermedad = idEnfermedad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSintomas() {
		return sintomas;
	}

	public void setSintomas(String sintomas) {
		this.sintomas = sintomas;
	}

	public int getIdTipoEnfermedad() {
		return idTipoEnfermedad;
	}

	public void setIdTipoEnfermedad(int idTipoEnfermedad) {
		this.idTipoEnfermedad = idTipoEnfermedad;
	}
	
}
