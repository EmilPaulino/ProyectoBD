package logico;

import java.io.Serializable;

public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigo;
	private String nombre;
	private String contrasena;
	private String rol;
	private Medico medicoRelacionado;
	
	public Usuario(String codigo,String nombre, String contrasena, String rol, Medico medicoRelacionado) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.contrasena = contrasena;
		this.rol = rol;
		this.medicoRelacionado = medicoRelacionado;
	}

	
	public Medico getMedicoRelacionado() {
		return medicoRelacionado;
	}


	public void setMedicoRelacionado(Medico medicoRelacionado) {
		this.medicoRelacionado = medicoRelacionado;
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	
	
}
