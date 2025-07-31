package visual.enfermedad;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.sun.javafx.scene.control.SelectedCellsMap;

import logico.ClinicaMedica;
import logico.Enfermedad;
import logico.EnfermedadPaciente;
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
	EnfermedadPaciente enf;

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
								enf = ClinicaMedica.getInstance().buscarEnfermedadPacienteByCodigo(paciente.getIdPersona(), codigo);
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
							int Result = ClinicaMedica.getInstance().curarEnfermedadPaciente(enf.getEnfermedad().getIdEnfermedad(), paciente.getIdPersona());
							if(Result == 1) {
								JOptionPane.showMessageDialog(null, "La enfermedad se curó exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(null, "No se pudo curar la enfermedad.", "Error", JOptionPane.ERROR_MESSAGE);
							}
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
		ArrayList<EnfermedadPaciente> enf = ClinicaMedica.getInstance().getEnfermedadesByHistorial(paciente.getIdPersona());
		row = new Object[table.getColumnCount()];
		for(EnfermedadPaciente enfermedad : enf) {
			row[0] = enfermedad.getEnfermedad().getIdEnfermedad();
			row[1] = enfermedad.getEnfermedad().getNombre();
			String tipo = ClinicaMedica.getInstance().getTipoEnfermedadByIdEnfermedad(enfermedad.getEnfermedad().getIdTipoEnfermedad());
			row[2] = tipo;
			if(enfermedad.estaCurado()) {
				row[3] = "Si";
			}
			else {
				row[3] = "No";
			}
			
			modelo.addRow(row);
		}
	}

}
