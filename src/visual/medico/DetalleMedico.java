package visual.medico;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import logico.Medico;

public class DetalleMedico extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JSpinner spnExequatur;
	Medico selected = null;
	private JSpinner spnEdad;
	private JTextField txtSexo;
	private JTextField txtFecha;
	private JTextField txtEspecialidad;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DetalleMedico dialog = new DetalleMedico(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DetalleMedico(Medico aux) {
		setTitle("Detalle Medico");
		selected = aux;
		setBounds(100, 100, 568, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("C\u00F3digo:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(19, 31, 56, 14);
		contentPanel.add(label);
		
		txtCodigo = new JTextField();
		txtCodigo.setText("M-1");
		txtCodigo.setEditable(false);
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(85, 28, 131, 20);
		contentPanel.add(txtCodigo);
		
		JLabel label_1 = new JLabel("C\u00E9dula:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(225, 31, 56, 14);
		contentPanel.add(label_1);
		
		txtCedula = new JTextField();
		txtCedula.setEnabled(false);
		txtCedula.setColumns(10);
		txtCedula.setBounds(290, 28, 243, 20);
		contentPanel.add(txtCedula);
		
		JLabel label_2 = new JLabel("Nombre:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(19, 68, 56, 14);
		contentPanel.add(label_2);
		
		txtNombre = new JTextField();
		txtNombre.setEnabled(false);
		txtNombre.setColumns(10);
		txtNombre.setBounds(93, 66, 175, 20);
		contentPanel.add(txtNombre);
		
		JLabel label_3 = new JLabel("Apellido:");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(285, 69, 56, 14);
		contentPanel.add(label_3);
		
		txtApellido = new JTextField();
		txtApellido.setEnabled(false);
		txtApellido.setColumns(10);
		txtApellido.setBounds(358, 66, 175, 20);
		contentPanel.add(txtApellido);
		
		JLabel label_4 = new JLabel("Tel\u00E9fono:");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(18, 105, 57, 14);
		contentPanel.add(label_4);
		
		txtTelefono = new JTextField();
		txtTelefono.setEnabled(false);
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(85, 103, 144, 20);
		contentPanel.add(txtTelefono);
		
		JLabel label_5 = new JLabel("Direcci\u00F3n:");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setBounds(238, 106, 65, 14);
		contentPanel.add(label_5);
		
		txtDireccion = new JTextField();
		txtDireccion.setEnabled(false);
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(312, 103, 221, 20);
		contentPanel.add(txtDireccion);
		
		JLabel label_6 = new JLabel("Fecha Nacimiento:");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setBounds(10, 142, 109, 14);
		contentPanel.add(label_6);
		
		JLabel label_7 = new JLabel("Edad:");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setBounds(267, 143, 36, 14);
		contentPanel.add(label_7);
		
		JLabel label_8 = new JLabel("Sexo:");
		label_8.setHorizontalAlignment(SwingConstants.RIGHT);
		label_8.setBounds(378, 143, 36, 14);
		contentPanel.add(label_8);
		
		JLabel label_9 = new JLabel("Especialidad:");
		label_9.setHorizontalAlignment(SwingConstants.RIGHT);
		label_9.setBounds(11, 179, 87, 14);
		contentPanel.add(label_9);
		
		JLabel label_10 = new JLabel("Exequatur:");
		label_10.setHorizontalAlignment(SwingConstants.RIGHT);
		label_10.setBounds(282, 179, 65, 14);
		contentPanel.add(label_10);
		
		spnExequatur = new JSpinner();
		spnExequatur.setEnabled(false);
		spnExequatur.setBounds(358, 176, 175, 20);
		contentPanel.add(spnExequatur);
		
		spnEdad = new JSpinner();
		spnEdad.setEnabled(false);
		spnEdad.setBounds(312, 139, 56, 20);
		contentPanel.add(spnEdad);
		
		txtSexo = new JTextField();
		txtSexo.setEnabled(false);
		txtSexo.setBounds(424, 139, 99, 20);
		contentPanel.add(txtSexo);
		txtSexo.setColumns(10);
		
		txtFecha = new JTextField();
		txtFecha.setEnabled(false);
		txtFecha.setBounds(129, 139, 109, 20);
		contentPanel.add(txtFecha);
		txtFecha.setColumns(10);
		
		txtEspecialidad = new JTextField();
		txtEspecialidad.setEnabled(false);
		txtEspecialidad.setBounds(113, 176, 125, 20);
		contentPanel.add(txtEspecialidad);
		txtEspecialidad.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		loadMedico();
	}

	private void loadMedico() {
		if(selected != null) {
			txtCodigo.setText(selected.getIdPersona());
			txtCedula.setText(selected.getCedula());
			txtNombre.setText(selected.getNombre());
			txtApellido.setText(selected.getApellido());
			txtTelefono.setText(selected.getTelefono());
			txtDireccion.setText(selected.getDireccion());
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			txtFecha.setText(dateFormat.format(selected.getFechaNacimiento()));
			spnEdad.setValue(selected.getEdad());
			txtSexo.setText(selected.getSexo());
			txtEspecialidad.setText(selected.getEspecialidad());
			spnExequatur.setValue(selected.getExequatur());
		}
		
	}
}
