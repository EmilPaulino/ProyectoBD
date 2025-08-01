package logico;

import java.io.Serializable;
import java.util.Date;

public class Consulta implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codConsulta;
	private String idMedico;
	private Date fecha;
	private String diagnostico;
	private String indicacion;
	private boolean esImportante;
	private String idPaciente;
	
	public Consulta(String codConsulta, String idMedico, Date fecha, String diagnostico, String indicacion,
			boolean esImportante, String idPaciente) {
		super();
		this.codConsulta = codConsulta;
		this.idMedico = idMedico;
		this.fecha = fecha;
		this.diagnostico = diagnostico;
		this.indicacion = indicacion;
		this.esImportante = esImportante;
		this.idPaciente = idPaciente;
	}

	public String getCodConsulta() {
		return codConsulta;
	}

	public void setCodConsulta(String codConsulta) {
		this.codConsulta = codConsulta;
	}

	public String getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(String idMedico) {
		this.idMedico = idMedico;
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

	public String getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(String idPaciente) {
		this.idPaciente = idPaciente;
	}
	
}
