package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ConexionBD.ConexionMySQL;
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
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ventanaMercancia extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String item;
	private JTextField idAñadirM;
	private JTextField nombreAñadirM;
	private JTextField tipoAñadirM;
	private JTextField precioAñadirM;
	private JTextField idProveedorAñadirM;
	private JTextField nombreEditarM;
	private JTextField tipoEditarM;
	private JTextField precioEditarM;
	private JTextField idProveedorEditarM;
	
	private JComboBox<String> desEditarM;
	private JComboBox<String> desEliminarM;
	private JLabel idProductoEtiqueta;
	private JLabel nombreEtiqueta;
	private JLabel tipoEtiqueta;
	private JLabel precioEtiqueta;
	
	private JButton btnConsultarM;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaMercancia frame = new ventanaMercancia();
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
	public ventanaMercancia() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 424);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAñadirM = new JButton("AÑADIR");
		btnAñadirM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				añadirM();
			}
		});
		btnAñadirM.setBounds(653, 86, 71, 31);
		contentPane.add(btnAñadirM);
		
		JLabel lblNewLabel = new JLabel("Gestión de mercancía");
		lblNewLabel.setBounds(284, 11, 160, 19);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblNewLabel);
		
		JButton btnEditarM = new JButton("EDITAR");
		btnEditarM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarM();
			}
		});
		btnEditarM.setBounds(653, 141, 71, 31);
		contentPane.add(btnEditarM);
		
		
		btnConsultarM = new JButton("CONSULTAR");
		btnConsultarM.setBounds(284, 255, 150, 31);
		contentPane.add(btnConsultarM);
		btnConsultarM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarM();
			}
		});
	
		
		JButton atras = new JButton("Atrás"); //Botón para ir hacia la ventana anterior
		atras.setBounds(42, 302, 89, 23);
		contentPane.add(atras);
		atras.addActionListener(e -> {

			dispose();

			new ventanaCompradores().setVisible(true);

		});
		
		desEditarM = new JComboBox<String>();
		desEditarM.setBounds(45, 145, 100, 22);
		contentPane.add(desEditarM);
		añadirDes(desEditarM);
		
		idAñadirM = new JTextField();
		idAñadirM.setBounds(55, 91, 86, 20);
		contentPane.add(idAñadirM);
		idAñadirM.setColumns(10);
		
		nombreAñadirM = new JTextField();
		nombreAñadirM.setColumns(10);
		nombreAñadirM.setBounds(176, 91, 86, 20);
		contentPane.add(nombreAñadirM);
		
		tipoAñadirM = new JTextField();
		tipoAñadirM.setColumns(10);
		tipoAñadirM.setBounds(290, 91, 86, 20);
		contentPane.add(tipoAñadirM);
		
		precioAñadirM = new JTextField();
		precioAñadirM.setColumns(10);
		precioAñadirM.setBounds(419, 91, 86, 20);
		contentPane.add(precioAñadirM);
		
		idProveedorAñadirM = new JTextField();
		idProveedorAñadirM.setColumns(10);
		idProveedorAñadirM.setBounds(563, 91, 35, 20);
		contentPane.add(idProveedorAñadirM);
		
		nombreEditarM = new JTextField();
		nombreEditarM.setColumns(10);
		nombreEditarM.setBounds(176, 146, 86, 20);
		contentPane.add(nombreEditarM);
		
		tipoEditarM = new JTextField();
		tipoEditarM.setColumns(10);
		tipoEditarM.setBounds(290, 146, 86, 20);
		contentPane.add(tipoEditarM);
		
		precioEditarM = new JTextField();
		precioEditarM.setColumns(10);
		precioEditarM.setBounds(419, 146, 86, 20);
		contentPane.add(precioEditarM);
		
		idProveedorEditarM = new JTextField();
		idProveedorEditarM.setColumns(10);
		idProveedorEditarM.setBounds(563, 146, 35, 20);
		contentPane.add(idProveedorEditarM);
		
		desEliminarM = new JComboBox<String>();
		desEliminarM.setBounds(232, 200, 100, 22);
		contentPane.add(desEliminarM);
		añadirDes(desEliminarM);
		
		addWindowListener(new WindowAdapter()   //Es para que se ejecute cuando se abra la ventana automaticamente
				{
					@Override
					public void windowOpened(WindowEvent e)
					{
						añadirDes(desEliminarM);
						añadirDes(desEditarM);//Es el metodo que queremos que se ejecute
					}
				});
		JButton btnEliminarM = new JButton("ELIMINAR");
		btnEliminarM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				eliminarItem(desEliminarM, desEditarM);
				
			}
		});
		btnEliminarM.setForeground(new Color(0, 0, 0));
		btnEliminarM.setBounds(372, 196, 150, 31);
		contentPane.add(btnEliminarM);
		
		JLabel idProveedorEtiqueta = new JLabel("ID de proveedor");
		idProveedorEtiqueta.setFont(new Font("Tahoma", Font.BOLD, 12));
		idProveedorEtiqueta.setVerticalAlignment(SwingConstants.TOP);
		idProveedorEtiqueta.setHorizontalAlignment(SwingConstants.LEFT);
		idProveedorEtiqueta.setBounds(538, 61, 100, 19);
		contentPane.add(idProveedorEtiqueta);
		
		idProductoEtiqueta = new JLabel("ID de producto");
		idProductoEtiqueta.setVerticalAlignment(SwingConstants.TOP);
		idProductoEtiqueta.setHorizontalAlignment(SwingConstants.LEFT);
		idProductoEtiqueta.setFont(new Font("Tahoma", Font.BOLD, 12));
		idProductoEtiqueta.setBounds(45, 62, 100, 19);
		contentPane.add(idProductoEtiqueta);
		
		nombreEtiqueta = new JLabel("Nombre");
		nombreEtiqueta.setVerticalAlignment(SwingConstants.TOP);
		nombreEtiqueta.setHorizontalAlignment(SwingConstants.LEFT);
		nombreEtiqueta.setFont(new Font("Tahoma", Font.BOLD, 12));
		nombreEtiqueta.setBounds(194, 61, 52, 19);
		contentPane.add(nombreEtiqueta);
		
		tipoEtiqueta = new JLabel("Tipo");
		tipoEtiqueta.setVerticalAlignment(SwingConstants.TOP);
		tipoEtiqueta.setHorizontalAlignment(SwingConstants.LEFT);
		tipoEtiqueta.setFont(new Font("Tahoma", Font.BOLD, 12));
		tipoEtiqueta.setBounds(320, 61, 35, 19);
		contentPane.add(tipoEtiqueta);
		
		precioEtiqueta = new JLabel("Precio");
		precioEtiqueta.setVerticalAlignment(SwingConstants.TOP);
		precioEtiqueta.setHorizontalAlignment(SwingConstants.LEFT);
		precioEtiqueta.setFont(new Font("Tahoma", Font.BOLD, 12));
		precioEtiqueta.setBounds(444, 62, 43, 19);
		contentPane.add(precioEtiqueta);

	}
	
	
	
	public void añadirDes(JComboBox desEditarM)           //Método para añadir los productos al desplegable de editar.
	{
		try
		{
			ventanaPrincipal.ConexionPrincipal.conectar();
			String query = "SELECT ID_Merchandise FROM merchandise ORDER BY ID_Merchandise ASC";
			ResultSet resultado = ventanaPrincipal.ConexionPrincipal.ejecutarSelect(query);
			desEditarM.removeAllItems();
			
			desEditarM.addItem("Seleccionar");	// Aparece Seleccionar por defecto en lugar de escoger un ID de producto

	        // Cambiamos 'if' por 'while' para recorrer todas las filas
	        while (resultado.next()) {
	            // Usamos addItem para añadir el ID al desplegable
	            desEditarM.addItem(resultado.getString("ID_Merchandise"));
	        }
	        
	        desEditarM.setSelectedIndex(0);  //Selecciona el ítem vacío
	        
			ventanaPrincipal.ConexionPrincipal.desconectar(); 
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
	}
	
	
	public void eliminarItem(JComboBox desEliminarM, JComboBox desEditarM)       //Método para eliminar un producto por ID
	{
		String idParaBorrar = (String) desEliminarM.getSelectedItem(); //Se obtiene el ID y se convierte en String.

	    if (idParaBorrar == null || idParaBorrar.equals("Seleccionar"))
	    {   
	    	JOptionPane.showMessageDialog(this, "Selecciona un producto.");
	        return;
	    }

	    try {
	        ventanaPrincipal.ConexionPrincipal.conectar();
	        String query = "DELETE FROM merchandise WHERE ID_Merchandise = '" + idParaBorrar + "'";
	        ventanaPrincipal.ConexionPrincipal.ejecutarInsertDeleteUpdate(query);
	        ventanaPrincipal.ConexionPrincipal.desconectar();

	        añadirDes(desEliminarM);  //Refrescamos los desplegables para que no aparezcan en los dos cuando se elimina.
	        añadirDes(desEditarM);
	  
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}
	
	public void editarM()				// Método para editar atributos de un producto según su ID.
	{
	    String idSeleccionado = (String) desEditarM.getSelectedItem();
	    
	    if (idSeleccionado == null || idSeleccionado.equals("Seleccionar"))   //Si el id está vacío o no se ha elegido ninguno -->
	    {
	        JOptionPane.showMessageDialog(this, "Selecciona un producto.");		// --> salta error pidiendo seleccionar un producto
	        return;
	    }

	    try
	    {
	        ventanaPrincipal.ConexionPrincipal.conectar();

	        String queryBusqueda = "SELECT Name, Type, Price, ID_Supplier FROM merchandise WHERE ID_Merchandise = '" + idSeleccionado + "'";
	        ResultSet rs = ventanaPrincipal.ConexionPrincipal.ejecutarSelect(queryBusqueda);

	        if (rs.next())
	        {
	            String nombreProducto = nombreEditarM.getText().trim().isEmpty() ? rs.getString("Name") : nombreEditarM.getText();     	            // Si el campo de la ventana está vacío, usamos el valor que ya hay en la BBDD
	            String tipoProducto = tipoEditarM.getText().trim().isEmpty() ? rs.getString("Type") : tipoEditarM.getText();
	            String precioProducto = precioEditarM.getText().trim().isEmpty() ? rs.getString("Price") : precioEditarM.getText();
	            String idProveedor = idProveedorEditarM.getText().trim().isEmpty() ? rs.getString("ID_Supplier") : idProveedorEditarM.getText();

	            String queryUpdate = "UPDATE merchandise SET " + "Name = '" + nombreProducto+ "', " + "Type = '" + tipoProducto + "', " + "Price = " + precioProducto + ", " + "ID_Supplier = " + idProveedor + " " + "WHERE ID_Merchandise = '" + idSeleccionado + "'";

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
	
	public void añadirM()
	{
		String idProducto=idAñadirM.getText().trim();
		String nombreProducto=nombreAñadirM.getText().trim();
		String tipoProducto=tipoAñadirM.getText().trim();
		String precioProducto=precioAñadirM.getText().trim();
		String proveedorProducto=idProveedorAñadirM.getText().trim();
		
		//Si un campo está vacio no se ejecuta la acción.
		if (idProducto.isEmpty() || nombreProducto.isEmpty() || tipoProducto.isEmpty() || precioProducto.isEmpty() || proveedorProducto.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Rellene todos los campos.");
			return;
		}
		try
		{
			ventanaPrincipal.ConexionPrincipal.conectar();
			
			String query = "INSERT INTO merchandise (ID_Merchandise, Name, Type, Price, ID_Supplier) VALUES (" + "'" + idProducto + "', " + "'" + nombreProducto + "', " + "'" + tipoProducto + "', " + "" + precioProducto + ", " + "" + proveedorProducto + ")";
			
			if(ventanaPrincipal.ConexionPrincipal.ejecutarInsertDeleteUpdate(query)>0)
			{
				JOptionPane.showMessageDialog(this, "Producto añadido correctamente.");
				
				añadirDes(desEditarM);   //Limpiamos los campos.
				añadirDes(desEliminarM);
				
			}
			ventanaPrincipal.ConexionPrincipal.desconectar();
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void consultarM()
	{
		JFrame consultarTabla=new JFrame("Consulta de productos");  //Creamos la ventana dentro de la misma clase
		consultarTabla.setBounds(100, 100, 700, 400);
		consultarTabla.setLocationRelativeTo(null);   //Se centra la ventana
		consultarTabla.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   //Si se cierra esta ventana no afecta a las demás ventanas abiertas
		
		String[] columnasTabla= {"ID de producto", "Nombre", "Tipo", "Precio", "ID de proveedor"};  //Se definen las cabeceras de la tabla
		DefaultTableModel modeloTabla=new DefaultTableModel(null, columnasTabla);   //Creamos el modelo de la tabla
		
		try
		{
			ventanaPrincipal.ConexionPrincipal.conectar();
			String query = "SELECT * FROM merchandise";
			ResultSet resultado=ventanaPrincipal.ConexionPrincipal.ejecutarSelect(query);
			
			while(resultado.next())
			{
				Object[] filas=new Object[5];						//Creamos un array para representar las filas de la tabla
				filas[0]=resultado.getString("ID_Merchandise");
				filas[1]=resultado.getString("Name");
				filas[2]=resultado.getString("Type");
				filas[3]=resultado.getString("Price");
				filas[4]=resultado.getString("ID_Supplier");
				
				modeloTabla.addRow(filas);		//Se añaden las filas al modelo de la tabla
			}
			ventanaPrincipal.ConexionPrincipal.desconectar();
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		JTable tabla=new JTable(modeloTabla);   //Creamos la tabla con todos los datos de la BBDD
		consultarTabla.add(new JScrollPane(tabla));		//Añadimos la tabla a JScrollPane para que podamos scrollear
		consultarTabla.setVisible(true);		//Hacemos la tabla visible
		
		
	}
}
