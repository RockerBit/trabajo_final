import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class NuevoProducto extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textMarca;
	private JTextField textCategoria;
	private JTextField textPrecio;
	private JTextField textCantidad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevoProducto frame = new NuevoProducto();
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
	public NuevoProducto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 337);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textNombre.getText().equals("") || textMarca.getText().equals("") || textCategoria.getText().equals("") || textPrecio.getText().equals("") || textCantidad.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Ninguno de los campos puede estar vacio");
				}else {
					PreparedStatement ps;
					String insertarRegistroQuery = "INSERT INTO producto (nombre,marca,categoria,precio,cantidad) VALUES(?,?,?,?,?)";
					try {
						ps = Conexion.getConnection().prepareStatement(insertarRegistroQuery);
						ps.setString(1, textNombre.getText());
						ps.setString(2, textMarca.getText());
						ps.setString(3, textCategoria.getText());
						ps.setString(4, textPrecio.getText());
						ps.setString(5, textCategoria.getText());
						
						if(ps.executeUpdate() != 0) {
							JOptionPane.showMessageDialog(null, "Producto guardado");
							textNombre.setText("");
							textMarca.setText("");
							textCategoria.setText("");
							textPrecio.setText("");
							textCantidad.setText("");
						}else {
							JOptionPane.showMessageDialog(null, "Hubo un error. Verifica tu informacion.");
						}
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\thero\\eclipse-workspace\\Proyecto_Final\\imagen\\salvar.png"));
		btnNewButton.setBounds(10, 229, 64, 64);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textNombre.setText("");
				textMarca.setText("");
				textCategoria.setText("");
				textPrecio.setText("");
				textCantidad.setText("");
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\thero\\eclipse-workspace\\Proyecto_Final\\imagen\\limpiar.png"));
		btnNewButton_1.setBackground(UIManager.getColor("Button.background"));
		btnNewButton_1.setBounds(110, 229, 64, 64);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("NOMBRE:");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 10, 100, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("MARCA:");
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 48, 92, 27);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("CATEGORIA:");
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 15));
		lblNewLabel_2.setBounds(10, 85, 115, 27);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("PRECIO:");
		lblNewLabel_3.setFont(new Font("Verdana", Font.BOLD, 15));
		lblNewLabel_3.setBounds(10, 121, 100, 27);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("CANTIDAD");
		lblNewLabel_4.setFont(new Font("Verdana", Font.BOLD, 15));
		lblNewLabel_4.setBounds(10, 160, 100, 27);
		contentPane.add(lblNewLabel_4);
		
		textNombre = new JTextField();
		textNombre.setFont(new Font("Verdana", Font.PLAIN, 12));
		textNombre.setBounds(135, 11, 140, 27);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		textMarca = new JTextField();
		textMarca.setFont(new Font("Verdana", Font.PLAIN, 12));
		textMarca.setBounds(135, 48, 140, 27);
		contentPane.add(textMarca);
		textMarca.setColumns(10);
		
		textCategoria = new JTextField();
		textCategoria.setFont(new Font("Verdana", Font.PLAIN, 12));
		textCategoria.setBounds(135, 85, 140, 27);
		contentPane.add(textCategoria);
		textCategoria.setColumns(10);
		
		textPrecio = new JTextField();
		textPrecio.setFont(new Font("Verdana", Font.PLAIN, 12));
		textPrecio.setBounds(135, 122, 140, 27);
		contentPane.add(textPrecio);
		textPrecio.setColumns(10);
		
		textCantidad = new JTextField();
		textCantidad.setFont(new Font("Verdana", Font.PLAIN, 12));
		textCantidad.setBounds(135, 170, 140, 27);
		contentPane.add(textCantidad);
		textCantidad.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("DISPONIBLE:");
		lblNewLabel_5.setFont(new Font("Verdana", Font.BOLD, 15));
		lblNewLabel_5.setBounds(10, 176, 115, 27);
		contentPane.add(lblNewLabel_5);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Producto producto = new Producto();
				producto.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\thero\\eclipse-workspace\\Proyecto_Final\\imagen\\left_arrow.png"));
		btnNewButton_2.setBounds(207, 229, 64, 64);
		contentPane.add(btnNewButton_2);
	}
}
