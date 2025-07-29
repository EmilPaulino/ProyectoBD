package visual.consulta;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.ClinicaMedica;
import logico.Consulta;
import logico.Enfermedad;
import logico.Medico;
import logico.Paciente;
import visual.enfermedad.SeleccionarEnfermedad;
import visual.paciente.DetallePaciente;
import visual.paciente.SeleccionarPaciente;

public class RegistroConsulta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodConsulta;
	private JTextField txtMedico;
	private JTextField txtPaciente;
	private JTextField txtDiagnostico;
	private JTextArea txtAIndicacion;
	private JCheckBox chckbxImportante;
	private Medico medico = null;
	private Paciente paciente = null;
	private Consulta updated; 
	private JSpinner spnFecha;
	private JButton btnAmpliarDatos;
	private JButton btnRegistrar;
	private JButton btnSeleccionarPaciente;
	private JTextField txtEnfermedad;
	private Enfermedad enfermedad;
	private JButton btnSeleccionarEnfermedad;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistroConsulta dialog = new RegistroConsulta(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistroConsulta(Consulta aux) {
		
		if(ClinicaMedica.getLoginUsuario().getRol().equals("Médico")) {
			medico = ClinicaMedica.getLoginUsuario().getMedicoRelacionado();
		}
		
		updated = aux;
		if(updated == null) {
			setTitle("Registro de consulta");
		}
		else {
			setTitle("Detalle de consulta");
		}
		setBounds(100, 100, 554, 470);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "M\u00E9dico:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_1.setBounds(10, 68, 183, 81);
			panel.add(panel_1);
			panel_1.setLayout(null);
			{
				txtMedico = new JTextField();
				txtMedico.setBounds(10, 36, 163, 20);
				panel_1.add(txtMedico);
				txtMedico.setEditable(false);
				txtMedico.setColumns(10);
				if(ClinicaMedica.getLoginUsuario().getRol().equals("Médico")) {
					txtMedico.setText(medico.getNombre()+" "+medico.getApellido());
				}

			}
			
			JPanel panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_2.setBounds(10, 11, 508, 46);
			panel.add(panel_2);
			panel_2.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("C\u00F3digo:");
				lblNewLabel.setBounds(10, 17, 46, 14);
				panel_2.add(lblNewLabel);
				lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			}
			{
				txtCodConsulta = new JTextField();
				txtCodConsulta.setText("Cons-"+ClinicaMedica.getInstance().codConsulta);
				txtCodConsulta.setBounds(66, 14, 182, 20);
				panel_2.add(txtCodConsulta);
				txtCodConsulta.setEditable(false);
				txtCodConsulta.setColumns(10);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Fecha:");
				lblNewLabel_1.setBounds(258, 17, 46, 14);
				panel_2.add(lblNewLabel_1);
				lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			}
			{
				spnFecha = new JSpinner();
				spnFecha.setBounds(314, 14, 182, 20);
				panel_2.add(spnFecha);
				Date fechaActual = new Date();
				spnFecha.setModel(new SpinnerDateModel(fechaActual, null, null, Calendar.DAY_OF_YEAR));
				JSpinner.DateEditor de_spnFecha = new JSpinner.DateEditor(spnFecha, "dd/MM/yyyy");
				spnFecha.setEditor(de_spnFecha);
				spnFecha.setEnabled(false);

			}
			{
				JPanel panel_3 = new JPanel();
				panel_3.setBorder(new TitledBorder(null, "Paciente:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_3.setBounds(213, 68, 305, 81);
				panel.add(panel_3);
				panel_3.setLayout(null);
				{
					txtPaciente = new JTextField();
					txtPaciente.setBounds(10, 48, 151, 20);
					panel_3.add(txtPaciente);
					txtPaciente.setEditable(false);
					txtPaciente.setColumns(10);
				}
				
				btnAmpliarDatos = new JButton("Ampliar datos");
				btnAmpliarDatos.setEnabled(false);
				btnAmpliarDatos.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						DetallePaciente dp = new DetallePaciente(paciente);
						dp.setModal(true);
						dp.setVisible(true);
					}
				});
				btnAmpliarDatos.setBounds(171, 47, 124, 23);
				panel_3.add(btnAmpliarDatos);
				
				btnSeleccionarPaciente = new JButton("Seleccionar");
				btnSeleccionarPaciente.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						SeleccionarPaciente sp = new SeleccionarPaciente();
						sp.setModal(true);
						sp.setVisible(true);
						paciente = sp.getSelectedPaciente();
						if(paciente != null) {
							txtPaciente.setText(paciente.getNombre()+" "+paciente.getApellido());
							btnAmpliarDatos.setEnabled(true);
						}
					}
				});
				btnSeleccionarPaciente.setBounds(10, 23, 285, 20);
				panel_3.add(btnSeleccionarPaciente);
			}
			
			JPanel panel_3 = new JPanel();
			panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos generales de la consulta:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_3.setBounds(10, 160, 508, 212);
			panel.add(panel_3);
			panel_3.setLayout(null);
			
			JLabel lblNewLabel_3 = new JLabel("Diagn\u00F3stico:");
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_3.setBounds(10, 33, 70, 14);
			panel_3.add(lblNewLabel_3);
			
			txtDiagnostico = new JTextField();
			txtDiagnostico.setBounds(90, 30, 408, 20);
			panel_3.add(txtDiagnostico);
			txtDiagnostico.setColumns(10);
			
			JLabel lblNewLabel_4 = new JLabel("Indicaci\u00F3n:");
			lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_4.setBounds(10, 96, 70, 14);
			panel_3.add(lblNewLabel_4);
			
			JPanel panel_4 = new JPanel();
			panel_4.setBounds(90, 92, 408, 82);
			panel_3.add(panel_4);
			panel_4.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			panel_4.add(scrollPane, BorderLayout.CENTER);
			
			txtAIndicacion = new JTextArea();
			scrollPane.setViewportView(txtAIndicacion); 
			
			chckbxImportante = new JCheckBox("\u00BFEs relevante?");
			chckbxImportante.setBounds(90, 181, 137, 23);
			panel_3.add(chckbxImportante);
			
			txtEnfermedad = new JTextField();
			txtEnfermedad.setEditable(false);
			txtEnfermedad.setBounds(90, 61, 264, 20);
			panel_3.add(txtEnfermedad);
			txtEnfermedad.setColumns(10);
			
			btnSeleccionarEnfermedad = new JButton("Seleccionar");
			btnSeleccionarEnfermedad.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					SeleccionarEnfermedad se = new SeleccionarEnfermedad();
					se.setModal(true);
					se.setVisible(true);
					enfermedad = se.getSelectedEnfermedad();
					if(enfermedad != null) {
						txtEnfermedad.setText(enfermedad.getNombre());
						chckbxImportante.setSelected(true);
					}
					
				}
			});
			btnSeleccionarEnfermedad.setBounds(364, 58, 134, 23);
			panel_3.add(btnSeleccionarEnfermedad);
			
			JLabel lblNewLabel_2 = new JLabel("Enfermedad:");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_2.setBounds(0, 64, 80, 14);
			panel_3.add(lblNewLabel_2);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegistrar = new JButton("Registrar");
				
				btnRegistrar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(camposVacios()) {
				            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Información", JOptionPane.ERROR_MESSAGE);
						}else {
							Consulta consulta = new Consulta(txtCodConsulta.getText(), medico, paciente, txtDiagnostico.getText(), txtAIndicacion.getText(), chckbxImportante.isSelected());
							ClinicaMedica.getInstance().insertarConsulta(consulta);
							if(chckbxImportante.isSelected()) {
								paciente.getMiHistorial().getLasConsultas().add(consulta);
							}
							if(enfermedad!=null) {
								paciente.getMisEnfermedades().add(enfermedad);
							}
							JOptionPane.showMessageDialog(null, "Operacion Satisfactoria", "Informacion", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}
					}
				});
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		loadConsulta();
	}
	private void loadConsulta(){
		if(updated != null) {
			txtCodConsulta.setText(updated.getIdConsulta());
			spnFecha.setValue(updated.getFecha());
			txtMedico.setText(updated.getMedico().getNombre()+" "+updated.getMedico().getApellido());
			txtPaciente.setText(updated.getPaciente().getNombre()+" "+updated.getPaciente().getApellido());
			btnAmpliarDatos.setEnabled(false);
			btnSeleccionarPaciente.setEnabled(false);
			txtDiagnostico.setText(updated.getDiagnostico());
			txtDiagnostico.setEditable(false);
			txtAIndicacion.setText(updated.getIndicacion());
			txtAIndicacion.setEditable(false);
			txtAIndicacion.setEnabled(false);
			if(updated.isImportante()) {
				chckbxImportante.setSelected(true);
			}
			else {
				chckbxImportante.setSelected(false);
			}
			chckbxImportante.setEnabled(false);
			btnRegistrar.setEnabled(false);
			txtEnfermedad.setText(updated.getMedico().getNombre()+" "+updated.getMedico().getApellido());
			btnSeleccionarEnfermedad.setEnabled(false);
		}
	}
	
	private boolean camposVacios() {
		boolean estanVacios = false;
		if(medico == null || paciente == null || txtDiagnostico.getText().isEmpty() || txtAIndicacion.getText().isEmpty()) {
			estanVacios = true;
		}
		return estanVacios;
	}
}
