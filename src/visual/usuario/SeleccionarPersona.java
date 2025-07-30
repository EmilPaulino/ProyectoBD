package visual.usuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.ClinicaMedica;
import logico.Enfermedad;
import logico.Persona;
import logico.Usuario;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map;

public class SeleccionarPersona extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static DefaultTableModel modelo;
	private static Object[] row;
	private static JTable table;
	private int index = -1;
	private Persona selected;
	private JButton btnSeleccionar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SeleccionarPersona dialog = new SeleccionarPersona();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SeleccionarPersona() {
		setBounds(100, 100, 545, 329);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				index = table.getSelectedRow();
				if(index >= 0) {
					btnSeleccionar.setEnabled(true);
					String codigo = table.getValueAt(index, 0).toString();
					selected = ClinicaMedica.getInstance().buscarPersonabyCodigo(codigo);
				}
			}
		});
		scrollPane.setViewportView(table);
		modelo = new DefaultTableModel();
		String[] identificadores = {"Código", "Cédula", "Nombre"};
		modelo.setColumnIdentifiers(identificadores);
		table.setModel(modelo);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnSeleccionar = new JButton("Seleccionar");
				btnSeleccionar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(selected!=null) {
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
		loadPersonasSinUsuario();
	}

	private void loadPersonasSinUsuario() {
		modelo.setRowCount(0);
		ArrayList<Persona> pers = ClinicaMedica.getInstance().getPersonasSinUsuario();
		row = new Object[table.getColumnCount()];
		for (Persona persona : pers) {
		    row[0] = persona.getIdPersona();
		    row[1] = persona.getCedula();
		    row[2] = persona.getNombre()+' '+persona.getApellido();
		    modelo.addRow(row);
		}
	}

	public Persona getSelectedPersona() {
		return selected;
	}
}
