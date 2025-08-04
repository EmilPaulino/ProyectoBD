package logico;

import java.io.Serializable;

public class Fabricante implements Serializable{
	
	/*
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private int idFabricante;
	private String nombre;
	
	public Fabricante(int idFabricante, String nombre) {
		super();
		this.idFabricante = idFabricante;
		this.nombre = nombre;
	}

	public int getIdFabricante() {
		return idFabricante;
	}

	public void setIdFabricante(int idFabricante) {
		this.idFabricante = idFabricante;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return nombre; // Así se mostrará en el ComboBox
	}

}
