import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;

public class Registro extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textUsuario;
	private JTextField textCorreo;
	private JTextField textTelefono;
	private JPasswordField passContrasena1;
	private JPasswordField passContrasena2;

	public boolean verifyFields() {
		String nombre = textNombre.getText();
		String apellido = textApellido.getText();
		String usuario = textUsuario.getText();
		String correo = textCorreo.getText();
		String telefono = textTelefono.getText();
		String contrasena1 = String.valueOf(passContrasena1.getPassword());
		String contrasena2 = String.valueOf(passContrasena2.getPassword());
		
		if(nombre.trim().equals("") || apellido.trim().equals("") || usuario.trim().equals("") || correo.trim().equals("") || telefono.trim().equals("") || contrasena1.trim().equals("") || contrasena2.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Ninguno de los campos puede estar vacio");
			return false;
		}
		else if(contrasena1.equals(contrasena2)) {
			return true;
		}
		else {
			JOptionPane.showMessageDialog(null, "Contrasena no coincide");
			return false;
		}
	}
	
	public boolean checkUsuario(String usuario) {
		PreparedStatement st;
		ResultSet rs;
		boolean usuario_existe = false;
		String query = "SELECT * FROM usuarios WHERE usuario = ?";
		try {
			st = Conexion.getConnection().prepareStatement(query);
			st.setString(1, usuario);
			rs = st.executeQuery();
			
			if(rs.next()) {
				usuario_existe = true;
				JOptionPane.showMessageDialog(null, "Este usuario ya existe. Intenta con otro.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario_existe;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro frame = new Registro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Registro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 328, 457);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("NOMBRE:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel.setBounds(26, 97, 99, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("APELLIDO:");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1.setBounds(26, 136, 99, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("USUARIO:");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_2.setBounds(26, 175, 99, 29);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("CORREO:");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_3.setBounds(26, 214, 99, 29);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("TELEFONO:");
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_4.setBounds(26, 253, 99, 29);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("CONTRASE\u00D1A:");
		lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_5.setBounds(26, 292, 112, 29);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("REPETIR");
		lblNewLabel_6.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_6.setBounds(26, 325, 99, 22);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("REGISTRATE");
		lblNewLabel_7.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel_7.setBounds(26, 26, 164, 41);
		contentPane.add(lblNewLabel_7);
		
		JButton btnNewButton = new JButton("Registrate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = textNombre.getText();
				String apellido = textApellido.getText();
				String usuario = textUsuario.getText();
				String correo = textCorreo.getText();
				String telefono = textTelefono.getText();
				String contrasena1 = String.valueOf(passContrasena1.getPassword());
				
				if(verifyFields()) {
					if(!checkUsuario(usuario)) {
						PreparedStatement ps;
						String insertarRegistroQuery = "INSERT INTO usuarios (nombre,apellido,usuario,correo_electronico,numero_telefono,contrasena) VALUES(?,?,?,?,?,?)";
						try {
							ps = Conexion.getConnection().prepareStatement(insertarRegistroQuery);
							ps.setString(1, nombre);
							ps.setString(2, apellido);
							ps.setString(3, usuario);
							ps.setString(4, correo);
							ps.setString(5, telefono);
							ps.setString(6, contrasena1);
							
							if(ps.executeUpdate() != 0) {
								JOptionPane.showMessageDialog(null, "Tu usuario fue creado");
								Login login = new Login();
								login.setVisible(true);
								login.setLocationRelativeTo(null);
								dispose();
							}else {
								JOptionPane.showMessageDialog(null, "Hubo un error. Verifica tu informacion.");
							}
							
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
					
					}
				}
			}
		});
		btnNewButton.setBackground(UIManager.getColor("Button.background"));
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 12));
		btnNewButton.setBounds(122, 385, 112, 29);
		contentPane.add(btnNewButton);
		
		textNombre = new JTextField();
		textNombre.setFont(new Font("Arial", Font.PLAIN, 12));
		textNombre.setBounds(151, 97, 150, 22);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		textApellido = new JTextField();
		textApellido.setFont(new Font("Arial", Font.PLAIN, 12));
		textApellido.setBounds(151, 136, 150, 22);
		contentPane.add(textApellido);
		textApellido.setColumns(10);
		
		textUsuario = new JTextField();
		textUsuario.setFont(new Font("Arial", Font.PLAIN, 12));
		textUsuario.setBounds(151, 175, 150, 22);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);
		
		textCorreo = new JTextField();
		textCorreo.setFont(new Font("Arial", Font.PLAIN, 12));
		textCorreo.setBounds(151, 214, 150, 22);
		contentPane.add(textCorreo);
		textCorreo.setColumns(10);
		
		textTelefono = new JTextField();
		textTelefono.setFont(new Font("Arial", Font.PLAIN, 12));
		textTelefono.setBounds(151, 253, 150, 22);
		contentPane.add(textTelefono);
		textTelefono.setColumns(10);
		
		passContrasena1 = new JPasswordField();
		passContrasena1.setBounds(151, 293, 150, 22);
		contentPane.add(passContrasena1);
		
		passContrasena2 = new JPasswordField();
		passContrasena2.setBounds(151, 334, 150, 22);
		contentPane.add(passContrasena2);
		
		JLabel lblNewLabel_8 = new JLabel("CONTRASENA:");
		lblNewLabel_8.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_8.setBounds(26, 340, 127, 22);
		contentPane.add(lblNewLabel_8);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login ventana = new Login();
				ventana.setVisible(true);
				ventana.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnNewButton_1.setBackground(UIManager.getColor("Button.background"));
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\thero\\eclipse-workspace\\Proyecto_Final\\imagen\\left_arrow.png"));
		btnNewButton_1.setBounds(233, 17, 68, 50);
		contentPane.add(btnNewButton_1);
	}
}
