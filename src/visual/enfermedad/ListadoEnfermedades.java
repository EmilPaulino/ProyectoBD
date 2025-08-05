package visual.enfermedad;
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
import logico.Enfermedad;

public class ListadoEnfermedades extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private int index = -1;
	private static DefaultTableModel modelo;
	private static Object[] row;
	private Enfermedad selected;
	private JButton btnModificar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoEnfermedades dialog = new ListadoEnfermedades();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Create the dialog.
	 */
	public ListadoEnfermedades() {
		setTitle("Listado de enfermedades");
		setBounds(100, 100, 611, 375);
		setLocationRelativeTo(null);
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
							if(index>=0 && ClinicaMedica.getLoginUsuario().getIdRol() == 1) {
								btnModificar.setEnabled(true);
								String codigo = table.getValueAt(index,0).toString();
								selected = ClinicaMedica.getInstance().buscarEnfermedadByCodigo(codigo);
							}
						}
					});
					modelo = new DefaultTableModel();
					String[] identificadores = {"Codigo", "Nombre", "Tipo"};
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
				btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						RegistroEnfermedad re = new RegistroEnfermedad(selected);
						re.setModal(true);
						re.setVisible(true);
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
		loadEnfermedades();
	}
	public static void loadEnfermedades() {
		modelo.setRowCount(0);
		ArrayList<Enfermedad> enf = ClinicaMedica.getInstance().getTodasLasEnfermedades();
		row = new Object[table.getColumnCount()];
		for(Enfermedad enfermedad : enf) {
			row[0] = enfermedad.getIdEnfermedad();
			row[1] = enfermedad.getNombre();
			String tipo = ClinicaMedica.getInstance().getTipoEnfermedadByIdEnfermedad(enfermedad.getIdTipoEnfermedad());
			row[2] = tipo;
			modelo.addRow(row);
		}
		
	}
}