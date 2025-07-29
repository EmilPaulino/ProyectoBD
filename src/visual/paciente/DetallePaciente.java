package visual.paciente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Paciente;
import visual.enfermedad.ListadoEnfermedadesPaciente;
import visual.medico.HistorialMedico;
import visual.vacuna.ListadoVacunas;

public class DetallePaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	Paciente selected = null;
	private JSpinner spnFechaNacim;
	private JSpinner spnEstatura;
	private JSpinner spnPeso;
	private JSpinner spnEdad;
	private JTextField txtSexo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DetallePaciente dialog = new DetallePaciente(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DetallePaciente(Paciente aux) {
		selected = aux;
		setTitle("Detalle del paciente");
		setBounds(100, 100, 613, 402);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new TitledBorder(null, "Datos del paciente:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_1.setBounds(10, 11, 567, 225);
				panel.add(panel_1);
				panel_1.setLayout(null);
				
				JLabel label = new JLabel("C\u00F3digo:");
				label.setHorizontalAlignment(SwingConstants.RIGHT);
				label.setBounds(31, 34, 56, 14);
				panel_1.add(label);
				
				txtCodigo = new JTextField();
				txtCodigo.setEditable(false);
				txtCodigo.setColumns(10);
				txtCodigo.setBounds(96, 30, 131, 20);
				panel_1.add(txtCodigo);
				
				JLabel label_1 = new JLabel("C\u00E9dula:");
				label_1.setHorizontalAlignment(SwingConstants.RIGHT);
				label_1.setBounds(237, 33, 56, 14);
				panel_1.add(label_1);
				
				txtCedula = new JTextField();
				txtCedula.setEditable(false);
				txtCedula.setColumns(10);
				txtCedula.setBounds(303, 30, 243, 20);
				panel_1.add(txtCedula);
				
				JLabel label_2 = new JLabel("Nombre:");
				label_2.setHorizontalAlignment(SwingConstants.RIGHT);
				label_2.setBounds(31, 71, 56, 14);
				panel_1.add(label_2);
				
				txtNombre = new JTextField();
				txtNombre.setEditable(false);
				txtNombre.setColumns(10);
				txtNombre.setBounds(95, 68, 175, 20);
				panel_1.add(txtNombre);
				
				JLabel label_3 = new JLabel("Apellido:");
				label_3.setHorizontalAlignment(SwingConstants.RIGHT);
				label_3.setBounds(303, 71, 56, 14);
				panel_1.add(label_3);
				
				txtApellido = new JTextField();
				txtApellido.setEditable(false);
				txtApellido.setColumns(10);
				txtApellido.setBounds(371, 68, 175, 20);
				panel_1.add(txtApellido);
				
				txtDireccion = new JTextField();
				txtDireccion.setEditable(false);
				txtDireccion.setColumns(10);
				txtDireccion.setBounds(325, 105, 221, 20);
				panel_1.add(txtDireccion);
				
				JLabel label_4 = new JLabel("Direcci\u00F3n:");
				label_4.setHorizontalAlignment(SwingConstants.RIGHT);
				label_4.setBounds(250, 108, 65, 14);
				panel_1.add(label_4);
				
				txtTelefono = new JTextField();
				txtTelefono.setEditable(false);
				txtTelefono.setColumns(10);
				txtTelefono.setBounds(96, 105, 144, 20);
				panel_1.add(txtTelefono);
				
				JLabel label_5 = new JLabel("Tel\u00E9fono:");
				label_5.setHorizontalAlignment(SwingConstants.RIGHT);
				label_5.setBounds(30, 108, 57, 14);
				panel_1.add(label_5);
				
				JLabel label_6 = new JLabel("Fecha Nacimiento:");
				label_6.setHorizontalAlignment(SwingConstants.RIGHT);
				label_6.setBounds(22, 145, 109, 14);
				panel_1.add(label_6);
				
				spnFechaNacim = new JSpinner();
				spnFechaNacim.setModel(new SpinnerDateModel(new Date(1732420800000L), null, null, Calendar.DAY_OF_YEAR));
				spnFechaNacim.setEnabled(false);
				spnFechaNacim.setBounds(141, 142, 129, 20);
				panel_1.add(spnFechaNacim);
				
				JLabel label_7 = new JLabel("Sexo:");
				label_7.setHorizontalAlignment(SwingConstants.RIGHT);
				label_7.setBounds(400, 145, 46, 14);
				panel_1.add(label_7);
				
				JLabel label_8 = new JLabel("Edad:");
				label_8.setHorizontalAlignment(SwingConstants.RIGHT);
				label_8.setBounds(280, 145, 46, 14);
				panel_1.add(label_8);
				
				JLabel label_9 = new JLabel("Estatura:");
				label_9.setHorizontalAlignment(SwingConstants.RIGHT);
				label_9.setBounds(31, 182, 56, 14);
				panel_1.add(label_9);
				
				spnEstatura = new JSpinner();
				spnEstatura.setEnabled(false);
				spnEstatura.setBounds(96, 179, 175, 20);
				panel_1.add(spnEstatura);
				
				JLabel label_10 = new JLabel("Peso:");
				label_10.setHorizontalAlignment(SwingConstants.RIGHT);
				label_10.setBounds(303, 182, 46, 14);
				panel_1.add(label_10);
				
				spnPeso = new JSpinner();
				spnPeso.setEnabled(false);
				spnPeso.setBounds(359, 179, 175, 20);
				panel_1.add(spnPeso);
				
				spnEdad = new JSpinner();
				spnEdad.setEnabled(false);
				spnEdad.setBounds(335, 142, 65, 20);
				panel_1.add(spnEdad);
				
				txtSexo = new JTextField();
				txtSexo.setEnabled(false);
				txtSexo.setBounds(455, 142, 91, 20);
				panel_1.add(txtSexo);
				txtSexo.setColumns(10);
			}
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Historial de vacunas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_1.setBounds(13, 247, 178, 57);
			panel.add(panel_1);
			panel_1.setLayout(null);
			
			JButton btnAbrirHVacunas = new JButton("Abrir");
			btnAbrirHVacunas.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					ListadoVacunas lv = new ListadoVacunas(selected);
					lv.setModal(true);
					lv.setVisible(true);
				}
			});
			btnAbrirHVacunas.setBounds(10, 23, 158, 23);
			panel_1.add(btnAbrirHVacunas);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Historial m\u00E9dico", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_2.setBounds(204, 247, 178, 57);
			panel.add(panel_2);
			panel_2.setLayout(null);
			
			JButton btnAbrirHMedico = new JButton("Abrir");
			btnAbrirHMedico.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(selected != null) {
						HistorialMedico hm = new HistorialMedico(selected);
						hm.setModal(true);
						hm.setVisible(true);
					}
				}
			});
			btnAbrirHMedico.setBounds(10, 23, 158, 23);
			panel_2.add(btnAbrirHMedico);
			
			JPanel panel_3 = new JPanel();
			panel_3.setBorder(new TitledBorder(null, "Historial de enfermedades", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_3.setBounds(395, 247, 178, 57);
			panel.add(panel_3);
			panel_3.setLayout(null);
			
			JButton btnHistorialEnf = new JButton("Abrir");
			btnHistorialEnf.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(selected != null) {
						ListadoEnfermedadesPaciente lep = new ListadoEnfermedadesPaciente(selected);
						lep.setModal(true);
						lep.setVisible(true);
					}
				}
			});
			btnHistorialEnf.setBounds(10, 23, 158, 23);
			panel_3.add(btnHistorialEnf);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
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
		loadPaciente();
	}
	private void loadPaciente() {
		if(selected != null) {
			txtCodigo.setText(selected.getIdPersona());
			txtCedula.setText(selected.getCedula());
			txtNombre.setText(selected.getNombre());
			txtApellido.setText(selected.getApellido());
			txtTelefono.setText(selected.getTelefono());
			txtDireccion.setText(selected.getDireccion());
			spnFechaNacim.setValue(selected.getFechaNacimiento());
			spnEdad.setValue(selected.getEdad());
			txtSexo.setText(selected.getSexo());
			spnEstatura.setValue(selected.getEstatura());
			spnPeso.setValue(selected.getPeso());
		}
	}
}
