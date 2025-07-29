package logico;

import java.io.Serializable;

public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String idUsuario;
	private String usuario;
	private String contrasenia;
	private int idRol;
	private int idPersona;
	
	public Usuario(String idUsuario, String usuario, String contrasenia, int idRol, int idPersona) {
		super();
		this.idUsuario = idUsuario;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.idRol = idRol;
		this.idPersona = idPersona;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	
}
