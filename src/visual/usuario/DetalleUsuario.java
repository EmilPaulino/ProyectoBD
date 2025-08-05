package visual.usuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.ClinicaMedica;
import logico.Medico;
import logico.Persona;
import logico.Usuario;

public class DetalleUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtUsuario;
	Usuario selected = null;
	private JTextField txtRol;
	private JTextField txtPersona;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DetalleUsuario dialog = new DetalleUsuario(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DetalleUsuario(Usuario aux) {
		selected = aux;
		
		setTitle("Detalle usuario");
		setBounds(100, 100, 558, 194);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 515, 169);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("C\u00F3digo:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(20, 35, 57, 14);
		panel.add(label);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setText("U-1");
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(84, 32, 409, 20);
		panel.add(txtCodigo);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setBounds(0, 66, 77, 14);
		panel.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setEditable(false);
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(84, 63, 102, 20);
		panel.add(txtUsuario);
		
		JLabel label_4 = new JLabel("Rol:");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(196, 66, 30, 14);
		panel.add(label_4);
		
		JLabel lblIdPersona = new JLabel("ID Persona:");
		lblIdPersona.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdPersona.setBounds(329, 66, 65, 14);
		panel.add(lblIdPersona);
		
		txtRol = new JTextField();
		txtRol.setEditable(false);
		txtRol.setBounds(233, 63, 92, 20);
		panel.add(txtRol);
		txtRol.setColumns(10);
		
		txtPersona = new JTextField();
		txtPersona.setEditable(false);
		txtPersona.setBounds(401, 63, 92, 20);
		panel.add(txtPersona);
		txtPersona.setColumns(10);
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
		loadUsuario();
		
	}

	private void loadUsuario() {
		if(selected != null) {
			String Cargo = null;
			if(selected.getIdRol() == 1) {
				Cargo = "Administrador";
			}
			else if(selected.getIdRol() == 2) {
				Cargo = "Medico";
			}
			else{
				Cargo = "Administrativo";
			}
			
			txtCodigo.setText(selected.getIdUsuario());
			txtUsuario.setText(selected.getUsuario());
			txtRol.setText(Cargo);
			txtPersona.setText(selected.getIdPersona());
			/*if("Médico".equalsIgnoreCase(selected.getRol())){
				txtCedula.setText(selected.getMedicoRelacionado().getCedula());
			}*/
			/*if (selected.getIdRol() == 2) {
			    if (selected.getMedicoRelacionado() != null) {
			        txtCedula.setText(selected.getMedicoRelacionado().getCedula());
			    } else {
			        JOptionPane.showMessageDialog(null, "El médico relacionado no se encuentra", "Error", JOptionPane.ERROR_MESSAGE);
			    }
		}*/
		
	}
   }
}
