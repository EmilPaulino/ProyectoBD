package visual.medico;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import logico.ClinicaMedica;
import logico.Especialidad;
import logico.Medico;
import logico.TipoEnfermedad;

public class RegistroMedico extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JSpinner spnExequatur;
	private JComboBox cbxEspecialidad;
	private JComboBox cbxSexo;
	private JSpinner spnFechaNacim;
	private Medico selected;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistroMedico dialog = new RegistroMedico(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistroMedico(Medico medico) {
		
		selected = medico;
		if(selected == null) {
			setTitle("Registro de m\u00E9dico");
		} else {
			setTitle("Modificar m\u00E9dico");
		}
		
		
		setBounds(100, 100, 572, 278);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel label = new JLabel("C\u00F3digo:");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setBounds(18, 14, 56, 14);
			panel.add(label);
			
			txtCodigo = new JTextField();
			txtCodigo.setEditable(false);
			txtCodigo.setColumns(10);
			txtCodigo.setBounds(84, 11, 131, 20);
			panel.add(txtCodigo);
			txtCodigo.setText(ClinicaMedica.getInstance().generarNuevoCodigoMedico());
			
			JLabel label_1 = new JLabel("C\u00E9dula:");
			label_1.setHorizontalAlignment(SwingConstants.RIGHT);
			label_1.setBounds(224, 14, 56, 14);
			panel.add(label_1);
			
			//txtCedula = new JTextField();
			try {
				txtCedula = new JFormattedTextField(new javax.swing.text.MaskFormatter("###-#######-#"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			txtCedula.setColumns(10);
			txtCedula.setBounds(289, 11, 243, 20);
			panel.add(txtCedula);
			
			JLabel label_2 = new JLabel("Nombre:");
			label_2.setHorizontalAlignment(SwingConstants.RIGHT);
			label_2.setBounds(18, 51, 56, 14);
			panel.add(label_2);
			
			txtNombre = new JTextField();
			txtNombre.setColumns(10);
			txtNombre.setBounds(92, 49, 175, 20);
			panel.add(txtNombre);
			
			JLabel label_3 = new JLabel("Apellido:");
			label_3.setHorizontalAlignment(SwingConstants.RIGHT);
			label_3.setBounds(284, 52, 56, 14);
			panel.add(label_3);
			
			txtApellido = new JTextField();
			txtApellido.setColumns(10);
			txtApellido.setBounds(357, 49, 175, 20);
			panel.add(txtApellido);
			
			JLabel label_4 = new JLabel("Tel\u00E9fono:");
			label_4.setHorizontalAlignment(SwingConstants.RIGHT);
			label_4.setBounds(17, 88, 57, 14);
			panel.add(label_4);
			
			try {
				txtTelefono = new JFormattedTextField(new javax.swing.text.MaskFormatter("###-###-####"));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        txtTelefono.setColumns(10); 
			txtTelefono.setColumns(10);
			txtTelefono.setBounds(84, 86, 144, 20);
			panel.add(txtTelefono);
			
			JLabel label_5 = new JLabel("Direcci\u00F3n:");
			label_5.setHorizontalAlignment(SwingConstants.RIGHT);
			label_5.setBounds(237, 89, 65, 14);
			panel.add(label_5);
			
			txtDireccion = new JTextField();
			txtDireccion.setColumns(10);
			txtDireccion.setBounds(311, 86, 221, 20);
			panel.add(txtDireccion);
			
			JLabel label_6 = new JLabel("Fecha Nacimiento:");
			label_6.setHorizontalAlignment(SwingConstants.RIGHT);
			label_6.setBounds(9, 125, 109, 14);
			panel.add(label_6);
			
			spnFechaNacim = new JSpinner();
			spnFechaNacim.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.MILLISECOND));
			spnFechaNacim.setEditor(new JSpinner.DateEditor(spnFechaNacim, "dd/MM/yyyy"));
			spnFechaNacim.setBounds(128, 123, 129, 20);
			panel.add(spnFechaNacim);
			
			JLabel label_8 = new JLabel("Sexo:");
			label_8.setHorizontalAlignment(SwingConstants.RIGHT);
			label_8.setBounds(377, 126, 36, 14);
			panel.add(label_8);
			
			cbxSexo = new JComboBox();
			cbxSexo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Masculino", "Femenino"}));
			cbxSexo.setBounds(423, 123, 108, 20);
			panel.add(cbxSexo);
			
			JLabel lblNewLabel = new JLabel("Especialidad:");
			lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel.setBounds(10, 162, 87, 14);
			panel.add(lblNewLabel);
			
			cbxEspecialidad = new JComboBox();
			cbxEspecialidad.setBounds(107, 159, 164, 20);
			panel.add(cbxEspecialidad);
			
			cargarEspecialidades();
			
			JLabel lblNewLabel_1 = new JLabel("Exequatur:");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_1.setBounds(281, 162, 65, 14);
			panel.add(lblNewLabel_1);
			
			spnExequatur = new JSpinner();
			spnExequatur.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			spnExequatur.setBounds(357, 159, 175, 20);
			panel.add(spnExequatur);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				if(selected != null) {
					btnRegistrar.setText("Modificar");
				}
				btnRegistrar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
				       
				        if (camposVacios()) {
				            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos obligatorios.", 
				                                          "Campos vacíos", JOptionPane.WARNING_MESSAGE);
				            return; 
				        }
				        
						if(selected == null) {
							String codigo = txtCodigo.getText();
							String cedula = txtCedula.getText();
							
					        if (ClinicaMedica.getInstance().cedulaMedicoExiste(cedula)) {
					            JOptionPane.showMessageDialog(null, "La cédula ya está registrada.", 
					                                          "Error", JOptionPane.ERROR_MESSAGE);
					            return;
					        }
							
							String nombre = txtNombre.getText();
							String apellido = txtApellido.getText();
							String telefono = txtTelefono.getText();
							String direccion = txtDireccion.getText();
							String sexoStr = (String) cbxSexo.getSelectedItem();
							char sexo;

							if ("Masculino".equals(sexoStr)) {
							    sexo = 'M';
							} else if ("Femenino".equals(sexoStr)) {
							    sexo = 'F';
							} else {
							    sexo = ' ';
							}
							Especialidad especialidadSeleccionada = (Especialidad) cbxEspecialidad.getSelectedItem();
							int idEspecialidad = especialidadSeleccionada.getIdEspecialidad();
							int exequatur = new Integer(spnExequatur.getValue().toString());
						    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						    Date fechaNacimiento = (Date)(spnFechaNacim.getValue());
							
							Medico medico = new Medico(codigo,cedula,nombre,apellido,telefono,direccion,fechaNacimiento,sexo,idEspecialidad,exequatur);
							ClinicaMedica.getInstance().insertarMedico(medico);
							JOptionPane.showMessageDialog(null,"Operacion exitosa","Informacion",JOptionPane.INFORMATION_MESSAGE);
							clean();
						} else {
							selected.setCedula(txtCedula.getText());
							selected.setNombre(txtNombre.getText());
							selected.setApellido(txtApellido.getText());
							selected.setTelefono(txtTelefono.getText());
							selected.setDireccion(txtDireccion.getText());
							String sexoStr = (String) cbxSexo.getSelectedItem();
							char sexo;

							if ("Masculino".equals(sexoStr)) {
							    sexo = 'M';
							} else if ("Femenino".equals(sexoStr)) {
							    sexo = 'F';
							} else {
							    sexo = ' ';
							}
							Especialidad especialidadSeleccionada = (Especialidad) cbxEspecialidad.getSelectedItem();
							selected.setEspecialidad(especialidadSeleccionada.getIdEspecialidad());
							selected.setExequatur(Integer.parseInt(spnExequatur.getValue().toString()));
							selected.setFechaNacimiento((Date) spnFechaNacim.getValue());
							ClinicaMedica.getInstance().updateMedico(selected);
							ListadoMedicos.loadMedicos();
							JOptionPane.showMessageDialog(null,"Operacion exitosa","Informacion",JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}
					}

					private void clean() {
						txtCodigo.setText(ClinicaMedica.getInstance().generarNuevoCodigoMedico());
						txtCedula.setText("");
						txtNombre.setText("");
						txtApellido.setText("");
						txtTelefono.setText("");
						txtDireccion.setText("");
						cbxSexo.setSelectedIndex(0);
						cbxEspecialidad.setSelectedIndex(0);
						spnFechaNacim.setValue(new Date());
						spnExequatur.setValue(0);
						
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
		loadMedico();
		
	}

	private void loadMedico() {
		if(selected!=null) {
			txtCodigo.setText(selected.getIdPersona());
			txtCedula.setText(selected.getCedula());
			txtNombre.setText(selected.getNombre());
			txtApellido.setText(selected.getApellido());
			txtTelefono.setText(selected.getTelefono());
			txtDireccion.setText(selected.getDireccion());
			cbxSexo.setSelectedItem(selected.getSexo());
			spnExequatur.setValue(selected.getExequatur());
            spnFechaNacim.setValue(selected.getFechaNacimiento());
            
            char sexo = selected.getSexo();
	        String sexoTexto;
	        if (sexo == 'M') {
	            sexoTexto = "Masculino";
	        } else if (sexo == 'F') {
	            sexoTexto = "Femenino";
	        } else {
	            sexoTexto = "<Seleccione>";
	        }
	        cbxSexo.setSelectedItem(sexoTexto);
	        
	        int idEspecialidadDelMedico = selected.getEspecialidad();

	        for (int i = 0; i < cbxEspecialidad.getItemCount(); i++) {
	            Especialidad esp = (Especialidad) cbxEspecialidad.getItemAt(i);
	            if (esp.getIdEspecialidad() == idEspecialidadDelMedico) {
	                cbxEspecialidad.setSelectedIndex(i);
	                break;
	            }
	        }
	        
	        txtCedula.setEditable(false);
	        txtNombre.setEditable(false);
	        txtApellido.setEditable(false);
	        spnFechaNacim.setEnabled(false);
	        cbxSexo.setEnabled(false);
	        spnExequatur.setEnabled(false);

		}
	}
	
	private boolean camposVacios() {
	    return txtNombre.getText().isEmpty() || 
	           txtCedula.getText().isEmpty() || 
	           txtApellido.getText().isEmpty() || 
	           txtTelefono.getText().isEmpty() || 
	           txtDireccion.getText().isEmpty();
	}
	
	private void cargarEspecialidades() {
	    ArrayList<Especialidad> esps = ClinicaMedica.getInstance().getLasEspecialidades();

	    cbxEspecialidad.removeAllItems();
	    
	    for (Especialidad especialidad : esps) {
	        cbxEspecialidad.addItem(especialidad);
	    }
	}
}
