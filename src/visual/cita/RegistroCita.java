package visual.cita;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Cita;
import logico.ClinicaMedica;
import logico.Especialidad;
import logico.Medico;
import visual.medico.SeleccionarMedico;

public class RegistroCita extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtMedico; 
	private JTextField txtEspecialidad;
	private JTextField txtMotivo;
	private Medico medico;
	private JSpinner spnFecha;
	private JSpinner spnHora;
	private Cita selected;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistroCita dialog = new RegistroCita(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistroCita(Cita cita) {
		
		selected = cita; 
		if(selected == null) {
			setTitle("Registro de m\u00E9dico");
		} else {
			setTitle("Modificar m\u00E9dico");
		}
		
		setTitle("Registrar Cita");
		setBounds(100, 100, 466, 352);
		setLocationRelativeTo(null);
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
			panel_1.setBorder(new TitledBorder(null, "Informaci\u00F3n general de la cita:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(10, 11, 421, 144);
			panel.add(panel_1);
			panel_1.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("C\u00F3digo:");
				lblNewLabel.setBounds(8, 27, 55, 14);
				panel_1.add(lblNewLabel);
				lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			}
			
			txtCodigo = new JTextField();
			txtCodigo.setText(ClinicaMedica.getInstance().generarNuevoCodigoCita());
			txtCodigo.setEditable(false);
			txtCodigo.setBounds(69, 24, 342, 20);
			panel_1.add(txtCodigo);
			txtCodigo.setColumns(10);
			
			JLabel lblNewLabel_1 = new JLabel("Nombre:");
			lblNewLabel_1.setBounds(8, 58, 55, 14);
			panel_1.add(lblNewLabel_1);
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			
			txtNombre = new JTextField();
			txtNombre.setBounds(69, 55, 342, 20);
			panel_1.add(txtNombre);
			txtNombre.setColumns(10);
			
			JLabel lblNewLabel_3 = new JLabel("Fecha:");
			lblNewLabel_3.setBounds(18, 86, 46, 14);
			panel_1.add(lblNewLabel_3);
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
			
			spnFecha = new JSpinner();
			spnFecha.setBounds(69, 83, 139, 20);
			panel_1.add(spnFecha);
			spnFecha.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_YEAR));
			JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spnFecha, "dd/MM/yyyy");
			spnFecha.setEditor(dateEditor);


			
			JLabel lblNewLabel_5 = new JLabel("Hora:");
			lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_5.setBounds(219, 86, 46, 14);
			panel_1.add(lblNewLabel_5);
			
			spnHora = new JSpinner();
			spnHora.setBounds(275, 83, 136, 20);
			panel_1.add(spnHora);
			spnHora.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY));
			JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(spnHora, "hh:mm a");
			spnHora.setEditor(timeEditor);

			
			JLabel lblNewLabel_6 = new JLabel("Motivo:");
			lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_6.setBounds(17, 111, 46, 14);
			panel_1.add(lblNewLabel_6);
			
			txtMotivo = new JTextField();
			txtMotivo.setBounds(69, 108, 342, 20);
			panel_1.add(txtMotivo);
			txtMotivo.setColumns(10);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(null, "Informaci\u00F3n del m\u00E9dico:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_2.setBounds(10, 166, 420, 92);
			panel.add(panel_2);
			panel_2.setLayout(null);
			
			JLabel lblNewLabel_2 = new JLabel("M\u00E9dico:");
			lblNewLabel_2.setBounds(14, 63, 46, 14);
			panel_2.add(lblNewLabel_2);
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
			
			txtMedico = new JTextField();
			txtMedico.setEditable(false);
			txtMedico.setBounds(74, 60, 113, 20);
			panel_2.add(txtMedico);
			txtMedico.setColumns(10);
			
			JLabel lblNewLabel_4 = new JLabel("Especialidad:");
			lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_4.setBounds(201, 63, 76, 14);
			panel_2.add(lblNewLabel_4);
			
			txtEspecialidad = new JTextField();
			txtEspecialidad.setEditable(false);
			txtEspecialidad.setBounds(291, 60, 113, 20);
			panel_2.add(txtEspecialidad);
			txtEspecialidad.setColumns(10);
			
			JButton btnSeleccionar = new JButton("Seleccionar");
			btnSeleccionar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					SeleccionarMedico sm = new SeleccionarMedico();
					sm.setModal(true);
					sm.setVisible(true);
					medico = sm.getSelectedMedico();
					if(medico != null) {
						txtMedico.setText(medico.getNombre()+" "+medico.getApellido());
						String esp = ClinicaMedica.getInstance().getEspecialidadByIdEspecialidad(medico.getEspecialidad());
						txtEspecialidad.setText(esp);
					}
				}
			});
			btnSeleccionar.setBounds(20, 26, 390, 23);
			panel_2.add(btnSeleccionar);
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
                        
                        if (selected == null) {

                            if (camposVacios()) {
                                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Información", JOptionPane.ERROR_MESSAGE);

                            } else {
                                Date fecha = (Date) spnFecha.getValue();
                                Date hora = (Date) spnHora.getValue();
                               
	                             Calendar calendar = Calendar.getInstance();
	                             calendar.setTime(hora);
	                             calendar.set(Calendar.SECOND, 0);
	                             calendar.set(Calendar.MILLISECOND, 0);
	
	                             Time horaSQL = new Time(calendar.getTimeInMillis());

	                             if(ClinicaMedica.getInstance().existeCita(fecha, horaSQL, medico, txtCodigo.getText())) {
	                                 JOptionPane.showMessageDialog(null, "Horario no disponible.", "Información", JOptionPane.ERROR_MESSAGE);

	                                 return;
	                             }else {
	                            	 Cita cita = new Cita(
	                                         txtCodigo.getText(),
	                                         medico.getIdPersona(),
	                                         txtNombre.getText(),
	                                         fecha,
	                                         horaSQL,
	                                         txtMotivo.getText()
	                                     );

	                                     ClinicaMedica.getInstance().insertarCita(cita);
	                                     JOptionPane.showMessageDialog(null, "Operación Satisfactoria", "Información", JOptionPane.INFORMATION_MESSAGE);
	                                     clean();
	                             }
                                
                            }
                        }
                        else {
                        	Date fecha = (Date) spnFecha.getValue();
                        	selected.setCodCita(txtCodigo.getText());
                            selected.setNombre(txtNombre.getText());
                            selected.setIdPersona(medico.getIdPersona());
                            selected.setFecha(fecha);

                            // Procesar la hora para que tenga segundos y milisegundos en 0
                            
                            Date hora = (Date) spnHora.getValue();
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(hora);
                            calendar.set(Calendar.SECOND, 0);
                            calendar.set(Calendar.MILLISECOND, 0);
                            Time horaSQL = new Time(calendar.getTimeInMillis());

                            selected.setHora(horaSQL);
                            selected.setMotivo(txtMotivo.getText());
                            if(ClinicaMedica.getInstance().existeCita(fecha, horaSQL, medico,selected.getCodCita())) {
                            	JOptionPane.showMessageDialog(null, "Horario no disponible.", "Información", JOptionPane.ERROR_MESSAGE);
                            }else {
                            	ClinicaMedica.getInstance().updateCita(selected);
                                ListadoCitas.loadCitas();
                                JOptionPane.showMessageDialog(null, "Operación exitosa", "Información", JOptionPane.INFORMATION_MESSAGE);
                                dispose();
                            }

                            
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
		loadCita();
	}
	private void clean() {
	    txtCodigo.setText(ClinicaMedica.getInstance().generarNuevoCodigoCita());
	    txtNombre.setText("");
	    spnFecha.setValue(new Date());
	    spnHora.setValue(new Date());
	    txtMotivo.setText("");
	    txtMedico.setText("");
	    txtEspecialidad.setText("");
	    medico = null;
	}

	private boolean camposVacios() {
		boolean estanVacios = false;
		if(txtNombre.getText().isEmpty() || txtMotivo.getText().isEmpty() || medico == null) {
			estanVacios = true;
		}
		return estanVacios;
	}
	private void loadCita() {
		if(selected != null) {
			txtCodigo.setText(selected.getCodCita());
			txtNombre.setText(selected.getNombre());
			Medico med = ClinicaMedica.getInstance().buscarMedicoByIdPersona(selected.getIdPersona());
			medico = med;
			txtMedico.setText(medico.getNombre()+' '+medico.getApellido());
			String esp = ClinicaMedica.getInstance().getEspecialidadByIdEspecialidad(medico.getEspecialidad());
			txtEspecialidad.setText(esp);
			txtMotivo.setText(selected.getMotivo());
			spnFecha.setValue(selected.getFecha());
			spnHora.setValue(selected.getHora());
			
		}
	}
}