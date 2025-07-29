package logico;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import conexionsql.Conexion;

public class ClinicaMedica implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Paciente> losPacientes;
	private ArrayList<Medico> losMedicos;
	private ArrayList<Consulta> lasConsultas;
	private ArrayList<Enfermedad> lasEnfermedades;
	private ArrayList<Vacuna> lasVacunas;
	private ArrayList<Cita> lasCitas;
	private ArrayList<Usuario>losUsuarios;

	
	public ArrayList<Usuario> getLosUsuarios() {
		return losUsuarios;
	}

	public void setLosUsuarios(ArrayList<Usuario> losUsuarios) {
		this.losUsuarios = losUsuarios;
	}

	public static int codVacuna;
	public static int codPaciente;
	public static int codMedico;
	public static int codEnfermedad;
	public static int codCita;
	public static int codConsulta;
	public static int codUsuario;
	private static Usuario loginUsuario;
	private static ClinicaMedica clinicaMedica = null;
	
	public ClinicaMedica() {
		super();
		losPacientes = new ArrayList<>();
		losMedicos = new ArrayList<>();
		lasConsultas = new ArrayList<>();
		lasEnfermedades = new ArrayList<>();
		lasVacunas = new ArrayList<>();
		lasCitas = new ArrayList<>();
		losUsuarios = new ArrayList<>();
		codVacuna = 1;
		codPaciente = 1;
		codMedico = 1;
		codEnfermedad = 1; 
		codCita = 1;
		codConsulta = 1;
		codUsuario = 1;
	}
	
	public static Usuario getLoginUsuario() {
		return loginUsuario;
	}

	public static void setLoginUsuario(Usuario loginUsuario) {
		ClinicaMedica.loginUsuario = loginUsuario;
	}

	public static ClinicaMedica getInstance() {
		if(clinicaMedica==null) {
			clinicaMedica = new ClinicaMedica();
		}
		return clinicaMedica;
	}
	
	public static int getCodVacuna() {
		return codVacuna;
	}

	public static void setCodVacuna(int codVacuna) {
		ClinicaMedica.codVacuna = codVacuna;
	}

	public static int getCodPaciente() {
		return codPaciente;
	}

	public static void setCodPaciente(int codPaciente) {
		ClinicaMedica.codPaciente = codPaciente;
	}

	public static int getCodMedico() {
		return codMedico;
	}

	public static void setCodMedico(int codMedico) {
		ClinicaMedica.codMedico = codMedico;
	}

	public static int getCodEnfermedad() {
		return codEnfermedad;
	}

	public static void setCodEnfermedad(int codEnfermedad) {
		ClinicaMedica.codEnfermedad = codEnfermedad;
	}

	public static int getCodCita() {
		return codCita;
	}

	public static int getCodConsulta() {
		return codConsulta;
	}

	public static void setCodConsulta(int codConsulta) {
		ClinicaMedica.codConsulta = codConsulta;
	}

	public static void setCodCita(int codCita) {
		ClinicaMedica.codCita = codCita;
	}

	public static ClinicaMedica getClinicaMedica() {
		return clinicaMedica;
	}

	public static void setClinicaMedica(ClinicaMedica clinicaMedica) {
		ClinicaMedica.clinicaMedica = clinicaMedica;
	}

	public ArrayList<Consulta> getLasConsultas() {
		return lasConsultas;
	}
	public void setLasConsultas(ArrayList<Consulta> lasConsultas) {
		this.lasConsultas = lasConsultas;
	}
	
	public ArrayList<Paciente> getLosPacientes() {
		return losPacientes;
	}

	public void setLosPacientes(ArrayList<Paciente> losPacientes) {
		this.losPacientes = losPacientes;
	}

	public ArrayList<Medico> getLosMedicos() {
		return losMedicos;
	}

	public void setLosMedicos(ArrayList<Medico> losMedicos) {
		this.losMedicos = losMedicos;
	}

	public ArrayList<Enfermedad> getLasEnfermedades() {
		return lasEnfermedades;
	}

	public void setLasEnfermedades(ArrayList<Enfermedad> lasEnfermedades) {
		this.lasEnfermedades = lasEnfermedades;
	}
	
	public ArrayList<Cita> getLasCitas() {
		return lasCitas;
	}

	public void setLasCitas(ArrayList<Cita> lasCitas) {
		this.lasCitas = lasCitas;
	}

	public ArrayList<Vacuna> getLasVacunas() {
		return lasVacunas;
	}

	public void setLasVacunas(ArrayList<Vacuna> lasVacunas) {
		this.lasVacunas = lasVacunas;
	}

	public void insertarPaciente(Paciente paciente) {
		losPacientes.add(paciente);
		codPaciente++;
	}
	
	public void insertarMedico(Medico medico) {
		losMedicos.add(medico);
		codMedico++;
	}
	
	public void insertarEnfermedad(Enfermedad enfermedad) {
		lasEnfermedades.add(enfermedad);
		codEnfermedad++;
	}
	
	public void insertarVacuna (Vacuna vacuna) {
		lasVacunas.add(vacuna);
		codVacuna++;
	}

	public void insertarCita (Cita cita) {
		lasCitas.add(cita);
		codCita++;
	}

	public static int getCodUsuario() {
		return codUsuario;
	}

	public static void setCodUsuario(int codUsuario) {
		ClinicaMedica.codUsuario = codUsuario;
	}

	public Medico buscarMedicoById(String codigo) {
		Medico medico = null;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i < losMedicos.size()) {
			if(losMedicos.get(i).getIdPersona().equalsIgnoreCase(codigo)) {
				medico = losMedicos.get(i);
				encontrado = true;
			}
			i++;
		}
		return medico;
	}

	public void insertarConsulta(Consulta consulta) {
		lasConsultas.add(consulta);
		codConsulta++;
	}
	
	public void insertarConsultaEnHistorial(Consulta consulta, Paciente paciente) {
		paciente.getMiHistorial().getLasConsultas().add(consulta);
	}

	public Consulta buscarConsultaById(String codigo) {
		Consulta consulta = null;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i < lasConsultas.size()) {
			if(lasConsultas.get(i).getIdConsulta().equalsIgnoreCase(codigo)) {
				consulta = lasConsultas.get(i);
				encontrado = true;
			}
			i++;
		}
		return consulta;
	}
	
	public Enfermedad buscarEnfermedadByCodigo(String codigo) {
		Enfermedad enfermedad = null;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i < lasEnfermedades.size()) {
			if(lasEnfermedades.get(i).getIdEnfermedad().equalsIgnoreCase(codigo)) {
				enfermedad = lasEnfermedades.get(i);
				encontrado = true;
			}
			i++;
		}
		return enfermedad;
    }
	
	public void actualizarEnfermedad(Enfermedad enfermedadActualizada) {
	    for (int i = 0; i < lasEnfermedades.size(); i++) {
	        Enfermedad enfermedad = lasEnfermedades.get(i);
	        if (enfermedad.getIdEnfermedad().equals(enfermedadActualizada.getIdEnfermedad())) {
	            lasEnfermedades.set(i, enfermedadActualizada);
	            return;
	        }
	    }
	    System.out.println("Enfermedad no encontrada para actualizar.");
	}
	
	public void actualizarPaciente(Paciente pacienteActualizado) {
        for (int i = 0; i < losPacientes.size(); i++) {
            Paciente paciente = losPacientes.get(i);
            if (paciente.getIdPersona().equals(pacienteActualizado.getIdPersona())) {
                losPacientes.set(i, pacienteActualizado);
                return; 
            }
        }
        System.out.println("Paciente no encontrado para actualizar.");
    }

	public Paciente buscarPacienteByCedula(String codigo) {
		Paciente paciente = null;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i < losPacientes.size()) {
			if(losPacientes.get(i).getCedula().equalsIgnoreCase(codigo)) {
				paciente = losPacientes.get(i);
				encontrado = true;
			}
			i++;
		}
		return paciente;
	}
	
	public int buscarPacienteByCedulaGetIndex(String cedula) {
		int paciente = -1;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i < losPacientes.size()) {
			if(losPacientes.get(i).getCedula().equalsIgnoreCase(cedula)) {
				paciente = i;
				encontrado = true;
			}
			i++;
		}
		return paciente;
	}

	public boolean cedulaPacienteExiste(String cedula) {
        for (Paciente paciente : losPacientes) {
            if (paciente.getCedula().equals(cedula)) {
                return true; 
            }
        }
        return false; 
    }
	
	public void updatePaciente(Paciente selected) {
		int index = buscarPacienteByCedulaGetIndex(selected.getCedula());
		if(index != 1) {
			losPacientes.set(index, selected);
		}
	}

	public Medico buscarMedicoByCedula(String codigo) {
		Medico medico = null;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i < losMedicos.size()) {
			if(losMedicos.get(i).getCedula().equalsIgnoreCase(codigo)) {
				medico = losMedicos.get(i);
				encontrado = true;
			}
			i++;
		}
		return medico;
	}
	
	public int buscarMedicoByCedulaGetIndex(String cedula) {
		int medico = -1;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i < losMedicos.size()) {
			if(losMedicos.get(i).getCedula().equalsIgnoreCase(cedula)) {
				medico = i;
				encontrado = true;
			}
			i++;
		}
		return medico;
	}
	
	public boolean cedulaMedicoExiste(String cedula) {
        for (Medico medico : losMedicos) {
            if (medico.getCedula().equals(cedula)) {
                return true; 
            }
        }
        return false; 
    }
	
	public void updateMedico(Medico selected) {
		int index = buscarMedicoByCedulaGetIndex(selected.getCedula());
		if(index != 1) {
			losMedicos.set(index, selected);
		}
	}
	
	public Cita buscarCitaByIdCita(String idCita) {
	    Cita cita = null;
	    boolean encontrado = false;
	    int i = 0;
	    while (!encontrado && i < lasCitas.size()) { 
	        if (lasCitas.get(i).getIdCita().equalsIgnoreCase(idCita)) { 
	            cita = lasCitas.get(i); 
	            encontrado = true; 
	        }
	        i++; 
	    }
	    return cita; 
	}

	public int buscarCitaByIdGetIndex(String idCita) {
	    int index = -1;
	    boolean encontrado = false;
	    int i = 0;
	    while (!encontrado && i < lasCitas.size()) {
	        if (lasCitas.get(i).getIdCita().equalsIgnoreCase(idCita)) {
	            index = i; 
	            encontrado = true; 
	        }
	        i++;
	    }
	    return index; 
	}
	
	public void eliminarCita(Cita cita) {
	    if (lasCitas.contains(cita)) {
	        lasCitas.remove(cita); 
	    } 
	}
	
	public void updateCita(Cita cita) {
		int index = buscarCitaByIdGetIndex(cita.getIdCita());
		if(index != -1) {
			lasCitas.set(index, cita);
		}
	}

	public boolean existeCita(Date fecha, Date hora, Medico medico) {
		boolean existe = false;
		int i = 0;
		while(!existe && i<lasCitas.size()) {
			if(lasCitas.get(i).getFecha().equals(fecha) && lasCitas.get(i).getHora().equals(hora) && lasCitas.get(i).getMedico().equals(medico)) {
				existe = true;
			}
			i++;
		}
		return existe;
	}

	public Vacuna buscarVacunaByCodigo(String codigo) {
		Vacuna vacuna = null;
	    boolean encontrado = false;
	    int i = 0;
	    while (!encontrado && i < lasVacunas.size()) { 
	        if (lasVacunas.get(i).getIdVacuna().equalsIgnoreCase(codigo)) { 
	        	vacuna = lasVacunas.get(i); 
	            encontrado = true; 
	        }
	        i++; 
	    }
	    return vacuna; 
	}
	
	public ArrayList<Vacuna> getVacunasGenerales() {
        return lasVacunas;
    }
	
	public boolean updateVacuna(String idVacuna, Vacuna nuevaVacuna) {
	    for (int i = 0; i < lasVacunas.size(); i++) {
	        if (lasVacunas.get(i).getIdVacuna().equalsIgnoreCase(idVacuna)) {
	            lasVacunas.set(i, nuevaVacuna);
	            return true; 
	        }
	    }
	    return false;
	}
	
	public boolean eliminarVacuna(String idVacuna) {
	    for (int i = 0; i < lasVacunas.size(); i++) {
	        if (lasVacunas.get(i).getIdVacuna().equalsIgnoreCase(idVacuna)) {
	            lasVacunas.remove(i);
	            return true;
	        }
	    }
	    return false; 
	}

	/*public int getCantPacientesPoseenEnfermedad(Enfermedad enfermedad) {
		int cant = 0;
		for(Paciente paciente:losPacientes) {
			if(paciente.getMisEnfermedades().contains(enfermedad)) {
				cant++;
			}
		}
		return cant;
	}*/

	public boolean confirmarLogin(String usuario, String contrasena) {
		boolean login = false;

		try {
			Conexion miConexion = new Conexion();  // Usas tu clase personalizada
			Connection conn = miConexion.getConexion();

			String query = "SELECT * FROM Usuario WHERE usuario = ? AND contrasenia = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, usuario);
			ps.setString(2, contrasena);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String idUsuario = rs.getString("idUsuario");
				int idRol = rs.getInt("idRol");
				String idPersona = rs.getString("idPersona");

				Usuario usuarioLogin = new Usuario(idUsuario, usuario, contrasena, idRol, idPersona);
				loginUsuario = usuarioLogin;
				login = true;
			}

			rs.close();
			ps.close();
			miConexion.cerrarConexion();  // Cierra la conexión al final

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return login;
	}

	public void regUser(Usuario aux) {
		losUsuarios.add(aux);
		codUsuario++;		
	}

	public Enfermedad buscarEnfermedadById(String codigo) {
		Enfermedad enfermedad = null;
		boolean encontrado = false;
	    int i = 0;
	    while (!encontrado && i < lasEnfermedades.size()) { 
	        if (lasEnfermedades.get(i).getIdEnfermedad().equalsIgnoreCase(codigo)) { 
	        	enfermedad = lasEnfermedades.get(i); 
	            encontrado = true; 
	        }
	        i++; 
	    }
	    return enfermedad;
	}

	/*public Enfermedad buscarEnfermedadPacienteByCodigo(Paciente paciente, String codigo) {
		ArrayList<Enfermedad> enfermedades = paciente.getMisEnfermedades();
		Enfermedad enfermedad = null;
		boolean encontrado = false;
	    int i = 0;
	    while (!encontrado && i < enfermedades.size()) { 
	        if (enfermedades.get(i).getIdEnfermedad().equalsIgnoreCase(codigo)) { 
	        	enfermedad = enfermedades.get(i); 
	            encontrado = true; 
	        }
	        i++; 
	    }
	    return enfermedad;
	}*/

	public void updateUsuario(Usuario selected) {
		int index = buscarUsuarioByID(selected.getCodigo());
		if(index != 1) {
			losUsuarios.set(index, selected);
		}
		
	}

	private int buscarUsuarioByID(String codigo) {
		int usuario = -1;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i < losUsuarios.size()) {
			if(losUsuarios.get(i).getCodigo().equals(codigo)) {
				usuario = i;
				encontrado = true;
			}
			i++;
		}
		return usuario;
	}

	
	public Usuario buscarUsuarioByCodigo(String codigo) {
		Usuario usuario = null;
		boolean encontrado = false;
	    int i = 0;
	    while (!encontrado && i < losUsuarios.size()) { 
	        if (losUsuarios.get(i).getCodigo().equals(codigo)) { 
	        	usuario = losUsuarios.get(i); 
	            encontrado = true; 
	        }
	        i++; 
	    }
	    return usuario;
	}


}
