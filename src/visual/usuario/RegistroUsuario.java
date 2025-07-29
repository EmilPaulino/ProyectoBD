package visual.usuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import logico.ClinicaMedica;
import logico.Medico;
import logico.Usuario;

public class RegistroUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtContrasenia;
	private JTextField txtConfContra;
	private Usuario selected;
	private JComboBox cbxRol;
	private JTextField txtCodigo;
	private JTextField txtCedulaMedico;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistroUsuario dialog = new RegistroUsuario(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistroUsuario(Usuario usuario) {
		setTitle("Registro usuario");
		
		selected = usuario;
		if(selected==null) {
			setTitle("Registro de usuario");
		} else {
			setTitle("Modificar paciente");
		}
		
		setBounds(100, 100, 515, 305);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 479, 206);
		contentPanel.add(panel);
		panel.setLayout(null);
		{
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNombre.setBounds(219, 20, 113, 14);
			panel.add(lblNombre);
		}
		{ 
			txtNombre = new JTextField();
			txtNombre.setColumns(10);
			txtNombre.setBounds(342, 17, 127, 20);
			panel.add(txtNombre);
		}
		{
			JLabel lblContrasenia = new JLabel("Contrase\u00F1a:");
			lblContrasenia.setBounds(7, 64, 127, 14);
			panel.add(lblContrasenia);
		}
		{
			txtContrasenia = new JTextField();
			txtContrasenia.setColumns(10);
			txtContrasenia.setBounds(85, 61, 127, 20);
			panel.add(txtContrasenia);
		}
		{
			JLabel lblConfContra = new JLabel("Confirmar contrase\u00F1a:");
			lblConfContra.setFont(new Font("Tahoma", Font.PLAIN, 10));
			lblConfContra.setHorizontalAlignment(SwingConstants.RIGHT);
			lblConfContra.setBounds(195, 64, 141, 14);
			panel.add(lblConfContra);
		}
		{
			txtConfContra = new JTextField();
			txtConfContra.setColumns(10);
			txtConfContra.setBounds(342, 61, 127, 20);
			panel.add(txtConfContra);
		}
		{
			JLabel lblRol = new JLabel("Rol:");
			lblRol.setHorizontalAlignment(SwingConstants.RIGHT);
			lblRol.setBounds(-44, 114, 96, 14);
			panel.add(lblRol);
		}
		{
			cbxRol = new JComboBox();
			cbxRol.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(cbxRol.getSelectedItem().equals("Médico")) {
						txtCedulaMedico.setEnabled(true);
					}
				}
			});
			cbxRol.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "M\u00E9dico", "Administrativo", "Administrador"}));
			cbxRol.setBounds(85, 111, 127, 20);
			panel.add(cbxRol);
		}
		{
			JLabel lblCodigo = new JLabel("C\u00F3digo:");
			lblCodigo.setBounds(21, 20, 113, 14);
			panel.add(lblCodigo);
		}
		{
			txtCodigo = new JTextField();
			txtCodigo.setEnabled(false);
			txtCodigo.setColumns(10);
			txtCodigo.setBounds(85, 17, 127, 20);
			panel.add(txtCodigo);
			txtCodigo.setText("U-"+ClinicaMedica.getInstance().codUsuario);

		}
		
		JLabel lblCedulaMedico = new JLabel("C\u00E9dula m\u00E9dico:");
		lblCedulaMedico.setBounds(247, 114, 123, 14);
		panel.add(lblCedulaMedico);
		
		//txtCedulaMedico = new JTextField();
		try {
			txtCedulaMedico = new JFormattedTextField(new javax.swing.text.MaskFormatter("###-#######-#"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtCedulaMedico.setEnabled(false);
		txtCedulaMedico.setBounds(343, 111, 126, 20);
		panel.add(txtCedulaMedico);
		txtCedulaMedico.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(null);
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
							String contrasena = txtContrasenia.getText();
							String confContra = txtConfContra.getText();
							String rol = (String) cbxRol.getSelectedItem();
							Medico medicoSeleccionado;
							String cedulaMedico = txtCedulaMedico.getText();
							medicoSeleccionado = ClinicaMedica.getInstance().buscarMedicoByCedula(cedulaMedico);
							if("Medico".equalsIgnoreCase((String) cbxRol.getSelectedItem()) && !txtCedulaMedico.getText().equals("")) {
								if(medicoSeleccionado==null) {
						            JOptionPane.showMessageDialog(null, "Medico no encontrado", 
	                                        "Error", JOptionPane.ERROR_MESSAGE);
						            return;
								
								}
							}
							
							if(!(contrasena.equals(confContra))) {
					            JOptionPane.showMessageDialog(null, "Las contraseñas no son iguales", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
					            	return;
							}
							
							//Usuario usuario = new Usuario(codigo,nombre,confContra,rol,medicoSeleccionado);
							
							ClinicaMedica.getInstance().regUser(usuario);
							JOptionPane.showMessageDialog(null,"Operacion exitosa","Informacion",JOptionPane.INFORMATION_MESSAGE);
							clean();
						} else {
							selected.setUsuario(txtNombre.getText());
							selected.setContrasenia(txtContrasenia.getText());
							//selected.setIdRol((String)cbxRol.getSelectedItem());
							ClinicaMedica.getInstance().updateUsuario(selected);
							ListadoUsuarios.loadUsuarios();
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
		loadUsuarios();
	}
	
	private void clean() {
		txtCodigo.setText("U-"+ClinicaMedica.getInstance().codUsuario);
		txtNombre.setText("");
		txtContrasenia.setText("");
		txtConfContra.setText("");
		cbxRol.setSelectedIndex(0);
		txtCedulaMedico.setText("");

	}
	
	private void loadUsuarios() {
		if(selected!=null) {
			txtCodigo.setText(selected.getIdUsuario());
			txtNombre.setText(selected.getUsuario());
			txtContrasenia.setText(selected.getContrasenia());
            cbxRol.setSelectedItem(selected.getIdRol());

		}
	}
}
