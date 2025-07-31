package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class HistoriaClinica implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idHistorialClinico;
	private String idPersona;
	private ArrayList<Consulta> misConsultas;
	private ArrayList<VacunaAplicada>misVacunas;
	private ArrayList<Enfermedad>misEnfermedades;
	
	public HistoriaClinica() {
		super();
		misConsultas = new ArrayList<>();
		misVacunas = new ArrayList<>();
		misEnfermedades = new ArrayList<>();
		
	}
	
	public int getIdHistorialClinico() {
		return idHistorialClinico;
	}

	public void setIdHistorialClinico(int idHistorialClinico) {
		this.idHistorialClinico = idHistorialClinico;
	}

	public String getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}

	public ArrayList<Consulta> getMisConsultas() {
		return misConsultas;
	}

	public void setMisConsultas(ArrayList<Consulta> misConsultas) {
		this.misConsultas = misConsultas;
	}

	public ArrayList<VacunaAplicada> getMisVacunas() {
		return misVacunas;
	}

	public void setMisVacunas(ArrayList<VacunaAplicada> misVacunas) {
		this.misVacunas = misVacunas;
	}

	public ArrayList<Enfermedad> getMisEnfermedades() {
		return misEnfermedades;
	}

	public void setMisEnfermedades(ArrayList<Enfermedad> misEnfermedades) {
		this.misEnfermedades = misEnfermedades;
	}

	public HistoriaClinica(int idHistorialClinico, String idPersona) {
        this.idHistorialClinico = idHistorialClinico;
        this.idPersona = idPersona;
        misConsultas = new ArrayList<>();
        misVacunas = new ArrayList<>();
        misEnfermedades = new ArrayList<>();
    }
	
	public ArrayList<Consulta> getLasConsultas() {
		return misConsultas;
	}
	public void setLasConsultas(ArrayList<Consulta> lasConsultas) {
		this.misConsultas = lasConsultas;
	}
}
