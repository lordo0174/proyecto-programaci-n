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
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

public class ventanaRealizarCompra extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	// Constructores de los componentes del JFrame
	private JComboBox<String> desProducto;
	private JComboBox<String> desProveedor;
	private JTextField coste;
	private JDateChooser elegirFecha;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaRealizarCompra frame = new ventanaRealizarCompra();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ventanaRealizarCompra() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 424);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Realizar compra de productos");
		lblTitulo.setBounds(234, 21, 300, 19);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblTitulo);
		
		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProducto.setBounds(75, 75, 100, 19);
		contentPane.add(lblProducto);
		
		desProducto = new JComboBox<String>();
		desProducto.setBounds(75, 105, 130, 25);
		contentPane.add(desProducto);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProveedor.setBounds(240, 75, 100, 19);
		contentPane.add(lblProveedor);
		
		desProveedor = new JComboBox<String>();
		desProveedor.setBounds(240, 105, 130, 25);
		contentPane.add(desProveedor);
		
		JLabel lblCoste = new JLabel("Coste");
		lblCoste.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCoste.setBounds(410, 75, 100, 19);
		contentPane.add(lblCoste);
		
		coste = new JTextField();
		coste.setBounds(410, 105, 130, 25);
		contentPane.add(coste);
		coste.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha de compra");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFecha.setBounds(580, 75, 130, 19);
		contentPane.add(lblFecha);
		
		elegirFecha = new JDateChooser();
		elegirFecha.setBounds(580, 105, 130, 25);
		contentPane.add(elegirFecha);
		
		JButton btnAñadir = new JButton("AÑADIR");
		btnAñadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				añadirCompra();
			}
		});
		btnAñadir.setBounds(580, 250, 110, 31);
		contentPane.add(btnAñadir);
		
		JButton atras = new JButton("Atrás"); //Botón para ir hacia la ventana anterior
		atras.setBounds(42, 302, 89, 23);
		contentPane.add(atras);
		atras.addActionListener(e -> {
			dispose();
			new ventanaCompradores().setVisible(true);
		});
		
		addWindowListener(new WindowAdapter() //Es para que se ejecute cuando se abra la ventana automaticamente
			{
				@Override
				public void windowOpened(WindowEvent e)
				{
					cargarProductos(); //Es el metodo que queremos que se ejecute
					cargarProveedores();
				}
			});
	}
	
	// Método para extraer el ID de la cadena combinada "ID - Nombre"
	public String obtenerIdSeleccionado(String itemSeleccionado)
	{
		if (itemSeleccionado == null || itemSeleccionado.equals("Seleccionar"))
		{
			return null;
		}
		String[] separado = itemSeleccionado.split(" - ");   //Divide el item con "-"
		return separado[0]; 	//Selecciona el primer item del array (1º ID)
	}
	
	public void cargarProductos() //Método para añadir los productos al desplegable.
	{
		try
		{
			ventanaPrincipal.ConexionPrincipal.conectar();
			String query = "SELECT ID_Merchandise, Name FROM merchandise ORDER BY ID_Merchandise ASC"; 
			ResultSet resultado = ventanaPrincipal.ConexionPrincipal.ejecutarSelect(query);
			desProducto.removeAllItems();		//Mostramos todos los productos en el desplegable
			
			desProducto.addItem("Seleccionar");

			while (resultado.next())
			{
				String Idnombre = resultado.getString("ID_Merchandise") + " - " + resultado.getString("Name");   //Muestra el ID y el nombre separado por "-"
				desProducto.addItem(Idnombre);
			}
	        
			desProducto.setSelectedIndex(0); 
	        
			ventanaPrincipal.ConexionPrincipal.desconectar();
			desProducto.revalidate();     //Limpiamos el campo Productos
			desProducto.repaint();
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void cargarProveedores() //Método para añadir los proveedores al desplegable.
	{
		try
		{
			ventanaPrincipal.ConexionPrincipal.conectar();
			String query = "SELECT ID_Supplier, Name FROM supplier ORDER BY ID_Supplier ASC"; 
			ResultSet resultado = ventanaPrincipal.ConexionPrincipal.ejecutarSelect(query);
			desProveedor.removeAllItems();		//Mostramos los proveedores en el desplegable
			
			desProveedor.addItem("Seleccionar");

			while (resultado.next())
			{
				String Idnombre = resultado.getString("ID_Supplier") + " - " + resultado.getString("Name");		//Muestra el ID y el nombre separado por "-"
				desProveedor.addItem(Idnombre);
			}
	        
			desProveedor.setSelectedIndex(0); 
	        
			ventanaPrincipal.ConexionPrincipal.desconectar();
			
			desProveedor.revalidate();   //Limpiamos el campo Proveedores
			desProveedor.repaint();
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void añadirCompra()
	{
		String prodSeleccionado = (String) desProducto.getSelectedItem();
		String provSeleccionado = (String) desProveedor.getSelectedItem();
		String costeTexto = coste.getText().trim();
		
		String idProducto = obtenerIdSeleccionado(prodSeleccionado);
		String idProveedor = obtenerIdSeleccionado(provSeleccionado);
		
		//Formateamos la fecha para que sea válida en la BBDD
		String fechaTexto = "";
		if (elegirFecha.getDate() != null) {
			SimpleDateFormat fech = new SimpleDateFormat("yyyy-MM-dd");
			fechaTexto = fech.format(elegirFecha.getDate());
		}
		
		//Si un campo está vacio no se ejecuta
		if (idProducto == null || idProveedor == null || costeTexto.isEmpty() || fechaTexto.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Rellene todos los campos.");
			return;
		}
		
		try
		{
			// Convertimos el coste a int para insertar en la BBDD
			int costeInt = Integer.parseInt(costeTexto);
			
			ventanaPrincipal.ConexionPrincipal.conectar();
			
			String query = "INSERT INTO buy (ID_Merchandise, ID_Supplier, Cost, Purchase_Date) VALUES (" + "'" + idProducto + "', " + "'" + idProveedor + "', " + costeInt + ", " + "'" + fechaTexto + "')";
			
			if(ventanaPrincipal.ConexionPrincipal.ejecutarInsertDeleteUpdate(query) > 0)
			{
				ventanaPrincipal.ConexionPrincipal.desconectar();
				JOptionPane.showMessageDialog(this, "Compra añadida correctamente.");
				
				// Limpiamos los campos
				desProducto.setSelectedIndex(0);
				desProveedor.setSelectedIndex(0);
				coste.setText("");
				elegirFecha.setDate(null);
				return;
			}
			ventanaPrincipal.ConexionPrincipal.desconectar();
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
	}
}
