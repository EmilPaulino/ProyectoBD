package logico;

import java.io.Serializable;

public class TipoVacuna implements Serializable {
	
	/*
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private int codTipoVacuna;
	private String nombreTipo;
	
	public TipoVacuna(int codTipoVacuna, String nombreTipo) {
		super();
		this.codTipoVacuna = codTipoVacuna;
		this.nombreTipo = nombreTipo;
	}

	public int getCodTipoVacuna() {
		return codTipoVacuna;
	}

	public void setCodTipoVacuna(int codTipoVacuna) {
		this.codTipoVacuna = codTipoVacuna;
	}

	public String getNombreTipo() {
		return nombreTipo;
	}

	public void setNombreTipo(String nombreTipo) {
		this.nombreTipo = nombreTipo;
	}

}
