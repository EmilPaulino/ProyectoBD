package logico;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

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
	
	public static int calcularEdad(Date fechaNacimiento) {
	    if (fechaNacimiento == null) return 0;

	    Calendar nacimiento = Calendar.getInstance();
	    nacimiento.setTime(fechaNacimiento);

	    Calendar hoy = Calendar.getInstance();

	    int edad = hoy.get(Calendar.YEAR) - nacimiento.get(Calendar.YEAR);

	    if (hoy.get(Calendar.DAY_OF_YEAR) < nacimiento.get(Calendar.DAY_OF_YEAR)) {
	        edad--;
	    }

	    return edad;
	}

	public ArrayList<Medico> getLosMedicos() {
	    ArrayList<Medico> medicos = new ArrayList<>();
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        conn = new Conexion().getConexion();
	        String sql = "SELECT p.idPersona, p.cedula, p.nombre, p.apellido, p.telefono, p.direccion, " +
	                     "p.fechaNacimiento, p.sexo, m.idEspecialidad, m.exequatur " +
	                     "FROM Persona p " +
	                     "JOIN Medico m ON p.idPersona = m.idPersona";

	        ps = conn.prepareStatement(sql);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            String idPersona = rs.getString("idPersona");
	            String cedula = rs.getString("cedula");
	            String nombre = rs.getString("nombre");
	            String apellido = rs.getString("apellido");
	            String telefono = rs.getString("telefono");
	            String direccion = rs.getString("direccion");
	            Date fechaNacimiento = rs.getDate("fechaNacimiento");
	            char sexo = rs.getString("sexo").charAt(0);
	            int idEspecialidad = rs.getInt("idEspecialidad");
	            int exequatur = rs.getInt("exequatur");

	            Medico medico = new Medico(idPersona, cedula, nombre, apellido, telefono, direccion,
	                                       fechaNacimiento, sexo, idEspecialidad, exequatur);
	            medicos.add(medico);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return medicos;
	}
	
	public String getEspecialidadByIdEspecialidad(int idEspecialidad) {
	    String descripcion = null;
	    String sql = "SELECT descripcion FROM Especialidad WHERE idEspecialidad = ?";

	    try (Connection conn = new Conexion().getConexion();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, idEspecialidad);

	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                descripcion = rs.getString("descripcion");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return descripcion;
	}

	public void setLosMedicos(ArrayList<Medico> losMedicos) {
		this.losMedicos = losMedicos;
	}
	
	public ArrayList<TipoVacuna> getLosTiposVacunas() {
	    ArrayList<TipoVacuna> tipos = new ArrayList<>();
	    String sql = "SELECT codTipoVacuna, nombreTipo FROM TipoVacuna";

	    try (Connection conn = new Conexion().getConexion();
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            int cod = rs.getInt("codTipoVacuna");
	            String nombre = rs.getString("nombreTipo");

	            TipoVacuna tipo = new TipoVacuna(cod, nombre);
	            tipos.add(tipo);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return tipos;
	}
	
	public String getTipoVacunaByCodTipoVacuna(int idTipoVacuna) {
	    String nombreTipo = null;
	    String sql = "SELECT nombreTipo FROM TipoVacuna WHERE codTipoVacuna = ?";

	    try (Connection conn = new Conexion().getConexion();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, idTipoVacuna);

	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                nombreTipo = rs.getString("nombreTipo");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return nombreTipo;
	}
	
	public ArrayList<Fabricante> getLosFabricantes() {
	    ArrayList<Fabricante> fabricantes = new ArrayList<>();
	    String sql = "SELECT idFabricante, nombre FROM Fabricante";

	    try (Connection conn = new Conexion().getConexion();
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            int id = rs.getInt("idFabricante");
	            String nombre = rs.getString("nombre");

	            Fabricante fab = new Fabricante(id, nombre);
	            fabricantes.add(fab);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return fabricantes;
	}
	
	public String getFabricanteByIdFabricante(int idFabricante) {
	    String nombre = null;
	    String sql = "SELECT nombre FROM Fabricante WHERE idFabricante = ?";

	    try (Connection conn = new Conexion().getConexion();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, idFabricante);

	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                nombre = rs.getString("nombre");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return nombre;
	}

	public ArrayList<Enfermedad> getTodasLasEnfermedades() {
	    ArrayList<Enfermedad> enfermedades = new ArrayList<>();
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        Conexion conexion = new Conexion();
	        con = conexion.getConexion();

	        String sql = "SELECT idEnfermedad, nombre, sintomas, idTipoEnfermedad FROM Enfermedad";
	        ps = con.prepareStatement(sql);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            String idEnfermedad = rs.getString("idEnfermedad");
	            String nombre = rs.getString("nombre");
	            String sintomas = rs.getString("sintomas");
	            int idTipo = rs.getInt("idTipoEnfermedad");

	            Enfermedad enfermedad = new Enfermedad(idEnfermedad, nombre, sintomas, idTipo);
	            enfermedades.add(enfermedad);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return enfermedades;
	}


	public void setLasEnfermedades(ArrayList<Enfermedad> lasEnfermedades) {
		this.lasEnfermedades = lasEnfermedades;
	}

	public ArrayList<Cita> getLasCitas() {
	    ArrayList<Cita> citas = new ArrayList<>();
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        Conexion conexion = new Conexion();
	        con = conexion.getConexion();

	        String sql = "SELECT codCita, idPersona, nombre, fecha, hora, motivo FROM Cita"; 
	        ps = con.prepareStatement(sql);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            String codCita = rs.getString("codCita");
	            String idMedico = rs.getString("idPersona");
	            String nombre = rs.getString("nombre");
	            Date fecha = rs.getDate("fecha");
	            Time hora = rs.getTime("hora");
	            String motivo = rs.getString("motivo");

	            Cita cita = new Cita(codCita, idMedico, nombre, fecha, hora, motivo);
	            citas.add(cita);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return citas;
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
	    Connection con = null;
	    PreparedStatement psPersona = null;
	    PreparedStatement psMedico = null;

	    try {
	        Conexion conexion = new Conexion();
	        con = conexion.getConexion();

	        // Insertar en tabla Persona
	        String sqlPersona = "INSERT INTO Persona (idPersona, cedula, nombre, apellido, telefono, direccion, fechaNacimiento, sexo) " +
	                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	        psPersona = con.prepareStatement(sqlPersona);
	        psPersona.setString(1, medico.getIdPersona());
	        psPersona.setString(2, medico.getCedula());
	        psPersona.setString(3, medico.getNombre());
	        psPersona.setString(4, medico.getApellido());
	        psPersona.setString(5, medico.getTelefono());
	        psPersona.setString(6, medico.getDireccion());
	        psPersona.setDate(7, new java.sql.Date(medico.getFechaNacimiento().getTime()));
	        psPersona.setString(8, String.valueOf(medico.getSexo()));

	        psPersona.executeUpdate();

	        // Insertar en tabla Medico
	        String sqlMedico = "INSERT INTO Medico (idPersona, idEspecialidad, exequatur) VALUES (?, ?, ?)";
	        psMedico = con.prepareStatement(sqlMedico);
	        psMedico.setString(1, medico.getIdPersona());
	        psMedico.setInt(2, medico.getEspecialidad());
	        psMedico.setInt(3, medico.getExequatur());

	        psMedico.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (psPersona != null) psPersona.close();
	            if (psMedico != null) psMedico.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	public void insertarEnfermedad(Enfermedad enfermedad) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			Conexion conexion = new Conexion();
			con = conexion.getConexion();

			String sql = "INSERT INTO Enfermedad (idEnfermedad, nombre, sintomas, idTipoEnfermedad) " +
			             "VALUES (?, ?, ?, ?)";

			ps = con.prepareStatement(sql);
			ps.setString(1, enfermedad.getIdEnfermedad());
			ps.setString(2, enfermedad.getNombre());
			ps.setString(3, enfermedad.getSintomas());
			ps.setInt(4, enfermedad.getIdTipoEnfermedad());

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
	
	public String generarNuevoCodigoEnfermedad() {
	    String nuevoCodigo = "E-1"; // Valor por defecto
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        conn = new Conexion().getConexion();
	        String sql = "SELECT MAX(CAST(SUBSTRING(idEnfermedad, 3, LEN(idEnfermedad)) AS INT)) FROM Enfermedad";
	        ps = conn.prepareStatement(sql);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            int ultimoNumero = rs.getInt(1);
	            nuevoCodigo = "E-" + (ultimoNumero + 1);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return nuevoCodigo;
	}

	public String generarNuevoCodigoVacuna() {
	    String nuevoCodigo = "V-1"; // Valor por defecto
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        conn = new Conexion().getConexion();
	        String sql = "SELECT MAX(CAST(SUBSTRING(idVacuna, 3, LEN(idVacuna)) AS INT)) FROM Vacuna";
	        ps = conn.prepareStatement(sql);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            int ultimoNumero = rs.getInt(1);
	            nuevoCodigo = "V-" + (ultimoNumero + 1);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return nuevoCodigo;
	}
	
	public boolean insertarVacuna(Vacuna vacuna) {
	    boolean insertado = false;

	    String sql = "INSERT INTO Vacuna (idVacuna, nombre, codTipoVacuna, idFabricante, fechaVencimiento, cantStock) "
	               + "VALUES (?, ?, ?, ?, ?, ?)";

	    try (Connection conn = new Conexion().getConexion();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, vacuna.getIdVacuna());
	        ps.setString(2, vacuna.getNombre());
	        ps.setInt(3, vacuna.getCodTipoVacuna());
	        ps.setInt(4, vacuna.getIdFabricante());
	        ps.setDate(5, new java.sql.Date(vacuna.getFechaVencimiento().getTime()));
	        ps.setInt(6, vacuna.getCantStock());
	        ps.executeUpdate();


	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return insertado;
	}


	public void insertarCita (Cita cita) {
		Connection con = null;
	    PreparedStatement ps = null;

	    try {
	        Conexion conexion = new Conexion();
	        con = conexion.getConexion();

	        String sql = "INSERT INTO Cita (codCita, idPersona, nombre, fecha, hora, motivo) "
	                   + "VALUES (?, ?, ?, ?, ?, ?)";

	        ps = con.prepareStatement(sql);
	        ps.setString(1, cita.getCodCita());
	        ps.setString(2, cita.getIdPersona());
	        ps.setString(3, cita.getNombre());
	        ps.setDate(4, new java.sql.Date(cita.getFecha().getTime()));
	        ps.setTime(5, new java.sql.Time(cita.getHora().getTime()));
	        ps.setString(6, cita.getMotivo());
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
		Connection con = null;
		PreparedStatement ps = null;

		try {
			Conexion conexion = new Conexion();
			con = conexion.getConexion();

			String sql = "INSERT INTO Consulta (codConsulta, idPersona, fecha, diagnostico, indicacion, "+
					     "esImportante, idPaciente) " +
			             "VALUES (?, ?, ?, ?, ?, ?, ?)";

			ps = con.prepareStatement(sql);
			ps.setString(1, consulta.getCodConsulta());
			ps.setString(2, consulta.getIdMedico());
			ps.setDate(3, new java.sql.Date(consulta.getFecha().getTime()));
			ps.setString(4, consulta.getDiagnostico());
			ps.setString(5, consulta.getIndicacion());
			ps.setBoolean(6, consulta.isEsImportante());
			ps.setString(7, consulta.getIdPaciente());

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

	public void insertarConsultaEnHistorial(Consulta consulta, Paciente paciente) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Conexion conexion = new Conexion();
			con = conexion.getConexion();

			String idHistorial = null;
			String sql = "SELECT idHistorialClinico FROM HistorialClinico WHERE idPersona = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, paciente.getIdPersona());
			rs = ps.executeQuery();

			if (rs.next()) {
				idHistorial = rs.getString("idHistorialClinico");
			}
			rs.close();
			ps.close();

			sql = "INSERT INTO Historial_Consulta (idHistorial, codConsulta) VALUES (?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, idHistorial);
			ps.setString(2, consulta.getCodConsulta());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	public Consulta getConsultaById(String codigo) {
		Consulta consulta = null;
		String sql = "SELECT codConsulta, idPersona, fecha, diagnostico, indicacion, esImportante, idPaciente "
				+ "FROM Consulta WHERE codConsulta = ?";

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
							rs.getBoolean("esImportante"),
							rs.getString("idPaciente")
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
	    String sql = "SELECT idEnfermedad, nombre, sintomas, idTipoEnfermedad "
	    		+ "FROM Enfermedad WHERE idEnfermedad = ?";

	    try (Connection conn = new Conexion().getConexion();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, codigo);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            enfermedad = new Enfermedad(
	                rs.getString("idEnfermedad"),
	                rs.getString("nombre"),
	                rs.getString("sintomas"),
	                rs.getInt("idTipoEnfermedad")
	            );
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
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

	public Medico buscarMedicoByCedula(String cedula) {
	    Medico medico = null;

	    try {
	        Connection conn = new Conexion().getConexion();
	        String sql = "SELECT p.*, m.idEspecialidad, m.exequatur " +
	                     "FROM Persona p " +
	                     "JOIN Medico m ON p.idPersona = m.idPersona " +
	                     "WHERE p.cedula = ? AND p.idPersona LIKE 'M-%'";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, cedula);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            String idPersona = rs.getString("idPersona");
	            String nombre = rs.getString("nombre");
	            String apellido = rs.getString("apellido");
	            String telefono = rs.getString("telefono");
	            String direccion = rs.getString("direccion");
	            Date fechaNacimiento = rs.getDate("fechaNacimiento");
	            char sexo = rs.getString("sexo").charAt(0);
	            int idEspecialidad = rs.getInt("idEspecialidad");
	            int exequatur = rs.getInt("exequatur");

	            medico = new Medico(idPersona, cedula, nombre, apellido, telefono, direccion,
	                                 fechaNacimiento, sexo, idEspecialidad, exequatur);
	        }

	        rs.close();
	        ps.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
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

	public int updateMedico(Medico medico) {
	    int filasAfectadas = 0;

	    String sqlPersona = "UPDATE Persona SET telefono = ?, direccion = ? WHERE idPersona = ?";
	    String sqlMedico = "UPDATE Medico SET idEspecialidad = ?, exequatur = ? WHERE idPersona = ?";

	    try (Connection conn = new Conexion().getConexion()) {
	        conn.setAutoCommit(false); // iniciar transacción

	        try (
	            PreparedStatement psPersona = conn.prepareStatement(sqlPersona);
	            PreparedStatement psMedico = conn.prepareStatement(sqlMedico)
	        ) {
	            // Actualizar Persona
	            psPersona.setString(1, medico.getTelefono());
	            psPersona.setString(2, medico.getDireccion());
	            psPersona.setString(3, medico.getIdPersona());
	            filasAfectadas += psPersona.executeUpdate();

	            // Actualizar Medico
	            psMedico.setInt(1, medico.getEspecialidad());
	            psMedico.setInt(2, medico.getExequatur());
	            psMedico.setString(3, medico.getIdPersona());
	            filasAfectadas += psMedico.executeUpdate();

	            conn.commit(); // confirmar transacción

	        } catch (SQLException ex) {
	            conn.rollback(); // deshacer si algo falla
	            ex.printStackTrace();
	            return 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return filasAfectadas;
	}


	public Cita buscarCitaByIdCita(String idCita) {
	    Cita cita = null;
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        Conexion conexion = new Conexion();
	        con = conexion.getConexion();

	        String sql = "SELECT codCita, idPersona, nombre, fecha, hora, motivo FROM Cita WHERE codCita = ?";
	        ps = con.prepareStatement(sql);
	        ps.setString(1, idCita);

	        rs = ps.executeQuery();

	        if (rs.next()) {
	            String id = rs.getString("codCita");
	            String idPersona = rs.getString("idPersona");
	            String nombre = rs.getString("nombre");
	            Date fecha = rs.getDate("fecha");
	            Time hora = rs.getTime("hora");
	            String motivo = rs.getString("motivo");

	            cita = new Cita(id, idPersona, nombre, fecha, hora, motivo);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
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
	    Connection con = null;
	    PreparedStatement ps = null;

	    try {
	        Conexion conexion = new Conexion();
	        con = conexion.getConexion();

	        String sql = "DELETE FROM Cita WHERE codCita = ?";
	        ps = con.prepareStatement(sql);
	        ps.setString(1, cita.getCodCita());
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


	public void updateCita(Cita cita) {
	    Connection con = null;
	    PreparedStatement ps = null;

	    try {
	        Conexion conexion = new Conexion();
	        con = conexion.getConexion();

	        String sql = "UPDATE Cita SET idPersona = ?, nombre = ?, fecha = ?, hora = ?, motivo = ? WHERE codCita = ?";
	        ps = con.prepareStatement(sql);

	        ps.setString(1, cita.getIdPersona());
	        ps.setString(2, cita.getNombre());
	        ps.setDate(3, new java.sql.Date(cita.getFecha().getTime()));
	        ps.setTime(4, new java.sql.Time(cita.getHora().getTime()));
	        ps.setString(5, cita.getMotivo());
	        ps.setString(6, cita.getCodCita());

	        ps.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
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

	    String sql = "SELECT * FROM Vacuna WHERE idVacuna = ?";

	    try (Connection conn = new Conexion().getConexion();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, codigo);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            vacuna = new Vacuna(rs.getString("idVacuna"), 
	            		rs.getString("nombre"), rs.getInt("codTipoVacuna"),
	            		rs.getInt("idFabricante"), rs.getDate("fechaVencimiento"),
	            		rs.getInt("cantStock"));
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return vacuna;
	}

	public ArrayList<Vacuna> getVacunasGenerales() {
	    ArrayList<Vacuna> vacunas = new ArrayList<>();
	    String sql = "SELECT idVacuna, nombre, codTipoVacuna, idFabricante, fechaVencimiento, cantStock FROM Vacuna";

	    try (Connection conn = new Conexion().getConexion();
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            String idVacuna = rs.getString("idVacuna");
	            String nombre = rs.getString("nombre");
	            int codTipoVacuna = rs.getInt("codTipoVacuna");
	            int idFabricante = rs.getInt("idFabricante");
	            Date fechaVencimiento = rs.getDate("fechaVencimiento");
	            int cantStock = rs.getInt("cantStock");

	            Vacuna vacuna = new Vacuna(idVacuna, nombre, codTipoVacuna, idFabricante, fechaVencimiento, cantStock);
	            vacunas.add(vacuna);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return vacunas;
	}

	public boolean updateVacuna(Vacuna vacuna) {
	    boolean actualizada = false;

	    String sql = "UPDATE Vacuna "
	               + "SET nombre = ?, codTipoVacuna = ?, idFabricante = ?, fechaVencimiento = ?, cantStock = ? "
	               + "WHERE idVacuna = ?";

	    try (Connection conn = new Conexion().getConexion();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, vacuna.getNombre());
	        ps.setInt(2, vacuna.getCodTipoVacuna());
	        ps.setInt(3, vacuna.getIdFabricante());

	        java.util.Date fecha = vacuna.getFechaVencimiento();
	        ps.setDate(4, new java.sql.Date(fecha.getTime()));

	        ps.setInt(5, vacuna.getCantStock());
	        ps.setString(6, vacuna.getIdVacuna());

	        int filasAfectadas = ps.executeUpdate();
	        actualizada = (filasAfectadas > 0);

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return actualizada;
	}


	public boolean eliminarVacuna(Vacuna selected) {
	    boolean eliminada = false;

	    String sql = "DELETE FROM Vacuna WHERE idVacuna = ?";

	    try (Connection conn = new Conexion().getConexion();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, selected.getIdVacuna());
	        int filasAfectadas = ps.executeUpdate();
	        eliminada = (filasAfectadas > 0);

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return eliminada;
	}


	public int getCantPacientesPoseenEnfermedad(Enfermedad enfermedad) {
	    int cantidad = 0;

	    try  {
	        Conexion miConexion = new Conexion();  // Usa tu clase personalizada
	        Connection conn = miConexion.getConexion();

	        String query = "SELECT COUNT(DISTINCT hc.idPersona) AS cantidad " +
	                       "FROM HistorialClinico hc " +
	                       "INNER JOIN Historial_Enfermedad he ON hc.idHistorialClinico = he.idHistorialClinico " +
	                       "WHERE he.idEnfermedad = ?";

	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setString(1, enfermedad.getIdEnfermedad());

	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            cantidad = rs.getInt("cantidad");

	        }

	        rs.close();
	        ps.close();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return cantidad;
	}
	
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
				setLoginUsuario(usuarioLogin);
				login = true;
			}

			rs.close();
			ps.close();
			miConexion.cerrarConexion();  // Cierra la conexi�n al final

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
			conn.setAutoCommit(false); //Para hacer una transacci�n

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
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        Conexion conexion = new Conexion();
	        con = conexion.getConexion();

	        String sql = "SELECT idEnfermedad, nombre, sintomas, idTipoEnfermedad FROM Enfermedad WHERE idEnfermedad = ?";
	        ps = con.prepareStatement(sql);
	        ps.setString(1, codigo);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            String id = rs.getString("idEnfermedad");
	            String nombre = rs.getString("nombre");
	            String sintomas = rs.getString("sintomas");
	            int idTipo = rs.getInt("idTipoEnfermedad");

	            enfermedad = new Enfermedad(id, nombre, sintomas, idTipo);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return enfermedad;
	}
	
	public ArrayList<Paciente> getPacientesPorEnfermedad(String idEnfermedad) {
	    ArrayList<Paciente> pacientes = new ArrayList<>();

	    String query = "SELECT DISTINCT per.idPersona, per.cedula, per.nombre, per.apellido, per.telefono, " +
	                   "per.direccion, per.fechaNacimiento, per.sexo, pac.estatura, pac.peso " +
	                   "FROM HistorialClinico hc " +
	                   "JOIN Historial_Enfermedad he ON hc.idHistorialClinico = he.idHistorialClinico " +
	                   "JOIN Paciente pac ON hc.idPersona = pac.idPersona " +
	                   "JOIN Persona per ON pac.idPersona = per.idPersona " +
	                   "WHERE he.idEnfermedad = ?";

	    try (Connection conn = new Conexion().getConexion();
	         PreparedStatement ps = conn.prepareStatement(query)) {

	        ps.setString(1, idEnfermedad);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            String idPersona = rs.getString("idPersona");
	            String cedula = rs.getString("cedula");
	            String nombre = rs.getString("nombre");
	            String apellido = rs.getString("apellido");
	            String telefono = rs.getString("telefono");
	            String direccion = rs.getString("direccion");
	            Date fechaNacimiento = rs.getDate("fechaNacimiento");
	            char sexo = rs.getString("sexo").charAt(0);
	            float estatura = rs.getFloat("estatura");
	            float peso = rs.getFloat("peso");

	            Paciente paciente = new Paciente(idPersona, cedula, nombre, apellido, telefono,
	                                             direccion, fechaNacimiento, sexo, estatura, peso);
	            pacientes.add(paciente);
	        }

	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return pacientes;
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
				System.out.println("No se encontr� el usuario para actualizar.");
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
				+ "p.fechaNacimiento, p.sexo " +
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

	public static String generarNuevoCodigoMedico() {
	    String nuevoCodigo = "M-1"; // Valor por defecto si no hay m�dicos

	    try {
	        Connection conn = new Conexion().getConexion();
	        String sql = "SELECT MAX(CAST(SUBSTRING(idPersona, 3, LEN(idPersona)) AS INT)) FROM Persona WHERE idPersona LIKE 'M-%'";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            int ultimoNumero = rs.getInt(1);
	            nuevoCodigo = "M-" + (ultimoNumero + 1);
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
	
	public static boolean registrarVacunacion(Paciente paciente, Vacuna vacuna, Date fechaAplicacion) {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        conn = new Conexion().getConexion();

	        // 1. Obtener el idHistorialClinico del paciente
	        String sqlHistorial = "SELECT idHistorialClinico FROM HistorialClinico WHERE idPersona = ?";
	        ps = conn.prepareStatement(sqlHistorial);
	        ps.setString(1, paciente.getIdPersona());
	        rs = ps.executeQuery();

	        int idHistorialClinico = -1;
	        if (rs.next()) {
	            idHistorialClinico = rs.getInt("idHistorialClinico");
	        } else {
	            // Crear historial si no existe
	            rs.close();
	            ps.close();
	            String insertHistorial = "INSERT INTO HistorialClinico (idPersona) OUTPUT INSERTED.idHistorialClinico VALUES (?)";
	            ps = conn.prepareStatement(insertHistorial);
	            ps.setString(1, paciente.getIdPersona());
	            rs = ps.executeQuery();
	            if (rs.next()) {
	                idHistorialClinico = rs.getInt(1);
	            }
	        }

	        rs.close();
	        ps.close();

	        // 2. Insertar en Historial_Vacunacion
	        String insertVac = "INSERT INTO Historial_Vacunacion (idHistorialClinico, idVacuna, fecha) VALUES (?, ?, ?)";
	        ps = conn.prepareStatement(insertVac);
	        ps.setInt(1, idHistorialClinico);
	        ps.setString(2, vacuna.getIdVacuna());
	        ps.setDate(3, new java.sql.Date(fechaAplicacion.getTime()));
	        ps.executeUpdate();
	        ps.close();

	        // 3. Actualizar stock de la vacuna
	        String updateStock = "UPDATE Vacuna SET cantStock = cantStock - 1 WHERE idVacuna = ?";
	        ps = conn.prepareStatement(updateStock);
	        ps.setString(1, vacuna.getIdVacuna());
	        ps.executeUpdate();

	        return true;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
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

			String sql = "SELECT c.codConsulta, c.idPersona, c.fecha, c.diagnostico, c.indicacion, c.esImportante, c.idPaciente " + 
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
						rs.getBoolean("esImportante"),
						rs.getString("idPaciente")
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
				return 1; // actualizaci�n exitosa
			} else {
				return 0; // no se encontr� o no se actualiz� nada
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return 0; 
		}
	}
	
	public ArrayList<TipoEnfermedad> getTiposEnfermedades() {
		ArrayList<TipoEnfermedad> tipos = new ArrayList<>();
		String sql = "SELECT idTipoEnfermedad, nombreTipo FROM TipoEnfermedad";

		try (Connection conn = new Conexion().getConexion();
			 PreparedStatement ps = conn.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				int id = rs.getInt("idTipoEnfermedad");
				String nombre = rs.getString("nombreTipo");

				TipoEnfermedad tipo = new TipoEnfermedad(id, nombre);
				tipos.add(tipo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tipos;
	}
	
	public ArrayList<Especialidad> getLasEspecialidades() {
		ArrayList<Especialidad> especialidades = new ArrayList<>();
		String sql = "SELECT idEspecialidad, descripcion FROM Especialidad";

		try (Connection conn = new Conexion().getConexion();
			 PreparedStatement ps = conn.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				int id = rs.getInt("idEspecialidad");
				String descripcion = rs.getString("descripcion");

				Especialidad esp = new Especialidad(id, descripcion);
				especialidades.add(esp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return especialidades;
	}

	public String getTipoEnfermedadByIdEnfermedad(int idTipoEnfermedad) {
		String tipoNombre = null;
		String sql = "SELECT nombreTipo FROM TipoEnfermedad WHERE idTipoEnfermedad = ?";

		try (Connection conn = new Conexion().getConexion();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, idTipoEnfermedad);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					tipoNombre = rs.getString("nombreTipo");
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
	
	public void insertarEnfermedadDePacientePorIdPaciente(String idPaciente, String idEnfermedad) {
	    Connection con = null;
	    PreparedStatement psSelect = null;
	    PreparedStatement psInsert = null;
	    ResultSet rs = null;

	    try {
	        Conexion conexion = new Conexion();
	        con = conexion.getConexion();

	        // Paso 1: Buscar idHistorial
	        String sqlSelect = "SELECT idHistorialClinico FROM HistorialClinico WHERE idPersona = ?";
	        psSelect = con.prepareStatement(sqlSelect);
	        psSelect.setString(1, idPaciente);
	        rs = psSelect.executeQuery();

	        if (rs.next()) {
	            String idHistorial = rs.getString("idHistorialClinico");

	            // Paso 2: Insertar en Historial_Enfermedad
	            String sqlInsert = "INSERT INTO Historial_Enfermedad (idHistorialClinico, idEnfermedad) VALUES (?, ?)";
	            psInsert = con.prepareStatement(sqlInsert);
	            psInsert.setString(1, idHistorial);
	            psInsert.setString(2, idEnfermedad);

	            psInsert.executeUpdate();
	        } else {
	            System.out.println("No se encontr� historial cl�nico para el paciente con id: " + idPaciente);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (psSelect != null) psSelect.close();
	            if (psInsert != null) psInsert.close();
	            if (con != null) con.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	}

	public String generarNuevoCodigoConsulta() {
		String nuevoCodigo = "C-1"; // Valor por defecto
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        conn = new Conexion().getConexion();
	        String sql = "SELECT MAX(CAST(SUBSTRING(codConsulta, 3, LEN(codConsulta)) AS INT)) FROM Consulta";
	        ps = conn.prepareStatement(sql);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            int ultimoNumero = rs.getInt(1);
	            nuevoCodigo = "C-" + (ultimoNumero + 1);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return nuevoCodigo;
	}
	
	public String generarNuevoCodigoCita() {
		String nuevoCodigo = "CT-1"; // Valor por defecto
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        conn = new Conexion().getConexion();
	        String sql = "SELECT MAX(CAST(SUBSTRING(codCita, 4, LEN(codCita)) AS INT)) FROM Cita";
	        ps = conn.prepareStatement(sql);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            int ultimoNumero = rs.getInt(1); // si no hay resultados, ser� 0
	            nuevoCodigo = "CT-" + (ultimoNumero + 1);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return nuevoCodigo;
	}
	
	public boolean existeCita(Date fecha, Time hora, Medico medico, String codCita) {
	    boolean existe = false;
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        Conexion conexion = new Conexion();
	        con = conexion.getConexion();

	        String sql = "SELECT COUNT(*) FROM Cita WHERE fecha = ? AND hora = ? AND idPersona = ? AND codCita != ?";
	        ps = con.prepareStatement(sql);
	        java.sql.Date sqlFecha = new java.sql.Date(fecha.getTime());
	        java.sql.Time sqlHora = hora; // Ya es java.sql.Time
	        
	        ps.setDate(1, sqlFecha);
	        ps.setString(2, sqlHora.toString());
	        ps.setString(3, medico.getIdPersona());
	        ps.setString(4, codCita);

	        rs = ps.executeQuery();
	        if (rs.next()) {
	            int count = rs.getInt(1);
	            existe = count > 0;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }

	    return existe;
	}


}