package logico;

import java.io.Serializable;

public class Enfermedad implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String idEnfermedad;
	private String nombre;
	private String sintomas;
	private String tipo;
	private boolean curada;
	
	public Enfermedad(String idEnfermedad, String nombre, String sintomas, String tipo) {
		super();
		this.idEnfermedad = idEnfermedad;
		this.nombre = nombre;
		this.sintomas = sintomas;
		this.tipo = tipo;
	}
	
	
	public boolean isCurada() {
		return curada;
	}

	public void setCurada(boolean curada) {
		this.curada = curada;
	}



	public String getIdEnfermedad() {
		return idEnfermedad;
	}
	public void setIdEnfermedad(String idEnfermedad) {
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
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
