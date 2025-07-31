package logico;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import conexionsql.Conexion;
import visual.usuario.Personal;

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
		ArrayList<Usuario> usuarios = new ArrayList<>();

		try {
			Conexion conexion = new Conexion();
			Connection conn = conexion.getConexion();

			String query = "SELECT u.idUsuario, u.usuario, u.contrasenia, u.idRol, r.descripcion, u.idPersona " +
					"FROM Usuario u " +
					"JOIN Rol r ON u.idRol = r.idRol";

			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String idUsuario = rs.getString("idUsuario");
				String usuario = rs.getString("usuario");
				String contrasenia = rs.getString("contrasenia");
				int idRol = rs.getInt("idRol");
				String idPersona = rs.getString("idPersona");

				Usuario u = new Usuario(idUsuario, usuario, contrasenia, idRol, idPersona);
				usuarios.add(u);
			}

			rs.close();
			ps.close();
			conexion.cerrarConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuarios;
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
		ArrayList<Paciente> pacientes = new ArrayList<>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			Conexion conexion = new Conexion();
			con = conexion.getConexion();

			String sql = "SELECT p.idPersona, p.cedula, p.nombre, p.apellido, p.telefono, p.direccion, "
					+ "p.fechaNacimiento, p.sexo, pa.estatura, pa.peso "
					+ "FROM Persona p "
					+ "INNER JOIN Paciente pa ON p.idPersona = pa.idPersona";

			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Paciente paciente = new Paciente(
						rs.getString("idPersona"),
						rs.getString("cedula"),
						rs.getString("nombre"),
						rs.getString("apellido"),
						rs.getString("telefono"),
						rs.getString("direccion"),
						rs.getDate("fechaNacimiento"),
						rs.getString("sexo").charAt(0),
						rs.getFloat("estatura"),
						rs.getFloat("peso")
						);
				pacientes.add(paciente);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return pacientes;
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
		Connection conn = null;
		PreparedStatement psPersona = null;
		PreparedStatement psPaciente = null;

		try {
			Conexion conexion = new Conexion();
			conn = conexion.getConexion();

			// 1. Insertar en Persona
			String sqlPersona = "INSERT INTO Persona (idPersona, cedula, nombre, apellido, telefono, direccion, fechaNacimiento, sexo) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			psPersona = conn.prepareStatement(sqlPersona);
			psPersona.setString(1, paciente.getIdPersona());
			psPersona.setString(2, paciente.getCedula());
			psPersona.setString(3, paciente.getNombre());
			psPersona.setString(4, paciente.getApellido());
			psPersona.setString(5, paciente.getTelefono());
			psPersona.setString(6, paciente.getDireccion());
			psPersona.setDate(7, new java.sql.Date(paciente.getFechaNacimiento().getTime()));
			psPersona.setString(8, String.valueOf(paciente.getSexo()));

			psPersona.executeUpdate();

			// 2. Insertar en Paciente
			String sqlPaciente = "INSERT INTO Paciente (idPersona, estatura, peso) VALUES (?, ?, ?)";
			psPaciente = conn.prepareStatement(sqlPaciente);
			psPaciente.setString(1, paciente.getIdPersona());
			psPaciente.setFloat(2, paciente.getEstatura());
			psPaciente.setFloat(3, paciente.getPeso());

			psPaciente.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al insertar el paciente en la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
		} finally {
			try {
				if (psPaciente != null) psPaciente.close();
				if (psPersona != null) psPersona.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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

	public Consulta getConsultaById(String codigo) {
		Consulta consulta = null;
		String sql = "SELECT codConsulta, idPersona, fecha, diagnostico, indicacion, esImportante FROM Consulta WHERE codConsulta = ?";

		try (Connection conn = new Conexion().getConexion();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, codigo);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					consulta = new Consulta(
							rs.getString("codConsulta"),
							rs.getString("idPersona"),
							rs.getDate("fecha"),
							rs.getString("diagnostico"),
							rs.getString("indicacion"),
							rs.getBoolean("esImportante")
							);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
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

	public Paciente buscarPacienteByIdPersona(String codigo) {
		Paciente paciente = null;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			Conexion conexion = new Conexion();
			con = conexion.getConexion();

			String sql = "SELECT p.idPersona, p.cedula, p.nombre, p.apellido, p.telefono, p.direccion, p.fechaNacimiento, p.sexo, "
					+ "pa.estatura, pa.peso "
					+ "FROM Persona p "
					+ "INNER JOIN Paciente pa ON p.idPersona = pa.idPersona "
					+ "WHERE p.idPersona = ?";

			stmt = con.prepareStatement(sql);
			stmt.setString(1, codigo);

			rs = stmt.executeQuery();

			if (rs.next()) {
				paciente = new Paciente(
						rs.getString("idPersona"),
						rs.getString("cedula"),
						rs.getString("nombre"),
						rs.getString("apellido"),
						rs.getString("telefono"),
						rs.getString("direccion"),
						rs.getDate("fechaNacimiento"),
						rs.getString("sexo").charAt(0),
						rs.getFloat("estatura"),
						rs.getFloat("peso")
						);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
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

	public int updatePaciente(Paciente paciente) {
		 int filasAfectadas = 0;
		    String sqlPersona = "UPDATE Persona SET telefono = ?, direccion = ? WHERE idPersona = ?";
		    String sqlPaciente = "UPDATE Paciente SET estatura = ?, peso = ? WHERE idPersona = ?";

		    try (Connection conn = new Conexion().getConexion()) {
		        conn.setAutoCommit(false); // Para asegurar que ambas actualizaciones se hagan juntas

		        try (
		            PreparedStatement psPersona = conn.prepareStatement(sqlPersona);
		            PreparedStatement psPaciente = conn.prepareStatement(sqlPaciente)
		        ) {
		            // Actualizar en Persona
		            psPersona.setString(1, paciente.getTelefono());
		            psPersona.setString(2, paciente.getDireccion());
		            psPersona.setString(3, paciente.getIdPersona());
		            filasAfectadas += psPersona.executeUpdate();

		            // Actualizar en Paciente
		            psPaciente.setDouble(1, paciente.getEstatura());
		            psPaciente.setDouble(2, paciente.getPeso());
		            psPaciente.setString(3, paciente.getIdPersona());
		            filasAfectadas += psPaciente.executeUpdate();

		            conn.commit(); // Confirmar si todo fue bien
		        } catch (SQLException ex) {
		            conn.rollback(); // Revertir si algo falla
		            ex.printStackTrace();
		            return 0;
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return filasAfectadas;
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
		Connection conn = null;
		PreparedStatement psInsert = null;
		PreparedStatement psUpdateCod = null;
		try {
			conn = new Conexion().getConexion();
			conn.setAutoCommit(false); //Para hacer una transacción

			String sqlInsert = "INSERT INTO Usuario (idUsuario, usuario, contrasenia, idRol, idPersona) VALUES (?, ?, ?, ?, ?)";
			psInsert = conn.prepareStatement(sqlInsert);
			psInsert.setString(1, aux.getIdUsuario());
			psInsert.setString(2, aux.getUsuario());
			psInsert.setString(3, aux.getContrasenia());
			psInsert.setInt(4, aux.getIdRol());
			psInsert.setString(5, aux.getIdPersona());

			int rows = psInsert.executeUpdate();

			if(rows > 0) {
				conn.commit(); //Confirma
			} else {
				conn.rollback(); //No lo hagas
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			try {
				if(conn != null) conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} finally {
			try {
				if(psInsert != null) psInsert.close();
				if(psUpdateCod != null) psUpdateCod.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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

	public EnfermedadPaciente buscarEnfermedadPacienteByCodigo(String idPaciente, String codigoEnfermedad) {
		EnfermedadPaciente enfermedadPaciente = null;
		String sql = "SELECT e.idEnfermedad, e.nombre, e.sintomas, e.idTipoEnfermedad, he.estaCurado " +
				"FROM Enfermedad e " +
				"INNER JOIN Historial_Enfermedad he ON e.idEnfermedad = he.idEnfermedad " +
				"INNER JOIN HistorialClinico hc ON he.idHistorialClinico = hc.idHistorialClinico " +
				"WHERE hc.idPersona = ? AND e.idEnfermedad = ?";
		try (Connection conn = new Conexion().getConexion();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, idPaciente);
			ps.setString(2, codigoEnfermedad);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Enfermedad enfermedad = new Enfermedad(
							rs.getString("idEnfermedad"),
							rs.getString("nombre"),
							rs.getString("sintomas"),
							rs.getInt("idTipoEnfermedad")
							);
					boolean estaCurado = rs.getBoolean("estaCurado");

					enfermedadPaciente = new EnfermedadPaciente(enfermedad, estaCurado);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return enfermedadPaciente;
	}

	public void updateUsuario(Usuario selected) {
		String sql = "UPDATE Usuario SET contrasenia = ?, idRol = ? WHERE idUsuario = ?";

		try (Connection conn = new Conexion().getConexion();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, selected.getContrasenia());
			ps.setInt(2, selected.getIdRol());
			ps.setString(3, selected.getIdUsuario());

			int rows = ps.executeUpdate();

			if (rows > 0) {
				System.out.println("Usuario actualizado correctamente en la base de datos.");
			} else {
				System.out.println("No se encontró el usuario para actualizar.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
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
		String sql = "SELECT u.idUsuario, u.usuario, u.contrasenia, u.idRol, u.idPersona " +
				"FROM Usuario u WHERE u.idUsuario = ?";
		try (Connection conn = new Conexion().getConexion();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, codigo);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					String idUsuario = rs.getString("idUsuario");
					String usuarioStr = rs.getString("usuario");
					String contrasenia = rs.getString("contrasenia");
					int idRol = rs.getInt("idRol");
					String idPersona = rs.getString("idPersona");

					usuario = new Usuario(idUsuario, usuarioStr, contrasenia, idRol, idPersona);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}

	public Map<Integer, String> cargarRoles() {
		Map<Integer, String> roles = new HashMap<>();
		String sql = "SELECT idRol, descripcion FROM Rol";

		try (Connection conn = new Conexion().getConexion();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				roles.put(rs.getInt("idRol"), rs.getString("descripcion"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roles;
	}

	public Medico buscarMedicoByIdPersona(String idPersona) {
		Medico medico = null;
		String sql = "SELECT m.idPersona, m.exequatur, m.idEspecialidad, " +
				"p.nombre, p.apellido, p.cedula, p.telefono, p.direccion, "
				+ "p.fechaNacimiento, p.sexo" +
				"FROM Medico m " +
				"JOIN Persona p ON m.idPersona = p.idPersona " +
				"WHERE m.idPersona = ?";

		try (Connection conn = new Conexion().getConexion();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, idPersona);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					String id = rs.getString("idPersona");
					String cedula = rs.getString("cedula");
					String nombre = rs.getString("nombre");
					String apellido = rs.getString("apellido");
					String telefono = rs.getString("telefono");
					String direccion = rs.getString("direccion");
					Date fechaNacimiento = rs.getDate("fechaNacimiento");
					char sexo = rs.getString("sexo").charAt(0);
					int idEspecialidad = rs.getInt("idEspecialidad");
					int exequatur = rs.getInt("exequatur");

					medico = new Medico(id, cedula, nombre, apellido, telefono, direccion, fechaNacimiento,
							sexo, idEspecialidad, exequatur);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return medico;
	}

	public Personal buscarPersonalbyIdPersona(String idPersona) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Rol> obtenerRoles() {
		ArrayList<Rol> roles = new ArrayList<>();
		String sql = "SELECT idRol, descripcion FROM Rol";

		try (Connection conn = new Conexion().getConexion();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				roles.add(new Rol(rs.getInt("idRol"), rs.getString("descripcion")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roles;
	}

	public Persona buscarPersonabyCodigo(String codigo) {
		Persona persona = null;
		String sql = "SELECT idPersona, nombre, apellido, cedula, telefono, direccion, fechaNacimiento, sexo FROM Persona WHERE idPersona = ?";

		try (Connection conn = new Conexion().getConexion();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, codigo);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				persona = new Persona(
						rs.getString("idPersona"),
						rs.getString("cedula"),
						rs.getString("nombre"),
						rs.getString("apellido"),
						rs.getString("telefono"),
						rs.getString("direccion"),
						rs.getDate("fechaNacimiento"),
						rs.getString("sexo").charAt(0)

						);
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return persona;
	}

	public ArrayList<Persona> getPersonasSinUsuario() {
		ArrayList<Persona> lista = new ArrayList<>();
		String sql = "SELECT p.idPersona, p.nombre, p.apellido, p.cedula, p.telefono, p.direccion, "
				+ "p.fechaNacimiento, p.sexo "
				+ "FROM Persona p "
				+ "LEFT JOIN Usuario u ON p.idPersona = u.idPersona "
				+ "WHERE u.idPersona IS NULL";

		try (Connection conn = new Conexion().getConexion();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Persona persona = new Persona(
						rs.getString("idPersona"),
						rs.getString("cedula"),
						rs.getString("nombre"),
						rs.getString("apellido"),
						rs.getString("telefono"),
						rs.getString("direccion"),
						rs.getDate("fechaNacimiento"),
						rs.getString("sexo").charAt(0)
						);
				lista.add(persona);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	public static String generarNuevoCodigoUsuario() {
		String nuevoCodigo = "U-1"; // Valor por defecto
		try {
			Connection conn = new Conexion().getConexion();
			String sql = "SELECT MAX(CAST(SUBSTRING(idUsuario, 3, LEN(idUsuario)) AS INT)) FROM Usuario";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int ultimoNumero = rs.getInt(1); // Ej: 5
				nuevoCodigo = "U-" + (ultimoNumero + 1);
			}

			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return nuevoCodigo;
	}


	public static String generarNuevoCodigoPaciente() {
		String nuevoCodigo = "Pac-1"; // Valor por defecto si no hay pacientes

		try {
			Connection conn = new Conexion().getConexion();
			String sql = "SELECT MAX(CAST(SUBSTRING(idPersona, 5, LEN(idPersona)) AS INT)) FROM Persona WHERE idPersona LIKE 'Pac-%'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int ultimoNumero = rs.getInt(1);
				nuevoCodigo = "Pac-" + (ultimoNumero + 1);
			}

			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return nuevoCodigo;
	}

	public ArrayList<VacunaAplicada> getVacunasDeUnPaciente(String idPaciente) {

		ArrayList<VacunaAplicada> vacunasAplicadas = new ArrayList<>();
		String sql = "SELECT v.idVacuna, v.nombre, v.codTipoVacuna, v.idFabricante, v.fechaVencimiento, " +
				"v.cantStock, hv.fecha AS fechaAplicacion " +
				"FROM Vacuna v " +
				"INNER JOIN Historial_Vacunacion hv ON v.idVacuna = hv.idVacuna " +
				"INNER JOIN HistorialClinico hc ON hv.idHistorialClinico = hc.idHistorialClinico " +
				"WHERE hc.idPersona = ?";
		try (Connection conn = new Conexion().getConexion();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, idPaciente);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Vacuna vacuna = new Vacuna(
						rs.getString("idVacuna"),
						rs.getString("nombre"),
						rs.getInt("codTipoVacuna"),
						rs.getInt("idFabricante"),
						rs.getDate("fechaVencimiento"),
						rs.getInt("cantStock")
						);
				Date fechaAplicacion = rs.getDate("fechaAplicacion");

				vacunasAplicadas.add(new VacunaAplicada(vacuna, fechaAplicacion));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vacunasAplicadas;
	}

	public TipoVacuna buscarTipoVacunaPorId(int codTipoVacuna) {
		TipoVacuna tipo = null;
		try {
			Connection conn = new Conexion().getConexion();
			String sql = "SELECT nombreTipo FROM TipoVacuna WHERE codTipoVacuna = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, codTipoVacuna);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String nombreTipo = rs.getString("nombreTipo");
				tipo = new TipoVacuna(codTipoVacuna, nombreTipo);
			}

			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tipo;
	}

	public Fabricante buscarFabricantePorId(int idFabricante) {
		Fabricante fabricante = null;
		try {
			Connection conn = new Conexion().getConexion();
			String sql = "SELECT * FROM Fabricante WHERE idFabricante = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idFabricante);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String nombre = rs.getString("nombre");
				fabricante = new Fabricante(idFabricante, nombre);
			}

			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fabricante;
	}

	public ArrayList<Consulta> getConsultasImportantesByIdPersona(String idPersona) {
		ArrayList<Consulta> consultas = new ArrayList<>();

		try {
			Connection conn = new Conexion().getConexion();

			String sql = "SELECT c.codConsulta, c.idPersona, c.fecha, c.diagnostico, c.indicacion, c.esImportante " + 
					"FROM HistorialClinico hc " + 
					"INNER JOIN Historial_Consulta hcon ON hc.idHistorialClinico = hcon.idHistorial " + 
					"INNER JOIN Consulta c ON hcon.codConsulta = c.codConsulta " + 
					"WHERE hc.idPersona = ? AND c.esImportante = 1 ";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, idPersona);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Consulta consulta = new Consulta(
						rs.getString("codConsulta"),
						rs.getString("idPersona"),
						rs.getDate("fecha"),
						rs.getString("diagnostico"),
						rs.getString("indicacion"),
						rs.getBoolean("esImportante")
						);
				consultas.add(consulta);
			}

			rs.close();
			ps.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return consultas;
	}

	public ArrayList<EnfermedadPaciente> getEnfermedadesByHistorial(String idPaciente) {
		ArrayList<EnfermedadPaciente> enfermedades = new ArrayList<>();

		try {
			Connection conn = new Conexion().getConexion();

			String sql = "SELECT e.idEnfermedad, e.nombre, e.sintomas, e.idTipoEnfermedad, he.estaCurado " + 
					"FROM HistorialClinico hc " + 
					"INNER JOIN Historial_Enfermedad he ON hc.idHistorialClinico = he.idHistorialClinico " + 
					"INNER JOIN Enfermedad e ON he.idEnfermedad = e.idEnfermedad " + 
					"WHERE hc.idPersona = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, idPaciente);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Enfermedad enfermedad = new Enfermedad(
						rs.getString("idEnfermedad"),
						rs.getString("nombre"),
						rs.getString("sintomas"),
						rs.getInt("idTipoEnfermedad")
						);
				EnfermedadPaciente ep = new EnfermedadPaciente(enfermedad, rs.getBoolean("estaCurado"));
				enfermedades.add(ep);
			}

			rs.close();
			ps.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return enfermedades;
	}

	public int curarEnfermedadPaciente(String idEnfermedad, String idPersona) {
		String sql = "UPDATE he " +
				"SET he.estaCurado = 1 " +
				"FROM Historial_Enfermedad he " +
				"INNER JOIN HistorialClinico hc ON he.idHistorialClinico = hc.idHistorialClinico " +
				"WHERE hc.idPersona = ? AND he.idEnfermedad = ?";

		try (Connection conn = new Conexion().getConexion();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, idPersona);
			ps.setString(2, idEnfermedad);

			int filasActualizadas = ps.executeUpdate();

			if (filasActualizadas > 0) {
				return 1; // actualización exitosa
			} else {
				return 0; // no se encontró o no se actualizó nada
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return 0; 
		}
	}

	public String getTipoEnfermedadByIdEnfermedad(int idTipoEnfermedad) {
		String tipoNombre = null;
		String sql = "SELECT nombre FROM TipoEnfermedad WHERE idTipoEnfermedad = ?";

		try (Connection conn = new Conexion().getConexion();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, idTipoEnfermedad);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					tipoNombre = rs.getString("nombre");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tipoNombre;
	}

	public void insertarHistorialClinico(String idPersona) {
	    Connection con = null;
	    PreparedStatement ps = null;

	    try {
	        Conexion conexion = new Conexion();
	        con = conexion.getConexion();

	        String sql = "INSERT INTO HistorialClinico (idPersona) VALUES (?)";
	        ps = con.prepareStatement(sql);
	        ps.setString(1, idPersona);
	        ps.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
}
