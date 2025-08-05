package visual.vacuna;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.ClinicaMedica;
import logico.TipoEnfermedad;
import logico.Vacuna;
import logico.TipoVacuna;
import logico.Fabricante;

public class RegistroVacuna extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JComboBox cbxTipo;
	private JSpinner spnFechaVen;
	private JComboBox cbxFabricante;
	private JSpinner spnCantidad;
	private Vacuna selected;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistroVacuna dialog = new RegistroVacuna(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistroVacuna(Vacuna vacuna) {
		
		selected = vacuna; 
		if(selected == null) {
			setTitle("Registro de vacuna");
		} else {
			setTitle("Modificar vacuna");
		}
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Registrar Vacuna");
		setBounds(100, 100, 507, 236);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("C\u00F3digo:");
			lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel.setBounds(14, 22, 46, 14);
			panel.add(lblNewLabel);
			
			txtCodigo = new JTextField();
			txtCodigo.setEditable(false);
			txtCodigo.setBounds(74, 19, 121, 20);
			panel.add(txtCodigo);
			txtCodigo.setColumns(10);
			
			JLabel lblNewLabel_1 = new JLabel("Fecha de vencimiento:");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_1.setBounds(186, 22, 148, 14);
			panel.add(lblNewLabel_1);
			
			spnFechaVen = new JSpinner();
			spnFechaVen.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_YEAR));
			JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spnFechaVen, "dd/MM/yy");
			spnFechaVen.setEditor(dateEditor);
			spnFechaVen.setBounds(344, 19, 128, 20);
			panel.add(spnFechaVen);
			
			JLabel lblNewLabel_2 = new JLabel("Nombre:");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_2.setBounds(4, 53, 56, 14);
			panel.add(lblNewLabel_2);
			
			txtNombre = new JTextField();
			txtNombre.setBounds(74, 50, 203, 20);
			panel.add(txtNombre);
			txtNombre.setColumns(10);
			txtCodigo.setText(ClinicaMedica.getInstance().generarNuevoCodigoVacuna());
			
			JLabel lblNewLabel_3 = new JLabel("Tipo:");
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_3.setBounds(291, 53, 46, 14);
			panel.add(lblNewLabel_3);
			
			cbxTipo = new JComboBox();
			cbxTipo.setBounds(351, 50, 121, 20);
			panel.add(cbxTipo);
			
			cargarTiposVacuna();
			
			JLabel lblNewLabel_4 = new JLabel("Fabricante:");
			lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_4.setBounds(-4, 121, 69, 14);
			panel.add(lblNewLabel_4);
			
			cbxFabricante = new JComboBox();
			cbxFabricante.setBounds(74, 118, 380, 20);
			panel.add(cbxFabricante);
			
			cargarFabricantes();
			
			JLabel lblNewLabel_5 = new JLabel("Cantidad:");
			lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_5.setBounds(-14, 90, 80, 14);
			panel.add(lblNewLabel_5);
			
			spnCantidad = new JSpinner();
			spnCantidad.setBounds(74, 87, 80, 20);
			panel.add(spnCantidad);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				if(selected != null) {
					btnRegistrar.setText("Modificar");
				}
				btnRegistrar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						
						if (selected == null) {
							String codigo = txtCodigo.getText();
							String nombre = txtNombre.getText();
						    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						    Date fechaVen = (Date)(spnFechaVen.getValue());
						    TipoVacuna tipoSeleccionado = (TipoVacuna) cbxTipo.getSelectedItem();
							int codTipo = tipoSeleccionado.getCodTipoVacuna();
							Fabricante fabricanteSeleccionado = (Fabricante) cbxFabricante.getSelectedItem();
							int idFabricante = fabricanteSeleccionado.getIdFabricante();
						    int cantidad = new Integer(spnCantidad.getValue().toString());
							
							Vacuna vacuna = new Vacuna(codigo,nombre,codTipo,idFabricante,fechaVen,cantidad);
							ClinicaMedica.getInstance().insertarVacuna(vacuna);
							JOptionPane.showMessageDialog(null,"Operacion exitosa","Informacion",JOptionPane.INFORMATION_MESSAGE);
							clean();
						}
						else {
							selected.setIdVacuna(txtCodigo.getText());
							selected.setNombre(txtNombre.getText());
							selected.setFechaVencimiento((Date)spnFechaVen.getValue());
							TipoVacuna tipoSeleccionado = (TipoVacuna) cbxTipo.getSelectedItem();
							int codTipo = tipoSeleccionado.getCodTipoVacuna();
							Fabricante fabricanteSeleccionado = (Fabricante) cbxFabricante.getSelectedItem();
							int idFabricante = fabricanteSeleccionado.getIdFabricante();
							selected.setCantStock((int)spnCantidad.getValue());
							ClinicaMedica.getInstance().updateVacuna(selected);
							JOptionPane.showMessageDialog(null,"Operacion exitosa","Informacion",JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}
					}

				});
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
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
		loadVacuna();
	}
	
	private void clean() {
		txtCodigo.setText(ClinicaMedica.getInstance().generarNuevoCodigoVacuna());
		txtNombre.setText("");
		cbxTipo.setSelectedIndex(0);
		spnCantidad.setValue(0);
		cbxFabricante.setSelectedIndex(0);
		spnFechaVen.setValue(new Date());
		
	}
	
	private void loadVacuna() {
		if (selected != null) {
			txtCodigo.setText(selected.getIdVacuna());
			txtNombre.setText(selected.getNombre());
			int codTipo = selected.getCodTipoVacuna();
	        for (int i = 0; i < cbxTipo.getItemCount(); i++) {
	            TipoVacuna tipo = (TipoVacuna) cbxTipo.getItemAt(i);
	            if (tipo.getCodTipoVacuna() == codTipo) {
	                cbxTipo.setSelectedIndex(i);
	                break;
	            }
	        }
			spnCantidad.setValue(selected.getCantStock());
			int idFabricante = selected.getIdFabricante();
	        for (int i = 0; i < cbxFabricante.getItemCount(); i++) {
	            Fabricante fabricante = (Fabricante) cbxFabricante.getItemAt(i);
	            if (fabricante.getIdFabricante() == idFabricante) {
	                cbxFabricante.setSelectedIndex(i);
	                break;
	            }
	        }
			spnFechaVen.setValue(selected.getFechaVencimiento());
		}
	}
	
	private void cargarTiposVacuna() {
	    ArrayList<TipoVacuna> tipos = ClinicaMedica.getInstance().getLosTiposVacunas();
	    cbxTipo.removeAllItems();
	    
	    for (TipoVacuna tipo : tipos) {
	        cbxTipo.addItem(tipo);
	    }
	}
	
	private void cargarFabricantes() {
	    ArrayList<Fabricante> fabricantes = ClinicaMedica.getInstance().getLosFabricantes();
	    cbxFabricante.removeAllItems();
	    
	    for (Fabricante fabricante : fabricantes) {
	        cbxFabricante.addItem(fabricante);
	    }
	}
}
