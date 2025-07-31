package logico;

import java.util.Date;

public class VacunaAplicada {
	private Vacuna vacuna;
    private Date fechaAplicacion;

    public VacunaAplicada(Vacuna vacuna, Date fechaAplicacion) {
        this.vacuna = vacuna;
        this.fechaAplicacion = fechaAplicacion;
    }

    public Vacuna getVacuna() {
        return vacuna;
    }

    public Date getFechaAplicacion() {
        return fechaAplicacion;
    }
}
