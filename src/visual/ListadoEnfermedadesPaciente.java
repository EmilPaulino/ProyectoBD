package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.ClinicaMedica;
import logico.Enfermedad;
import logico.Paciente;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListadoEnfermedadesPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	Paciente paciente;
	private static DefaultTableModel modelo;
	private static Object[] row;
	private JButton btnCurar;
	private int index = -1;
	Enfermedad enf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoEnfermedadesPaciente dialog = new ListadoEnfermedadesPaciente(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoEnfermedadesPaciente(Paciente aux) {
		paciente = aux;
		setTitle("Listado de enfermedades de "+paciente.getNombre()+" "+paciente.getApellido());
		setBounds(100, 100, 584, 347);
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
								btnCurar.setEnabled(true);
								String codigo = table.getValueAt(index, 0).toString();
								enf = ClinicaMedica.getInstance().buscarEnfermedadPacienteByCodigo(paciente, codigo);
							}
						}
					});
					modelo = new DefaultTableModel();
					String[] identificadores = {"Codigo", "Nombre", "Tipo", "Curado"};
					modelo.setColumnIdentifiers(identificadores);
					table.setModel(modelo);
					scrollPane.setViewportView(table);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				{
					btnCurar = new JButton("Curar Enfermedad");
					btnCurar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							enf.setCurada(true);
							btnCurar.setEnabled(false);
							loadEnfermedades();
						}
					});
					btnCurar.setEnabled(false);
					buttonPane.add(btnCurar);
				}
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		loadEnfermedades();
	}

	private void loadEnfermedades() {
		modelo.setRowCount(0);
		ArrayList<Enfermedad> enf = paciente.getMisEnfermedades();
		row = new Object[table.getColumnCount()];
		for(Enfermedad enfermedad : enf) {
			row[0] = enfermedad.getIdEnfermedad();
			row[1] = enfermedad.getNombre();
			row[2] = enfermedad.getTipo();
			if(enfermedad.isCurada()) {
				row[3] = "Si";
			}
			else {
				row[3] = "No";
			}
			
			modelo.addRow(row);
		}
	}

}
