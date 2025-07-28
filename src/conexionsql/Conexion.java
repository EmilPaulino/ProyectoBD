package conexionsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private final String url = "jdbc:sqlserver://localhost:1433;"
                             + "database=Clinica_Medica;"
                             + "user=sa;"
                             + "password=27092005;"
                             + "trustServerCertificate=true;";
    
    private Connection conexion;

    public Conexion() {
        try {
            conexion = DriverManager.getConnection(url);
            System.out.println("Conexi�n exitosa.");
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos.");
            e.printStackTrace();
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    public void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexi�n cerrada.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
