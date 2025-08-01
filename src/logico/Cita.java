package logico;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class Cita implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codCita;
	private String idPersona;
	private String nombre;
	private Date fecha;
	private Date hora;
	private String motivo;
	
	public Cita(String codCita, String idPersona, String nombre, Date fecha, Date hora, String motivo) {
		super();
		this.codCita = codCita;
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.fecha = fecha;
		this.hora = hora;
		this.motivo = motivo;
	}

	public String getCodCita() {
		return codCita;
	}

	public void setCodCita(String codCita) {
		this.codCita = codCita;
	}

	public String getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
