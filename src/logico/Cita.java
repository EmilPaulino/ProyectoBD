package logico;

import java.io.Serializable;
import java.util.Date;

public class Cita implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int codCita;
	private int idPersona;
	private String nombre;
	private Date fecha;
	private Date hora;
	private String motivo;
	
	public Cita(int codCita, int idPersona, String nombre, Date fecha, Date hora, String motivo) {
		super();
		this.codCita = codCita;
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.fecha = fecha;
		this.hora = hora;
		this.motivo = motivo;
	}

	public int getCodCita() {
		return codCita;
	}

	public void setCodCita(int codCita) {
		this.codCita = codCita;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
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
