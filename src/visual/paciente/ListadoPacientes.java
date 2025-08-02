package visual.paciente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.ClinicaMedica;
import logico.Paciente;

public class ListadoPacientes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private int index = -1;
	private static DefaultTableModel modelo;
	private static Object[] row;
	private JButton btnDetalles;
	private Paciente selected;
	private JButton btnModificar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoPacientes dialog = new ListadoPacientes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoPacientes() {
		setTitle("Listado de pacientes");
		setBounds(100, 100, 592, 389);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					table = new JTable();
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							index = table.getSelectedRow();

							if(index >= 0) {
								btnDetalles.setEnabled(true);
								btnModificar.setEnabled(true);
								String codigo = table.getValueAt(index, 0).toString();
								selected = ClinicaMedica.getInstance().buscarPacienteByIdPersona(codigo);
							}
						}
					});
					modelo = new DefaultTableModel();
					String[] identificadores = {"ID Persona", "Cédula", "Nombre", "Apellido", "Teléfono"};
					modelo.setColumnIdentifiers(identificadores);
					table.setModel(modelo);
					scrollPane.setViewportView(table);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnDetalles = new JButton("Ver detalle");
				btnDetalles.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						DetallePaciente dp = new DetallePaciente(selected);
						dp.setModal(true);
						dp.setVisible(true);
					}
				});
				btnDetalles.setEnabled(false);
				buttonPane.add(btnDetalles);
			}
			{
				btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						RegistroPaciente rp = new RegistroPaciente(selected);
						rp.setModal(true);
						rp.setVisible(true);
					}
				});
				btnModificar.setEnabled(false);
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
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
		loadPacientes();
	}
	public static void loadPacientes() {
		modelo.setRowCount(0);
		ArrayList<Paciente> pac = ClinicaMedica.getInstance().getLosPacientes();
		row = new Object[table.getColumnCount()];
		for(Paciente paciente:pac) {
			row[0] = paciente.getIdPersona();
			row[1] = paciente.getCedula();
	        row[2] = paciente.getNombre();
	        row[3] = paciente.getApellido();
	        row[4] = paciente.getTelefono();
	        modelo.addRow(row);
		}
	}
}