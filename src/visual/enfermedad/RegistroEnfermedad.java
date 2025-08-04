package visual.enfermedad;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.ClinicaMedica;
import logico.Enfermedad;
import logico.TipoEnfermedad;

public class RegistroEnfermedad extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JComboBox cbxTipo;
	private JTextArea txtASintomas;
	private Enfermedad selected;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistroEnfermedad dialog = new RegistroEnfermedad(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistroEnfermedad(Enfermedad enfermedad) {
		selected = enfermedad;
		if(selected == null) {
			setTitle("Registro de enfermedad");
		} else {
			setTitle("Modificar enfermedad");	
		}
		
		setBounds(100, 100, 474, 292);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("C\u00F3digo:");
				lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNewLabel.setBounds(10, 22, 56, 14);
				panel.add(lblNewLabel);
			}
			{
				txtCodigo = new JTextField();
				txtCodigo.setEditable(false);
				txtCodigo.setBounds(76, 19, 362, 20);
				panel.add(txtCodigo);
				txtCodigo.setColumns(10);
				txtCodigo.setText(ClinicaMedica.getInstance().generarNuevoCodigoEnfermedad());
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Nombre:");
				lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNewLabel_1.setBounds(10, 63, 56, 14);
				panel.add(lblNewLabel_1);
			}
			{
				txtNombre = new JTextField();
				txtNombre.setBounds(76, 60, 144, 20);
				panel.add(txtNombre);
				txtNombre.setColumns(10);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Tipo:");
				lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNewLabel_2.setBounds(242, 63, 46, 14);
				panel.add(lblNewLabel_2);
			}
			{
				cbxTipo = new JComboBox();
				cbxTipo.setBounds(294, 60, 144, 20);
				panel.add(cbxTipo);
			}
			cargarTiposEnfermedad();
			{
			JLabel lblNewLabel_3 = new JLabel("S\u00EDntomas:");
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_3.setBounds(10, 99, 59, 14);
			panel.add(lblNewLabel_3);
			}
			
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(76, 99, 362, 94);
			panel.add(panel_1);
			panel_1.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			panel_1.add(scrollPane, BorderLayout.CENTER);
			
			txtASintomas = new JTextArea();
			scrollPane.setViewportView(txtASintomas);
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
						if(selected == null) {
							String codigo = txtCodigo.getText();
							String nombre = txtNombre.getText();
							TipoEnfermedad tipoSeleccionado = (TipoEnfermedad) cbxTipo.getSelectedItem();
							int idTipo = tipoSeleccionado.getIdTipoEnfermedad();
							String sintomas = txtASintomas.getText();
							
							//Validación de que no hayan campos vacíos
							if(nombre.isEmpty() || sintomas.isEmpty() || tipoSeleccionado == null) {
								JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", 
									"Advertencia", JOptionPane.WARNING_MESSAGE);
								return;
							}
							
							Enfermedad enfermedad = new Enfermedad(codigo,nombre,sintomas,idTipo);
							ClinicaMedica.getInstance().insertarEnfermedad(enfermedad);
							JOptionPane.showMessageDialog(null,"Operacion exitosa","Informacion",JOptionPane.INFORMATION_MESSAGE);
							clean();
						} else {
							selected.setIdEnfermedad(txtCodigo.getText());
							selected.setNombre(txtNombre.getText());
							selected.setSintomas(txtASintomas.getText());
							TipoEnfermedad tipoSeleccionado = (TipoEnfermedad) cbxTipo.getSelectedItem();
							int idTipo = tipoSeleccionado.getIdTipoEnfermedad();
							selected.setIdTipoEnfermedad(idTipo);
							ClinicaMedica.getInstance().actualizarEnfermedad(selected);
							JOptionPane.showMessageDialog(null,"Operacion exitosa","Informacion",JOptionPane.INFORMATION_MESSAGE);
							ListadoEnfermedades.loadEnfermedades();
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
		loadEnfermedad();
	}
	
	private void clean() {
		txtCodigo.setText(ClinicaMedica.getInstance().generarNuevoCodigoEnfermedad());
		txtNombre.setText("");
		cbxTipo.setSelectedIndex(0);
		txtASintomas.setText("");
		
	}

	private void loadEnfermedad() {
	    if (selected != null) {
	        txtCodigo.setText(selected.getIdEnfermedad());
	        txtNombre.setText(selected.getNombre());
	        txtASintomas.setText(selected.getSintomas());

	        int idTipo = selected.getIdTipoEnfermedad();
	        for (int i = 0; i < cbxTipo.getItemCount(); i++) {
	            TipoEnfermedad tipo = (TipoEnfermedad) cbxTipo.getItemAt(i);
	            if (tipo.getIdTipoEnfermedad() == idTipo) {
	                cbxTipo.setSelectedIndex(i);
	                break;
	            }
	        }
	    }
	}
	
	private void cargarTiposEnfermedad() {
	    ArrayList<TipoEnfermedad> tipos = ClinicaMedica.getInstance().getTiposEnfermedades();

	    cbxTipo.removeAllItems();
	    
	    for (TipoEnfermedad tipo : tipos) {
	        cbxTipo.addItem(tipo);
	    }
	}
	
}