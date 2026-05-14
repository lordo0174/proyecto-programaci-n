package Ventanas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class ventanaProveedores extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField idAñadirP;
	private JTextField nombreAñadirP;
	private JTextField telefonoAñadirP;
	private JTextField direccionAñadirP;
	private JTextField cpAñadirP;
	private JTextField nombreEditarP;
	private JTextField telefonoEditarP;
	private JTextField direccionEditarP;
	private JTextField cpEditarP;
	
	private JComboBox<String> desEditarP;
	private JComboBox<String> desEliminarP;
	
	private JButton btnConsultarP;
	private JButton btnEditarP;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaProveedores frame = new ventanaProveedores();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ventanaProveedores() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 424);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAñadirS = new JButton("AÑADIR");
		btnAñadirS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				añadirP();
			}
		});
		btnAñadirS.setBounds(653, 86, 71, 31);
		contentPane.add(btnAñadirS);
		
		JLabel lblTitulo = new JLabel("Gestión de proveedores");
		lblTitulo.setBounds(284, 11, 200, 19);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblTitulo);
		
		btnEditarP = new JButton("EDITAR");
		btnEditarP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarP();
			}
		});
		btnEditarP.setBounds(653, 141, 71, 31);
		contentPane.add(btnEditarP);
		
		btnConsultarP = new JButton("CONSULTAR");
		btnConsultarP.setBounds(284, 255, 150, 31);
		contentPane.add(btnConsultarP);
		btnConsultarP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarP();
			}
		});
		
		JButton atras = new JButton("Atrás");
		atras.setBounds(42, 302, 89, 23);
		contentPane.add(atras);
		atras.addActionListener(e -> {
			dispose();
			new ventanaCompradores().setVisible(true);
		});
		
		desEditarP = new JComboBox<String>();
		desEditarP.setBounds(45, 145, 100, 22);
		contentPane.add(desEditarP);
		
		idAñadirP = new JTextField();
		idAñadirP.setBounds(55, 91, 86, 20);
		contentPane.add(idAñadirP);
		
		nombreAñadirP = new JTextField();
		nombreAñadirP.setBounds(176, 91, 86, 20);
		contentPane.add(nombreAñadirP);
		
		telefonoAñadirP = new JTextField();
		telefonoAñadirP.setBounds(290, 91, 86, 20);
		contentPane.add(telefonoAñadirP);
		
		direccionAñadirP = new JTextField();
		direccionAñadirP.setBounds(419, 91, 86, 20);
		contentPane.add(direccionAñadirP);
		
		cpAñadirP = new JTextField();
		cpAñadirP.setBounds(563, 91, 50, 20);
		contentPane.add(cpAñadirP);
		
		nombreEditarP = new JTextField();
		nombreEditarP.setBounds(176, 146, 86, 20);
		contentPane.add(nombreEditarP);
		
		telefonoEditarP = new JTextField();
		telefonoEditarP.setBounds(290, 146, 86, 20);
		contentPane.add(telefonoEditarP);
		
		direccionEditarP = new JTextField();
		direccionEditarP.setBounds(419, 146, 86, 20);
		contentPane.add(direccionEditarP);
		
		cpEditarP = new JTextField();
		cpEditarP.setBounds(563, 146, 50, 20);
		contentPane.add(cpEditarP);
		
		desEliminarP = new JComboBox<String>();
		desEliminarP.setBounds(232, 200, 100, 22);
		contentPane.add(desEliminarP);
		
		addWindowListener(new WindowAdapter()  //Se ejecuta automaticamente cuando se entre en la ventana
			{
				@Override
				public void windowOpened(WindowEvent e)
				{
					añadirDes(desEliminarP);  //Metodos que queremos que se ejecuten
					añadirDes(desEditarP);
				}
		});

		JButton btnEliminarP = new JButton("ELIMINAR");
		btnEliminarP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarItem(desEliminarP, desEditarP);
			}
		});
		btnEliminarP.setBounds(372, 196, 150, 31);
		contentPane.add(btnEliminarP);
		
		JLabel lblId = new JLabel("ID Proveedor");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblId.setBounds(45, 62, 100, 19);
		contentPane.add(lblId);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNombre.setBounds(194, 61, 52, 19);
		contentPane.add(lblNombre);
		
		JLabel lblTlf = new JLabel("Teléfono");
		lblTlf.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTlf.setBounds(300, 61, 60, 19);
		contentPane.add(lblTlf);
		
		JLabel lblDir = new JLabel("Dirección");
		lblDir.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDir.setBounds(430, 62, 70, 19);
		contentPane.add(lblDir);
		
		JLabel lblCp = new JLabel("CP");
		lblCp.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCp.setBounds(575, 62, 30, 19);
		contentPane.add(lblCp);
	}
	
	public void añadirDes(JComboBox desEditarP)
	{
		try
		{
			ventanaPrincipal.ConexionPrincipal.conectar();
			String query = "SELECT ID_Supplier FROM supplier ORDER BY ID_Supplier ASC";
			ResultSet resultado = ventanaPrincipal.ConexionPrincipal.ejecutarSelect(query);
			desEditarP.removeAllItems();
			
			desEditarP.addItem("Seleccionar"); //Aparece Seleccionar por defecto en el desplegable
			
			while (resultado.next())
			{
				//Usamos addItem para añadir el ID al desplegable
				desEditarP.addItem(resultado.getString("ID_Supplier"));
			}
			
			desEditarP.setSelectedIndex(0); //Selecciona un item vacío
			
			ventanaPrincipal.ConexionPrincipal.desconectar();
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void eliminarItem(JComboBox desEliminarP, JComboBox desEditarP)
	{
		String idParaBorrar = (String) desEliminarP.getSelectedItem();
		if (idParaBorrar == null || idParaBorrar.equals("Seleccionar"))
		{
			JOptionPane.showMessageDialog(this, "Selecciona un proveedor.");
			return;
		}
		try
		{
			ventanaPrincipal.ConexionPrincipal.conectar();
			String query = "DELETE FROM supplier WHERE ID_Supplier = " + idParaBorrar;
			ventanaPrincipal.ConexionPrincipal.ejecutarInsertDeleteUpdate(query);
			ventanaPrincipal.ConexionPrincipal.desconectar();
			
			añadirDes(desEliminarP);  //Refrescamos los desplegables al eliminar el item
			añadirDes(desEditarP);
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void editarP()		//Metodo para editar atributos de un proveedor
	{
		String idSeleccionado = (String) desEditarP.getSelectedItem();
		
		if (idSeleccionado == null || idSeleccionado.equals("Seleccionar"))
		{
			JOptionPane.showMessageDialog(this, "Selecciona un proveedor.");
			return;
		}
		try
		{
			ventanaPrincipal.ConexionPrincipal.conectar();
			
			String queryBusqueda = "SELECT Name, Phone_number, Address, Postcode FROM supplier WHERE ID_Supplier = " + idSeleccionado;
			ResultSet rs = ventanaPrincipal.ConexionPrincipal.ejecutarSelect(queryBusqueda);
			if (rs.next()) 
			{
				String nombreProveedor = nombreEditarP.getText().trim().isEmpty() ? rs.getString("Name") : nombreEditarP.getText();
				String tlfProveedor = telefonoEditarP.getText().trim().isEmpty() ? rs.getString("Phone_number") : telefonoEditarP.getText();
				String dirProveedor = direccionEditarP.getText().trim().isEmpty() ? rs.getString("Address") : direccionEditarP.getText();
				String cpProveedor = cpEditarP.getText().trim().isEmpty() ? rs.getString("Postcode") : cpEditarP.getText();

				String queryUpdate = "UPDATE supplier SET Name='" + nombreProveedor + "', Phone_number=" + tlfProveedor + ", Address='" + dirProveedor + "', Postcode=" + cpProveedor + " WHERE ID_Supplier=" + idSeleccionado;
				
				if (ventanaPrincipal.ConexionPrincipal.ejecutarInsertDeleteUpdate(queryUpdate) > 0)
				{
					JOptionPane.showMessageDialog(this, "Datos cambiados.");
				}
			}
			ventanaPrincipal.ConexionPrincipal.desconectar();
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void añadirP()
	{
		String idProveedor = idAñadirP.getText().trim();
		String nombreProveedor = nombreAñadirP.getText().trim();
		String tlfProveedor = telefonoAñadirP.getText().trim();
		String dirProveedor = direccionAñadirP.getText().trim();
		String cpProveedor = cpAñadirP.getText().trim();
		
		//Si un campo está vacío no se ejecuta
		if (idProveedor.isEmpty() || nombreProveedor.isEmpty() || tlfProveedor.isEmpty() || dirProveedor.isEmpty() || cpProveedor.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Rellene todos los campos.");
			return;
		}
		try
		{
			ventanaPrincipal.ConexionPrincipal.conectar();
			
			String query = "INSERT INTO supplier (ID_Supplier, Name, Phone_number, Address, Postcode) VALUES (" + idProveedor + ", '" + nombreProveedor + "', " + tlfProveedor + ", '" + dirProveedor + "', " + cpProveedor + ")";
			
			if (ventanaPrincipal.ConexionPrincipal.ejecutarInsertDeleteUpdate(query) > 0)
			{
				JOptionPane.showMessageDialog(this, "Proveedor añadido correctamente.");
				
				añadirDes(desEditarP);
				añadirDes(desEliminarP);
			}
			ventanaPrincipal.ConexionPrincipal.desconectar();
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void consultarP()
	{
		JFrame consultarTabla = new JFrame("Consulta de proveedores"); //Creamos la tabla dentro de la misma clase
		consultarTabla.setBounds(100, 100, 700, 400);
		consultarTabla.setLocationRelativeTo(null); 	//Centramos la ventana
		consultarTabla.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   //Si se cierra esta ventana no afecta a las demás ventanas abiertas
		
		String[] columnasTabla = {"ID Supplier", "Nombre", "Teléfono", "Dirección", "CP"};  //Definimos la cabecera de la tabla (columnas)
		DefaultTableModel modeloTabla = new DefaultTableModel(null, columnasTabla);  //Creamos el modelo de la tabla
		
		try
		{
			ventanaPrincipal.ConexionPrincipal.conectar();
			String query = "SELECT * FROM supplier";
			ResultSet resultado = ventanaPrincipal.ConexionPrincipal.ejecutarSelect(query);
			
			while (resultado.next())
			{
				Object[] filas = new Object[5];			//Creamos un array para representar las filas de la tabla
				filas[0] = resultado.getString("ID_Supplier");
				filas[1] = resultado.getString("Name");
				filas[2] = resultado.getString("Phone_number");
				filas[3] = resultado.getString("Address");
				filas[4] = resultado.getString("Postcode");
				
				modeloTabla.addRow(filas); //Se añaden las filas al modelo de la tabla
			}
			ventanaPrincipal.ConexionPrincipal.desconectar();
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		
		JTable tabla = new JTable(modeloTabla);  //Creamos la tabla con todos los datos de la BBDD
		consultarTabla.add(new JScrollPane(tabla));		//Añadimos la tabla a un JSrollPane para poder scrollear
		consultarTabla.setVisible(true);		//Hacemos la tabla visible
	}
}