package logico;

import java.io.Serializable;

public class TipoEnfermedad implements Serializable {

	/*
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private int idTipoEnfermedad;
	private String nombreTipo;
	
	public TipoEnfermedad(int idTipoEnfermedad, String nombreTipo) {
		super();
		this.idTipoEnfermedad = idTipoEnfermedad;
		this.nombreTipo = nombreTipo;
	}

	public int getIdTipoEnfermedad() {
		return idTipoEnfermedad;
	}

	public void setIdTipoEnfermedad(int idTipoEnfermedad) {
		this.idTipoEnfermedad = idTipoEnfermedad;
	}

	public String getNombreTipo() {
		return nombreTipo;
	}

	public void setNombreTipo(String nombreTipo) {
		this.nombreTipo = nombreTipo;
	}
	
}
