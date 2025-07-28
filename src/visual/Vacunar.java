package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import logico.ClinicaMedica;
import logico.Paciente;
import logico.Vacuna;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;

public class Vacunar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnRegistrar;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtCedula;
	private Paciente paciente;
	private Vacuna vacuna;
	private JTextField txtNomVacuna;
	private JTextField txtFabricante;
	private JTextField txtCodVacuna;
	private JTextField txtTipoVacuna;
	private JSpinner spnFechVencim;
	private JButton btnSeleccionarVacuna;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Vacunar dialog = new Vacunar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Vacunar() {
		setTitle("Vacunar");
		setBounds(100, 100, 525, 353);
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
			panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos del paciente:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_1.setBounds(10, 11, 479, 122);
			panel.add(panel_1);
			panel_1.setLayout(null);
			
			JLabel label = new JLabel("C\u00F3digo:");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setBounds(10, 56, 56, 14);
			panel_1.add(label);
			
			txtCodigo = new JTextField();
			txtCodigo.setEditable(false);
			txtCodigo.setColumns(10);
			txtCodigo.setBounds(75, 52, 66, 20);
			panel_1.add(txtCodigo);
			
			JLabel label_1 = new JLabel("C\u00E9dula:");
			label_1.setHorizontalAlignment(SwingConstants.RIGHT);
			label_1.setBounds(151, 56, 56, 14);
			panel_1.add(label_1);
			
			JLabel label_2 = new JLabel("Nombre:");
			label_2.setHorizontalAlignment(SwingConstants.RIGHT);
			label_2.setBounds(10, 93, 56, 14);
			panel_1.add(label_2);
			
			txtNombre = new JTextField();
			txtNombre.setEditable(false);
			txtNombre.setColumns(10);
			txtNombre.setBounds(77, 90, 156, 20);
			panel_1.add(txtNombre);
			
			JLabel label_3 = new JLabel("Apellido:");
			label_3.setHorizontalAlignment(SwingConstants.RIGHT);
			label_3.setBounds(244, 93, 56, 14);
			panel_1.add(label_3);
			
			txtApellido = new JTextField();
			txtApellido.setEditable(false);
			txtApellido.setColumns(10);
			txtApellido.setBounds(311, 90, 156, 20);
			panel_1.add(txtApellido);
			
			JButton btnSeleccionarPac = new JButton("Seleccionar");
			btnSeleccionarPac.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SeleccionarPaciente sp = new SeleccionarPaciente();
					sp.setModal(true);
					sp.setVisible(true);
					paciente=sp.getSelectedPaciente();
					if(paciente != null) {
						txtCodigo.setText(paciente.getIdPersona());
						txtCedula.setText(paciente.getCedula());
						txtNombre.setText(paciente.getNombre());
						txtApellido.setText(paciente.getApellido());
					}
				}
			});
			btnSeleccionarPac.setBounds(10, 22, 459, 23);
			panel_1.add(btnSeleccionarPac);
			
			txtCedula = new JTextField();
			txtCedula.setEditable(false);
			txtCedula.setBounds(217, 53, 250, 20);
			panel_1.add(txtCedula);
			txtCedula.setColumns(10);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(null, "Datos de la vacuna:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_2.setBounds(10, 144, 479, 111);
			panel.add(panel_2);
			panel_2.setLayout(null);
			
			JLabel label_4 = new JLabel("Nombre:");
			label_4.setHorizontalAlignment(SwingConstants.RIGHT);
			label_4.setBounds(10, 80, 56, 14);
			panel_2.add(label_4);
			
			txtNomVacuna = new JTextField();
			txtNomVacuna.setEditable(false);
			txtNomVacuna.setColumns(10);
			txtNomVacuna.setBounds(77, 77, 156, 20);
			panel_2.add(txtNomVacuna);
			
			JLabel lblFabricante = new JLabel("Fabricante:");
			lblFabricante.setHorizontalAlignment(SwingConstants.RIGHT);
			lblFabricante.setBounds(237, 80, 65, 14);
			panel_2.add(lblFabricante);
			
			txtFabricante = new JTextField();
			txtFabricante.setEditable(false);
			txtFabricante.setColumns(10);
			txtFabricante.setBounds(313, 77, 156, 20);
			panel_2.add(txtFabricante);
			
			JLabel lblCdigo = new JLabel("C\u00F3digo:");
			lblCdigo.setHorizontalAlignment(SwingConstants.RIGHT);
			lblCdigo.setBounds(9, 52, 56, 14);
			panel_2.add(lblCdigo);
			
			txtCodVacuna = new JTextField();
			txtCodVacuna.setEditable(false);
			txtCodVacuna.setColumns(10);
			txtCodVacuna.setBounds(74, 49, 56, 20);
			panel_2.add(txtCodVacuna);
			
			JLabel lblFechaVenc = new JLabel("Fecha Vencim.");
			lblFechaVenc.setHorizontalAlignment(SwingConstants.RIGHT);
			lblFechaVenc.setBounds(132, 52, 84, 14);
			panel_2.add(lblFechaVenc);
			
			spnFechVencim = new JSpinner();
			spnFechVencim.setModel(new SpinnerDateModel(new Date(1732680000000L), null, null, Calendar.DAY_OF_YEAR));
			JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spnFechVencim, "dd/MM/yy");
			spnFechVencim.setEditor(dateEditor);
			spnFechVencim.setEnabled(false);
			spnFechVencim.setBounds(225, 49, 77, 20);
			panel_2.add(spnFechVencim);
			
			JLabel lblNewLabel = new JLabel("Tipo:");
			lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel.setBounds(313, 52, 41, 14);
			panel_2.add(lblNewLabel);
			
			txtTipoVacuna = new JTextField();
			txtTipoVacuna.setEditable(false);
			txtTipoVacuna.setBounds(364, 49, 106, 20);
			panel_2.add(txtTipoVacuna);
			txtTipoVacuna.setColumns(10);
			
			btnSeleccionarVacuna = new JButton("Seleccionar");
			btnSeleccionarVacuna.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SeleccionarVacuna sv = new SeleccionarVacuna();
					sv.setModal(true);
					sv.setVisible(true);
					vacuna = sv.getSelectedVacuna();
					if(vacuna != null) {
						txtCodVacuna.setText(vacuna.getIdVacuna());
						spnFechVencim.setValue(vacuna.getFecha());
						txtTipoVacuna.setText(vacuna.getTipo());
						txtNomVacuna.setText(vacuna.getNombreVacuna());
						txtFabricante.setText(vacuna.getFabricante());
					}
				}
			});
			btnSeleccionarVacuna.setBounds(10, 18, 459, 23);
			panel_2.add(btnSeleccionarVacuna);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(paciente != null && vacuna != null) {
							paciente.getMisVacunas().add(vacuna);
							vacuna.setCantidad(vacuna.getCantidad()-1);
							JOptionPane.showMessageDialog(null, "Operacion Satisfactoria", "Informacion", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}
						else {
							JOptionPane.showMessageDialog(null, "Se debe seleccionar el paciente y la vacuna", "Error", JOptionPane.ERROR_MESSAGE);
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
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}
}
