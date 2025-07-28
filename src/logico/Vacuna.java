package logico;

import java.io.Serializable;
import java.util.Date;

public class Vacuna implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String idVacuna;
	private Date fechaVencimiento;
	private String nombreVacuna;
	private String tipo;
	private String fabricante;
	private int cantidad;
	
	public Vacuna(String idVacuna, Date fecha, String nombreVacuna, String tipo, String fabricante, int cantidad) {
		super();
		this.idVacuna = idVacuna;
		this.fechaVencimiento = fecha;
		this.nombreVacuna = nombreVacuna;
		this.tipo = tipo;
		this.fabricante = fabricante;
		this.cantidad = cantidad;
	}
	
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getIdVacuna() {
		return idVacuna;
	}
	public void setIdVacuna(String idVacuna) {
		this.idVacuna = idVacuna;
	}
	public Date getFecha() {
		return fechaVencimiento;
	}
	public void setFecha(Date fecha) {
		this.fechaVencimiento = fecha;
	}
	public String getNombreVacuna() {
		return nombreVacuna;
	}
	public void setNombreVacuna(String nombreVacuna) {
		this.nombreVacuna = nombreVacuna;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	
	
}
