package visual.usuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

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
import logico.Persona;
import logico.Rol;
import logico.Usuario;
import visual.enfermedad.SeleccionarEnfermedad;

public class RegistroUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsuario;
	private JTextField txtContrasenia;
	private JTextField txtConfContra;
	private Usuario selected;
	private JComboBox cbxRol;
	private JTextField txtCodigo;
	private JTextField txtIDPersona;
	private JTextField txtNombre;
	private Persona persona;
	private JButton btnRegistrar;
	
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
		
		
		setBounds(100, 100, 545, 266);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 509, 176);
		contentPanel.add(panel);
		panel.setLayout(null);
		{
			JLabel lblNombre = new JLabel("Usuario:");
			lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNombre.setBounds(249, 23, 113, 14);
			panel.add(lblNombre);
		}
		{ 
			txtUsuario = new JTextField();
			txtUsuario.setColumns(10);
			txtUsuario.setBounds(372, 20, 127, 20);
			panel.add(txtUsuario);
		}
		{
			JLabel lblContrasenia = new JLabel("Contrase\u00F1a:");
			lblContrasenia.setHorizontalAlignment(SwingConstants.RIGHT);
			lblContrasenia.setBounds(7, 64, 68, 14);
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
			lblConfContra.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblConfContra.setHorizontalAlignment(SwingConstants.RIGHT);
			lblConfContra.setBounds(225, 61, 141, 14);
			panel.add(lblConfContra);
		}
		{
			txtConfContra = new JTextField();
			txtConfContra.setColumns(10);
			txtConfContra.setBounds(372, 58, 127, 20);
			panel.add(txtConfContra);
		}
		{
			JLabel lblRol = new JLabel("Rol:");
			lblRol.setHorizontalAlignment(SwingConstants.RIGHT);
			lblRol.setBounds(21, 105, 54, 14);
			panel.add(lblRol);
		}
		{
			cbxRol = new JComboBox();
			cbxRol.setBounds(85, 102, 127, 20);
			panel.add(cbxRol);
		}
		cargarRolesEnComboBox();

		{
			JLabel lblCodigo = new JLabel("C\u00F3digo:");
			lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
			lblCodigo.setBounds(21, 20, 54, 14);
			panel.add(lblCodigo);
		}
		{
			txtCodigo = new JTextField();
			txtCodigo.setEnabled(false);
			txtCodigo.setColumns(10);
			txtCodigo.setBounds(85, 17, 127, 20);
			panel.add(txtCodigo);
			txtCodigo.setText(ClinicaMedica.getInstance().generarNuevoCodigoUsuario());

		}
		
		JButton btnAsociar = new JButton("Asociar el usuario a una persona");
		btnAsociar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SeleccionarPersona sp = new SeleccionarPersona();
				sp.setModal(true);
				sp.setVisible(true);
				persona = sp.getSelectedPersona();
				if(persona != null) {
					txtIDPersona.setText(persona.getIdPersona());
					txtNombre.setText(persona.getNombre()+' '+persona.getApellido());
					btnRegistrar.setEnabled(true);
				}
				
			}
		});
		btnAsociar.setBounds(249, 101, 250, 23);
		panel.add(btnAsociar);
		
		JLabel lblNewLabel = new JLabel("ID Persona:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(7, 145, 68, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(249, 145, 113, 14);
		panel.add(lblNewLabel_1);
		
		txtIDPersona = new JTextField();
		txtIDPersona.setEditable(false);
		txtIDPersona.setBounds(85, 142, 127, 20);
		panel.add(txtIDPersona);
		txtIDPersona.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setBounds(372, 142, 127, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(null);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegistrar = new JButton("Registrar");
				btnRegistrar.setEnabled(false);
				if(selected != null) {
					btnRegistrar.setText("Modificar");
					txtUsuario.setEditable(false);;
					btnAsociar.setEnabled(false);
				}
				btnRegistrar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(selected == null) {
							String codigo = txtCodigo.getText();
							String usuario = txtUsuario.getText();
							String contrasena = txtContrasenia.getText();
							String confContra = txtConfContra.getText();
							Rol rolSeleccionado = (Rol) cbxRol.getSelectedItem();
							int idRol = rolSeleccionado.getIdRol();
							String idPersona = persona.getIdPersona();
							
							if(!(contrasena.equals(confContra))) {
					            JOptionPane.showMessageDialog(null, "Las contraseñas no son iguales", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
					            	return;
							}
							
							Usuario user = new Usuario(codigo,usuario,confContra,idRol,idPersona);
							
							ClinicaMedica.getInstance().regUser(user);
							JOptionPane.showMessageDialog(null,"Operacion exitosa","Informacion",JOptionPane.INFORMATION_MESSAGE);
							clean();
						} else {
							
							String contrasena = txtContrasenia.getText();
							String confContra = txtConfContra.getText();
							if(!(contrasena.equals(confContra))) {
					            JOptionPane.showMessageDialog(null, "Las contraseñas no son iguales", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
					            	return;
							}
							selected.setContrasenia(contrasena);
						    Rol rolSeleccionado = (Rol) cbxRol.getSelectedItem();
						    int idRol = rolSeleccionado.getIdRol();
						    selected.setIdRol(idRol);
						    ClinicaMedica.getInstance().updateUsuario(selected);
						    ListadoUsuarios.loadUsuarios();
						    JOptionPane.showMessageDialog(null, "Operacion exitosa", "Informacion", JOptionPane.INFORMATION_MESSAGE);
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
		txtCodigo.setText(ClinicaMedica.getInstance().generarNuevoCodigoUsuario());
		txtUsuario.setText("");
		txtContrasenia.setText("");
		txtConfContra.setText("");
		cbxRol.setSelectedIndex(0);
		txtIDPersona.setText("");
		txtNombre.setText("");

	}
	
	private void loadUsuarios() {
		if(selected!=null) {
			btnRegistrar.setEnabled(true);
			txtCodigo.setText(selected.getIdUsuario());
			txtUsuario.setText(selected.getUsuario());
			txtContrasenia.setText(selected.getContrasenia());
			txtConfContra.setText(selected.getContrasenia());
            cbxRol.setSelectedItem(selected.getIdRol());
            
            int idRolUsuario = selected.getIdRol();
            for (int i = 0; i < cbxRol.getItemCount(); i++) {
                Rol rol = (Rol) cbxRol.getItemAt(i);
                if (rol.getIdRol() == idRolUsuario) {
                    cbxRol.setSelectedIndex(i);
                    break;
                }
            }
            
            String idPersona = selected.getIdPersona();
            persona = ClinicaMedica.getInstance().buscarPersonabyCodigo(idPersona);

            if (persona != null) {
                txtIDPersona.setText(persona.getIdPersona()); // o lo que necesites mostrar
                txtNombre.setText(persona.getNombre()+' '+persona.getApellido()); // o lo que necesites mostrar
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la persona asociada.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
		}
	}
	
	private void cargarRolesEnComboBox() {
		ArrayList<Rol> roles = ClinicaMedica.getInstance().obtenerRoles();

	    cbxRol.removeAllItems();

	    for (Rol rol : roles) {
	        cbxRol.addItem(rol);
	    }
	}
}
