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
import logico.Consulta;
import logico.Paciente;
import logico.Persona;
import visual.consulta.RegistroConsulta;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HistorialMedico extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static DefaultTableModel modelo;
	private static JTable table;
	private static Object[] row;
	private int index = -1;
	private Paciente selected = null;
	private Consulta sel;
	private JButton btnDetalle;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			HistorialMedico dialog = new HistorialMedico(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public HistorialMedico(Paciente aux) {
		selected = aux;
		setTitle("Historial m\u00E9dico "+selected.getNombre());
		setBounds(100, 100, 596, 376);
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
								btnDetalle.setEnabled(true);
								String codigo = table.getValueAt(index, 0).toString();
								sel = ClinicaMedica.getInstance().getConsultaById(codigo);
							}
						}
					});
					modelo = new DefaultTableModel();
					String[] identificadores = {"Código", "Fecha", "Diagnóstico", "Médico"};
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
				btnDetalle = new JButton("Ver detalle");
				btnDetalle.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(sel != null) {
							RegistroConsulta rc = new RegistroConsulta(sel);
							rc.setModal(true);
							rc.setVisible(true);
						}
					}
				});
				btnDetalle.setEnabled(false);
				btnDetalle.setActionCommand("OK");
				buttonPane.add(btnDetalle);
				getRootPane().setDefaultButton(btnDetalle);
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
		loadHistorial();
	}
	private void loadHistorial() {
	    if (selected != null) {
	        modelo.setRowCount(0);
	        ArrayList<Consulta> consultas = ClinicaMedica.getInstance().getConsultasImportantesByIdPersona(selected.getIdPersona());
	        row = new Object[table.getColumnCount()];

	        for (Consulta consulta : consultas) {
	            row[0] = consulta.getCodConsulta();
	            row[1] = consulta.getFecha();
	            row[2] = consulta.getDiagnostico();
	            Persona persona = ClinicaMedica.getInstance().buscarPersonabyCodigo(consulta.getIdPersona());
	            row[3] = persona.getNombre() + " " + persona.getApellido();
	            modelo.addRow(row);
	        }
	    }
	}
}
