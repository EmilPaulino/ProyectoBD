package logico;

public class EnfermedadPaciente {
	private Enfermedad enfermedad;
    private boolean estaCurado;

    public EnfermedadPaciente(Enfermedad enfermedad, boolean estaCurado) {
        this.enfermedad = enfermedad;
        this.estaCurado = estaCurado;
    }

    public Enfermedad getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(Enfermedad enfermedad) {
        this.enfermedad = enfermedad;
    }

    public boolean estaCurado() {
        return estaCurado;
    }

    public void estaCurado(boolean estaCurado) {
        this.estaCurado = estaCurado;
    }
}
