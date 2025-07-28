package visual;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Paciente;
import logico.Vacuna;

public class ListadoVacunas extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static DefaultTableModel modelo;
	private static Object[] row;
	private Paciente paciente;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoVacunas dialog = new ListadoVacunas(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Create the dialog.
	 */
	public ListadoVacunas(Paciente pac) {
		paciente = pac;
		setTitle("Listado de vacunas");
		setBounds(100, 100, 582, 355);
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
					modelo = new DefaultTableModel();
					String[] identificadores = {"Código", "Nombre", "Tipo", "Fabricante", "Fecha Vencimiento"};
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
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		loadVacunas();
	}
	private void loadVacunas() {
	    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

		modelo.setRowCount(0);
		ArrayList<Vacuna> vac = paciente.getMisVacunas();
		row = new Object[table.getColumnCount()];
		for(Vacuna vacunas:vac) {
			row[0] = vacunas.getIdVacuna();
		    row[1] = vacunas.getNombreVacuna();
	        row[2] = vacunas.getTipo();
		    row[3] = vacunas.getFabricante();
		    row[4] = dateFormatter.format(vacunas.getFecha());
		    modelo.addRow(row);
		}
	}
}