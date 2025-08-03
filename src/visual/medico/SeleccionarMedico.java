package visual.medico;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.ClinicaMedica;
import logico.Medico;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SeleccionarMedico extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static DefaultTableModel modelo;
	private static Object[] row;
	private static JTable table;
	private int index = -1;
	private Medico selected;
	private JButton btnSeleccionar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SeleccionarMedico dialog = new SeleccionarMedico();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SeleccionarMedico() {
		setTitle("Seleccionar médico");
		setBounds(100, 100, 616, 388);
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
								btnSeleccionar.setEnabled(true);
								String codigo = table.getValueAt(index, 0).toString();
								selected = ClinicaMedica.getInstance().buscarMedicoByIdPersona(codigo);
							}
						}
					});
					modelo = new DefaultTableModel();
					String[] identificadores = {"Código", "Nombre", "Apellido", "Especialidad"};
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
		loadMedicos();
	}
	private void loadMedicos() {
		modelo.setRowCount(0);
		ArrayList<Medico> selected = ClinicaMedica.getInstance().getLosMedicos();
		row = new Object[table.getColumnCount()];
		for(Medico medico:selected) {
			row[0] = medico.getIdPersona();
			row[1] = medico.getNombre();
			row[2] = medico.getApellido();
			row[3] = medico.getEspecialidad();
			modelo.addRow(row);
		}
	}
	
	public Medico getSelectedMedico() {
		return selected;
	}

}