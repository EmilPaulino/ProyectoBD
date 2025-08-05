package visual.paciente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.ClinicaMedica;
import logico.Medico;
import logico.Paciente;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SeleccionarPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Paciente selected = null;
	private JButton btnSeleccionar;
	private static DefaultTableModel modelo;
	private static Object[] row;
	private static JTable table;
	private int index = -1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SeleccionarPaciente dialog = new SeleccionarPaciente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SeleccionarPaciente() {
		setTitle("Seleccionar paciente");
		setBounds(100, 100, 593, 386);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
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
								btnSeleccionar.setEnabled(true);
								String codigo = table.getValueAt(index, 0).toString();
								selected = ClinicaMedica.getInstance().buscarPacienteByIdPersona(codigo);
							}
						}
					});
					modelo = new DefaultTableModel();
					String[] identificadores = {"ID Persona", "Nombre", "Apellido", "Teléfono"};
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
				btnSeleccionar = new JButton("Seleccionar");
				btnSeleccionar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(selected != null) {
							dispose();
						}
					}
				});
				btnSeleccionar.setEnabled(false);
				btnSeleccionar.setActionCommand("OK");
				buttonPane.add(btnSeleccionar);
				getRootPane().setDefaultButton(btnSeleccionar);
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
		loadPacientes();
	}
	public static void loadPacientes() {
		modelo.setRowCount(0);
		ArrayList<Paciente> pac = ClinicaMedica.getInstance().getLosPacientes();
		row = new Object[table.getColumnCount()];
		for(Paciente paciente:pac) {
			row[0] = paciente.getIdPersona();
	        row[1] = paciente.getNombre();
	        row[2] = paciente.getApellido();
	        row[3] = paciente.getTelefono();
	        modelo.addRow(row);
		}
	}
	public Paciente getSelectedPaciente() {
		return selected;
	}
	
}
