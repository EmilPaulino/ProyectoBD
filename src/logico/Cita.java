package logico;

import java.io.Serializable;
import java.util.Date;

public class Cita implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String idCita;
	private String nombrePersona;
	private Medico medico;
	private Date fecha;
	private Date hora;
	private String motivo;
	
	public Cita(String idCita, String nombrePersona, Medico medico, Date fecha, Date hora, String motivo) {
		super();
		this.idCita = idCita;
		this.nombrePersona = nombrePersona;
		this.medico = medico;
		this.fecha = fecha;
		this.hora = hora;
		this.motivo = motivo;
	}

	public String getIdCita() {
		return idCita;
	}

	public void setIdCita(String idCita) {
		this.idCita = idCita;
	}

	public String getNombrePersona() {
		return nombrePersona;
	}

	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
}
