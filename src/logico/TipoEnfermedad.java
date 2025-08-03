package logico;

import java.io.Serializable;

public class TipoEnfermedad implements Serializable {

	/*
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private int codTipoEnfermedad;
	private String nombreTipo;
	
	public TipoEnfermedad(int idTipoEnfermedad, String nombreTipo) {
		super();
		this.codTipoEnfermedad = idTipoEnfermedad;
		this.nombreTipo = nombreTipo;
	}

	public int getIdTipoEnfermedad() {
		return codTipoEnfermedad;
	}

	public void setIdTipoEnfermedad(int idTipoEnfermedad) {
		this.codTipoEnfermedad = idTipoEnfermedad;
	}

	public String getNombreTipo() {
		return nombreTipo;
	}

	public void setNombreTipo(String nombreTipo) {
		this.nombreTipo = nombreTipo;
	}
	
	@Override
	public String toString() {
		return nombreTipo; // Así se mostrará en el ComboBox
	}
}
