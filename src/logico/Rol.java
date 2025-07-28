package logico;

public class Rol {
	private int idRol;
    private String descripcion;

    public Rol(int idRol, String descripcion) {
        this.idRol = idRol;
        this.descripcion = descripcion;
    }

    public int getIdRol() { return idRol; }
    public String getDescripcion() { return descripcion; }

    @Override
    public String toString() {
        return descripcion; // lo que verá el combo
    }
}
