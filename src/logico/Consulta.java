package logico;

import java.io.Serializable;
import java.util.Date;

public class Consulta implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codConsulta;
	private String idPersona;
	private Date fecha;
	private String diagnostico;
	private String indicacion;
	private boolean esImportante;
	
	public Consulta(String codConsulta, String idPersona, Date fecha, String diagnostico, String indicacion,
			boolean esImportante) {
		super();
		this.codConsulta = codConsulta;
		this.idPersona = idPersona;
		this.fecha = fecha;
		this.diagnostico = diagnostico;
		this.indicacion = indicacion;
		this.esImportante = esImportante;
	}

	public String getCodConsulta() {
		return codConsulta;
	}

	public void setCodConsulta(String codConsulta) {
		this.codConsulta = codConsulta;
	}

	public String getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getIndicacion() {
		return indicacion;
	}

	public void setIndicacion(String indicacion) {
		this.indicacion = indicacion;
	}

	public boolean isEsImportante() {
		return esImportante;
	}

	public void setEsImportante(boolean esImportante) {
		this.esImportante = esImportante;
	}
	
}
