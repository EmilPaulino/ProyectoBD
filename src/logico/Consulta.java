package logico;

import java.io.Serializable;
import java.util.Date;

public class Consulta implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String idConsulta;
	private Date fecha;
	private Medico medico;
	private Paciente paciente;
	private String diagnostico;
	private String indicacion;
	private boolean importante;
	
	public Consulta(String idConsulta, Medico medico, Paciente paciente, String diagnostico,
			String indicacion, boolean importante) {
		super();
		this.idConsulta = idConsulta;
		this.fecha = new Date();
		this.medico = medico;
		this.paciente = paciente;
		this.diagnostico = diagnostico;
		this.indicacion = indicacion;
		this.importante = importante;
	}

	public String getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(String idConsulta) {
		this.idConsulta = idConsulta;
	}

	public Date getFecha() {
		return fecha;
	}


	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
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

	public boolean isImportante() {
		return importante;
	}

	public void setImportante(boolean importante) {
		this.importante = importante;
	}
}
