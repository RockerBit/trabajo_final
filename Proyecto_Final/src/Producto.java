import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.FlowLayout;

public class Producto extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textNombre;
	private JTextField textMarca;
	private JTextField textCategoria;
	private JTextField textPrecio;
	private JTextField textCantidadDisponible;
	DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Producto frame = new Producto();
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
	
	public Producto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 938, 466);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblProducto = new JLabel("PRODUCTOS");
		lblProducto.setBounds(79, 23, 167, 30);
		lblProducto.setForeground(new Color(0, 0, 0));
		lblProducto.setBackground(Color.WHITE);
		lblProducto.setFont(new Font("Verdana", Font.BOLD, 24));
		contentPane.add(lblProducto);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(323, 84, 591, 334);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		scrollPane.setColumnHeaderView(table);
		table.setBackground(SystemColor.info);
		
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setFont(new Font("Verdana", Font.PLAIN, 15));
		table.setBackground(SystemColor.info);
		model=new DefaultTableModel();
		Object[] column = {"Nombre","Marca", "Categoria", "Precio", "Cantidad Disponible"};
		final Object[] row = new Object[5];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JButton btnNuevoP = new JButton("");
		btnNuevoP.setBounds(10, 262, 68, 73);
		btnNuevoP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NuevoProducto producto = new NuevoProducto();
				producto.setVisible(true);
				dispose();
			}
		});
		
		btnNuevoP.setBackground(UIManager.getColor("Button.background"));
		btnNuevoP.setIcon(new ImageIcon("C:\\Users\\thero\\eclipse-workspace\\Proyecto_Final\\imagen\\add-file.png"));
		contentPane.add(btnNuevoP);
		
		JButton btnActualizarP = new JButton("");
		btnActualizarP.setBounds(88, 262, 68, 73);
		btnActualizarP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila, 0).toString());
				String nombre = table.getValueAt(fila, 1).toString();
				String marca = table.getValueAt(fila, 2).toString();
				String categoria = table.getValueAt(fila, 3).toString();
				String precio = table.getValueAt(fila, 4).toString();
				String cantidad = table.getValueAt(fila, 5).toString();
				
				String query = "UPDATE producto SET nombre='"+nombre+"', marca='"+marca+"', categoria='"+categoria+"', precio='"+precio+"', cantidad='"+cantidad+"' WHERE id='"+id+"'";
						
						try {
							PreparedStatement actu = Conexion.getConnection().prepareStatement(query);
							actu.executeUpdate();
							
							JOptionPane.showMessageDialog(null, "Producto actualizado");
						} catch (Exception e3) {
							e3.printStackTrace();
						}
			}
		});
		btnActualizarP.setIcon(new ImageIcon("C:\\Users\\thero\\eclipse-workspace\\Proyecto_Final\\imagen\\refresh.png"));
		btnActualizarP.setBackground(UIManager.getColor("Button.background"));
		contentPane.add(btnActualizarP);
		
		JButton btnEliminarP = new JButton("");
		btnEliminarP.setBounds(166, 262, 68, 73);
		btnEliminarP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Fila = table.getSelectedRow();
				String Valor = table.getValueAt(Fila, 0).toString();
				String insertarRegistroQuery = "DELETE FROM Producto WHERE Id='"+Valor+"'";
				
				try {
					PreparedStatement ps = Conexion.getConnection().prepareStatement(insertarRegistroQuery);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Registro borrado");
				}catch(Exception e4) {
					e4.printStackTrace();
				}
				String sql = "SELECT * FROM producto";
				Statement st; 
				DefaultTableModel model = new DefaultTableModel();
				table.setModel(model);
				
				model.addColumn("ID");
				model.addColumn("Nombre");
				model.addColumn("Marca");
				model.addColumn("Categoria");
				model.addColumn("Precio");
				model.addColumn("Cantidad Disponible");
				
				String[] dato = new String[6];
				
				try {
					st = Conexion.getConnection().prepareStatement(sql);
					ResultSet rs = st.executeQuery(sql);
					
					while(rs.next()) {
						dato[0]=rs.getString(1);
						dato[1]=rs.getString(2);
						dato[2]=rs.getString(3);
						dato[3]=rs.getString(4);
						dato[4]=rs.getString(5);
						dato[5]=rs.getString(6);
						model.addRow(dato);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		btnEliminarP.setBackground(UIManager.getColor("Button.background"));
		btnEliminarP.setIcon(new ImageIcon("C:\\Users\\thero\\eclipse-workspace\\Proyecto_Final\\imagen\\delete.png"));
		contentPane.add(btnEliminarP);
		
		JButton btnRegresar = new JButton("");
		btnRegresar.setBounds(128, 345, 68, 73);
		btnRegresar.setIcon(new ImageIcon("C:\\Users\\thero\\eclipse-workspace\\Proyecto_Final\\imagen\\left_arrow.png"));
		btnRegresar.setBackground(UIManager.getColor("Button.background"));
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inicio inicio = new Inicio();
				inicio.setVisible(true);
				inicio.setLocationRelativeTo(null);
				dispose();
			}
		});
		contentPane.add(btnRegresar);
		
		JButton btnCerrarSesion = new JButton("");
		btnCerrarSesion.setBounds(536, 10, 64, 64);
		btnCerrarSesion.setBackground(UIManager.getColor("Button.background"));
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				login.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnCerrarSesion.setIcon(new ImageIcon("C:\\Users\\thero\\eclipse-workspace\\Proyecto_Final\\imagen\\power.png"));
		contentPane.add(btnCerrarSesion);
		
		textNombre = new JTextField();
		textNombre.setBounds(166, 82, 122, 26);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		textMarca = new JTextField();
		textMarca.setBounds(166, 118, 122, 26);
		contentPane.add(textMarca);
		textMarca.setColumns(10);
		
		textCategoria = new JTextField();
		textCategoria.setBounds(166, 154, 122, 26);
		contentPane.add(textCategoria);
		textCategoria.setColumns(10);
		
		textPrecio = new JTextField();
		textPrecio.setBounds(166, 190, 122, 26);
		contentPane.add(textPrecio);
		textPrecio.setColumns(10);
		
		textCantidadDisponible = new JTextField();
		textCantidadDisponible.setFont(new Font("Verdana", Font.PLAIN, 10));
		textCantidadDisponible.setBounds(166, 226, 122, 26);
		contentPane.add(textCantidadDisponible);
		textCantidadDisponible.setColumns(10);
		
		JButton btnLimpiar = new JButton("");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textNombre.setText("");
				textMarca.setText("");
				textCategoria.setText("");
				textPrecio.setText("");
				textCantidadDisponible.setText("");
			}
		});
		btnLimpiar.setBounds(245, 262, 68, 73);
		btnLimpiar.setIcon(new ImageIcon("C:\\Users\\thero\\eclipse-workspace\\Proyecto_Final\\imagen\\limpiar.png"));
		contentPane.add(btnLimpiar);
		
		JLabel lblNombre = new JLabel("NOMBRE:");
		lblNombre.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNombre.setBounds(77, 88, 68, 20);
		contentPane.add(lblNombre);
		
		JLabel lblMarca = new JLabel("MARCA:");
		lblMarca.setFont(new Font("Verdana", Font.BOLD, 12));
		lblMarca.setBounds(87, 124, 54, 20);
		contentPane.add(lblMarca);
		
		JLabel lblCategoria = new JLabel("CATEGORIA:");
		lblCategoria.setFont(new Font("Verdana", Font.BOLD, 12));
		lblCategoria.setBounds(55, 160, 86, 20);
		contentPane.add(lblCategoria);
		
		JLabel lblPrecio = new JLabel("PRECIO:");
		lblPrecio.setFont(new Font("Verdana", Font.BOLD, 12));
		lblPrecio.setBounds(77, 196, 64, 20);
		contentPane.add(lblPrecio);
		
		JLabel lblCantDisponible = new JLabel("CANT. DISPONIBLE:");
		lblCantDisponible.setFont(new Font("Verdana", Font.BOLD, 12));
		lblCantDisponible.setBounds(10, 232, 167, 20);
		contentPane.add(lblCantDisponible);
		
		String sql = "SELECT * FROM producto";
		Statement st; 
		DefaultTableModel model = new DefaultTableModel();
		table.setModel(model);
		
		model.addColumn("ID");
		model.addColumn("Nombre");
		model.addColumn("Marca");
		model.addColumn("Categoria");
		model.addColumn("Precio");
		model.addColumn("Cantidad Disponible");
		
		String[] dato = new String[6];
		
		try {
			st = Conexion.getConnection().prepareStatement(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				dato[0]=rs.getString(1);
				dato[1]=rs.getString(2);
				dato[2]=rs.getString(3);
				dato[3]=rs.getString(4);
				dato[4]=rs.getString(5);
				dato[5]=rs.getString(6);
				model.addRow(dato);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
