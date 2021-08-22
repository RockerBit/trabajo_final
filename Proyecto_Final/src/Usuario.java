import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.SystemColor;
import javax.swing.table.DefaultTableModel;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JScrollPane;

public class Usuario extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuario frame = new Usuario();
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
	public Usuario() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 860, 622);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JButton btnRegresar = new JButton("");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inicio inicio = new Inicio();
				inicio.setVisible(true);
				inicio.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnRegresar.setBackground(UIManager.getColor("Button.background"));
		btnRegresar.setIcon(new ImageIcon("C:\\Users\\thero\\eclipse-workspace\\Proyecto_Final\\imagen\\left_arrow.png"));
		btnRegresar.setBounds(761, 514, 75, 55);
		contentPane.add(btnRegresar);
		
		JButton btnCerrarSesion = new JButton("");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				login.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnCerrarSesion.setIcon(new ImageIcon("C:\\Users\\thero\\eclipse-workspace\\Proyecto_Final\\imagen\\power.png"));
		btnCerrarSesion.setBounds(772, 10, 64, 64);
		contentPane.add(btnCerrarSesion);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 93, 826, 402);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		scrollPane.setColumnHeaderView(table);
		table.setBackground(SystemColor.info);
		
		
		
		JLabel lblUsuario = new JLabel("USUARIOS");
		lblUsuario.setFont(new Font("Verdana", Font.BOLD, 30));
		lblUsuario.setBounds(10, 10, 183, 64);
		contentPane.add(lblUsuario);
		
		String sql = "SELECT * FROM USUARIOS";
		Statement st; 
		DefaultTableModel model = new DefaultTableModel();
		table.setModel(model);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NuevoUsuario usuario = new NuevoUsuario();
				usuario.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBackground(UIManager.getColor("Button.background"));
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\thero\\eclipse-workspace\\Proyecto_Final\\imagen\\add-file.png"));
		btnNewButton.setBounds(401, 514, 64, 64);
		contentPane.add(btnNewButton);
		
		model.addColumn("Nombre");
		model.addColumn("Marca");
		model.addColumn("Categoria");
		model.addColumn("Precio");
		model.addColumn("Cantidad Disponible");
		
		String[] dato = new String[5];
		
		try {
			st = Conexion.getConnection().prepareStatement(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				dato[0]=rs.getString(1);
				dato[1]=rs.getString(2);
				dato[2]=rs.getString(3);
				dato[3]=rs.getString(4);
				dato[4]=rs.getString(5);
				model.addRow(dato);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}
}
