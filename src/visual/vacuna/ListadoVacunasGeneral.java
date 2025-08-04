package visual.vacuna;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.ClinicaMedica;
import logico.Vacuna;
import logico.TipoVacuna;

public class ListadoVacunasGeneral extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static DefaultTableModel modelo;
	private static Object[] row;
	private JButton btnAgregar;
	private Vacuna selected;
	private JButton btnModificar;
	private int index = -1;
	private JButton btnEliminar;

	/** 
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoVacunasGeneral dialog = new ListadoVacunasGeneral();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Create the dialog.
	 */
	public ListadoVacunasGeneral() {
		setTitle("Listado general de vacunas");
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

					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							index = table.getSelectedRow();
							if(index >= 0) {
								btnModificar.setEnabled(true);
								btnEliminar.setEnabled(true);
								String codigo = table.getValueAt(index, 0).toString();
								selected = ClinicaMedica.getInstance().buscarVacunaByCodigo(codigo);
							}
						}
					});
					modelo = new DefaultTableModel();
					String[] identificadores = {"Código", "Fecha Vencimiento", "Nombre", "Tipo", "Fabricante", "Cant."};
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

				btnAgregar = new JButton("Insertar");
				btnAgregar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						RegistroVacuna rv = new RegistroVacuna(null);
						rv.setModal(true);
						rv.setVisible(true);
						loadVacunas();
					}
				});
				btnAgregar.setEnabled(true);
				btnAgregar.setActionCommand("OK");
				buttonPane.add(btnAgregar);
				getRootPane().setDefaultButton(btnAgregar);

				btnModificar = new JButton("Modificar");


				btnModificar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						RegistroVacuna rv = new RegistroVacuna(selected);
						rv.setModal(true);
						rv.setVisible(true);
						loadVacunas();
					}



				});
				btnModificar.setEnabled(false);
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
			}
			JButton btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			{
				btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (index >= 0 && selected != null) {
							int confirm = javax.swing.JOptionPane.showConfirmDialog(
									null,
									"¿Estás seguro de que deseas eliminar esta vacuna?",
									"Confirmación de eliminación",
									javax.swing.JOptionPane.YES_NO_OPTION
									);

							if (confirm == javax.swing.JOptionPane.YES_OPTION) {
								ClinicaMedica.getInstance().eliminarVacuna(selected);
								javax.swing.JOptionPane.showMessageDialog(null, "Vacuna eliminada con éxito.");

								loadVacunas();

								btnEliminar.setEnabled(false);
								btnModificar.setEnabled(false);
							}
						} else {
							javax.swing.JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna vacuna.");
						}
					}
				});
				btnEliminar.setEnabled(false);
				buttonPane.add(btnEliminar);
			}
			btnCancelar.setActionCommand("Cancel");
			buttonPane.add(btnCancelar);
		}
		loadVacunas();
	}
	private void loadVacunas() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		modelo.setRowCount(0);

		ArrayList<Vacuna> vac = ClinicaMedica.getInstance().getVacunasGenerales();
		if (vac == null || vac.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No hay vacunas disponibles.");
			return;
		}

		row = new Object[6]; 
		for (Vacuna vacunas : vac) {
			row[0] = vacunas.getIdVacuna();
			row[1] = (vacunas.getFechaVencimiento() != null) ? dateFormatter.format(vacunas.getFechaVencimiento()) : "Fecha no disponible";
			row[2] = vacunas.getNombre();
			String tipo = ClinicaMedica.getInstance().getTipoVacunaByCodTipoVacuna(vacunas.getCodTipoVacuna());
			row[3] = tipo;
			String fabricante = ClinicaMedica.getInstance().getFabricanteByIdFabricante(vacunas.getIdFabricante());
			row[4] = fabricante;
			row[5] = vacunas.getCantStock();
			modelo.addRow(row);
		}
	}
}