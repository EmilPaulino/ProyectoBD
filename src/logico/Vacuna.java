package logico;

import java.io.Serializable;
import java.util.Date;

public class Vacuna implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int idVacuna;
	private String nombre;
	private int codTipoVacuna;
	private int idFabricante;
	private Date fechaVencimiento;
	private int cantStock;
	
	public Vacuna(int idVacuna, String nombre, int codTipoVacuna, int idFabricante, Date fechaVencimiento,
			int cantStock) {
		super();
		this.idVacuna = idVacuna;
		this.nombre = nombre;
		this.codTipoVacuna = codTipoVacuna;
		this.idFabricante = idFabricante;
		this.fechaVencimiento = fechaVencimiento;
		this.cantStock = cantStock;
	}

	public int getIdVacuna() {
		return idVacuna;
	}

	public void setIdVacuna(int idVacuna) {
		this.idVacuna = idVacuna;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCodTipoVacuna() {
		return codTipoVacuna;
	}

	public void setCodTipoVacuna(int codTipoVacuna) {
		this.codTipoVacuna = codTipoVacuna;
	}

	public int getIdFabricante() {
		return idFabricante;
	}

	public void setIdFabricante(int idFabricante) {
		this.idFabricante = idFabricante;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public int getCantStock() {
		return cantStock;
	}

	public void setCantStock(int cantStock) {
		this.cantStock = cantStock;
	}
	
}
