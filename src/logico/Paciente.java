package logico;

import java.util.ArrayList;
import java.util.Date;

public class Paciente extends Persona {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected HistoriaClinica miHistorial;
	protected float estatura;
	protected float peso;
	protected ArrayList<Vacuna>misVacunas;
	protected ArrayList<Enfermedad>misEnfermedades;
	
	public Paciente(String idPersona, String cedula, String nombre, String apellido, String telefono, String direccion,
			Date fechaNacimiento, int edad, String sexo, float estatura, float peso) {
		super(idPersona, cedula, nombre, apellido, telefono, direccion, fechaNacimiento, edad, sexo);
		this.miHistorial = new HistoriaClinica();
		this.estatura = estatura;
		this.peso = peso;
		misVacunas = new ArrayList<>();
		misEnfermedades = new ArrayList<>();
		
	}

	public ArrayList<Enfermedad> getMisEnfermedades() {
		return misEnfermedades;
	}

	public void setMisEnfermedades(ArrayList<Enfermedad> misEnfermedades) {
		this.misEnfermedades = misEnfermedades;
	}

	public ArrayList<Vacuna> getMisVacunas() {
		return misVacunas;
	}

	public void setMisVacunas(ArrayList<Vacuna> misVacunas) {
		this.misVacunas = misVacunas;
	}

	public HistoriaClinica getMiHistorial() {
		return miHistorial;
	}

	public void setMiHistorial(HistoriaClinica miHistorial) {
		this.miHistorial = miHistorial;
	}

	public float getEstatura() {
		return estatura;
	}

	public void setEstatura(float estatura) {
		this.estatura = estatura;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	
}
