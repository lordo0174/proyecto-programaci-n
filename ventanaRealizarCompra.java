package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ConexionBD.ConexionMySQL;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;

public class ventanaRealizarCompra extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField precioRC;
	private boolean Actualizacion = false; //Este boolean evita buques infinitos
	private int precioBase = 0; //Para a la hora de calcular el precio este buscara el precio base en la base de datos en la tabla mercancias
	
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

	/**
	 * Create the frame.
	 */
	public ventanaRealizarCompra() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 463, 291);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRealizarCompraDe = new JLabel("Realizar compra de productos");
		lblRealizarCompraDe.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRealizarCompraDe.setBounds(115, 11, 239, 35);
		contentPane.add(lblRealizarCompraDe);
		
		JButton atras = new JButton("Atrás");
		atras.setBounds(10, 227, 89, 23);
		contentPane.add(atras);
		
		JComboBox desProveedoresRC = new JComboBox();
		desProveedoresRC.setBounds(115, 96, 140, 22);
		contentPane.add(desProveedoresRC);
		añadirProveedor(desProveedoresRC);
		
		JComboBox desMercanciaRC = new JComboBox();
		desMercanciaRC.setBounds(115, 131, 140, 22);
		contentPane.add(desMercanciaRC);
		añadirMercancia(desMercanciaRC);
		
		precioRC = new JTextField();
		precioRC.setEditable(false);
		precioRC.setBounds(289, 114, 58, 20);
		contentPane.add(precioRC);
		precioRC.setColumns(10);
		
		JButton botonCompra = new JButton("Comprar");
		botonCompra.setBounds(164, 183, 88, 22);
		contentPane.add(botonCompra);
		
		JLabel TPrecioRC = new JLabel("Precio");
		TPrecioRC.setFont(new Font("Tahoma", Font.BOLD, 15));
		TPrecioRC.setBounds(289, 68, 65, 35);
		contentPane.add(TPrecioRC);
		
		JLabel TCantidadRC_1 = new JLabel("Cantidad");
		TCantidadRC_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		TCantidadRC_1.setBounds(374, 68, 75, 35);
		contentPane.add(TCantidadRC_1);
		
		//Configuracion de la cantidad
		SpinnerNumberModel modeloSpinner = new SpinnerNumberModel(10, 10, 1000, 10); //(Valor inicial = 10, Mínimo = 10, Máximo = 1000, Incremento = 10)
		JSpinner CantidadRC = new JSpinner(modeloSpinner);
		CantidadRC.setBounds(395, 114, 45, 20);
		contentPane.add(CantidadRC);
		
		//Acciones del boton Compra
		botonCompra.addActionListener(e ->{
			String proveedorSeleccionado = (String) desProveedoresRC.getSelectedItem();
			String mercanciaSeleccionada = (String) desMercanciaRC.getSelectedItem();
			
			if (proveedorSeleccionado == null || proveedorSeleccionado.equals("Proveedor") ||
					mercanciaSeleccionada == null || mercanciaSeleccionada.equals("Mercancia")) {
					JOptionPane.showMessageDialog(this, "Debe seleccionar un proveedor y una mercancía.", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}
			
			String idProveedor = proveedorSeleccionado.split(" - ")[0];
			String idMercancia = mercanciaSeleccionada.split(" - ")[0];
			int cantidad = (int) CantidadRC.getValue();
			int costoTotal = precioBase * cantidad; // Calculamos el coste final exacto
			
			try {
				ventanaPrincipal.ConexionPrincipal.conectar();
				
				// Query adaptada a la tabla 'buy' usando CURDATE() para registrar el momento de la acción
				String queryInsert = "INSERT INTO buy (ID_Merchandise, ID_Supplier, Cost, Quantity, Purchase_Date) " + "VALUES ('" +idMercancia+ "', '" +idProveedor+ "', " +costoTotal+ ", " +cantidad+ ", CURDATE())";
				
				// Ejecutamos la inserción (reemplazar por tu método UID de escritura si es necesario)
				ventanaPrincipal.ConexionPrincipal.ejecutarInsertDeleteUpdate(queryInsert);
				ventanaPrincipal.ConexionPrincipal.desconectar();
				
				JOptionPane.showMessageDialog(this, "La compra a sido un exito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
				
				// Limpieza 
				precioRC.setText("");
				precioBase = 0;
				CantidadRC.setValue(10);
				desProveedoresRC.setSelectedIndex(0);
				
			} catch (SQLException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Error de compra.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			
		});
		
		
		
		//Para poder conectar los dos Desplegables Para Proveedores
		desProveedoresRC.addActionListener(e ->{
			if (Actualizacion) return;
			
			String seleccion = (String) desProveedoresRC.getSelectedItem();
			
			if (seleccion != null && !seleccion.equals("Proveedor")) {
				String idProveedorLimpio = seleccion.split(" - ")[0];
				// Filtramos el combo de mercancías usando este ID de proveedor
				filtrarMercancia(desMercanciaRC, idProveedorLimpio);
				precioRC.setText("");
				precioBase = 0;
				CantidadRC.setValue(10);
			} else {
				// Si vuelven a poner la opción por defecto, recargamos todas las mercancías de nuevo
				if (!Actualizacion) {
					añadirMercancia(desMercanciaRC);
					precioRC.setText("");
					precioBase = 0;
					CantidadRC.setValue(10);
				}
			}
		});
		
		//Para poder conectar los dos Desplegables Para Mercancia
		
		desMercanciaRC.addActionListener(e -> {
			if (Actualizacion) return;

			String seleccion = (String) desMercanciaRC.getSelectedItem();
			
			if (seleccion != null && !seleccion.equals("Mercancancia") && !seleccion.equals("Mercancia")) {
				String idMercanciaLimpia = seleccion.split(" - ")[0];
				
				// Buscamos a qué proveedor pertenece esta mercancía y forzamos su selección
				filtrarProveedor(desProveedoresRC, idMercanciaLimpia);
				
				//Buscamos el precio en la Base de Datos
				obtenerPrecio(idMercanciaLimpia, CantidadRC);
			} else {
				precioRC.setText("");
				precioBase = 0;
				CantidadRC.setValue(10);
			}
		});
		
		//Multiplica la cantidad que tiene selecionada
		CantidadRC.addChangeListener(e -> {
			int cantidad = (int) CantidadRC.getValue();
			int totalCalculado = precioBase * cantidad;
			
			//para que lo muestre en el precio
			precioRC.setText(String.valueOf(totalCalculado));
		});
		
		
		//Para volver atras
		atras.addActionListener(e -> {

			dispose();

			new ventanaCompradores().setVisible(true);

		});
		
		//Para ejecutarlo nada mas entres las funciones que estan dentro
		addWindowListener(new WindowAdapter()   //Es para que se ejecute cuando se abra la ventana automaticamente
				{
					@Override
					public void windowOpened(WindowEvent e)
					{
						añadirProveedor(desProveedoresRC);
						añadirMercancia(desMercanciaRC);
					}
				});
		

	}
	
	public void añadirProveedor(JComboBox<String> desProveedoresRC) {
		try {
			Actualizacion = true;
			ventanaPrincipal.ConexionPrincipal.conectar();
			String query = "SELECT ID_Supplier, Name FROM supplier ORDER BY ID_Supplier ASC";
			ResultSet resultado = ventanaPrincipal.ConexionPrincipal.ejecutarSelect(query);
			desProveedoresRC.removeAllItems();
			
			desProveedoresRC.addItem("Proveedor");
			while (resultado.next()) {
	            // Usamos addItem para añadir el ID al desplegable
	            desProveedoresRC.addItem(resultado.getString("ID_Supplier")+ " - " + resultado.getString("Name"));
	        }

			desProveedoresRC.setSelectedIndex(0); 
	        
			ventanaPrincipal.ConexionPrincipal.desconectar();
			
			desProveedoresRC.revalidate();
			desProveedoresRC.repaint();
			Actualizacion = false;
		}
		catch(SQLException ex){
			ex.printStackTrace();
			Actualizacion = false;
		}
		
	}
	
	public void añadirMercancia(JComboBox<String> desMercanciaRC) {
		try {
			Actualizacion = true;
			ventanaPrincipal.ConexionPrincipal.conectar();
			String query = "SELECT ID_Merchandise, Name FROM merchandise ORDER BY ID_Supplier ASC";
			ResultSet resultado = ventanaPrincipal.ConexionPrincipal.ejecutarSelect(query);
			desMercanciaRC.removeAllItems();
			
			desMercanciaRC.addItem("Mercancia");
			while (resultado.next()) {
	            // Usamos addItem para añadir el ID al desplegable
				desMercanciaRC.addItem(resultado.getString("ID_Merchandise")+ " - " + resultado.getString("Name"));
	        }
	        
			desMercanciaRC.setSelectedIndex(0);  //Selecciona el ítem vacío
	        
			ventanaPrincipal.ConexionPrincipal.desconectar(); 
			Actualizacion = false;
		}
		catch(SQLException ex){
			ex.printStackTrace();
			Actualizacion = false;
		}
		
	}
	
	//Flitros para los desprlables 
	
	//Proveedores seleciona las mercancias que son de estos proveedores
	
	public void filtrarMercancia (JComboBox desMercanciaRC, String idProveedor) {
		try {
			Actualizacion = true;
			ventanaPrincipal.ConexionPrincipal.conectar();
			
			String query = "SELECT ID_Merchandise, Name FROM merchandise WHERE ID_Supplier = '" + idProveedor + "' ORDER BY ID_Merchandise ASC";
			ResultSet resultado = ventanaPrincipal.ConexionPrincipal.ejecutarSelect(query);
			desMercanciaRC.removeAllItems();
			
			desMercanciaRC.addItem("Mercancia");
			while (resultado.next()) {
				desMercanciaRC.addItem(resultado.getString("ID_Merchandise") + " - " + resultado.getString("Name"));
			}
			
			desMercanciaRC.setSelectedIndex(0);
			ventanaPrincipal.ConexionPrincipal.desconectar();
			
			desMercanciaRC.revalidate();
			desMercanciaRC.repaint();
			Actualizacion = false;
		}catch (SQLException ex) {
			ex.printStackTrace();
			Actualizacion = false;
		}
	}
	
	//Mercancia seleciona su proveedor
	public void filtrarProveedor (JComboBox desProveedoresRC, String idMercancia) {
		try {
			Actualizacion = true;
			ventanaPrincipal.ConexionPrincipal.conectar();
			//Pedimos con el Selecte que nosde el id del proveedor
			String query = "SELECT ID_Supplier FROM merchandise WHERE ID_Merchandise = '" + idMercancia + "'";
			ResultSet resultado = ventanaPrincipal.ConexionPrincipal.ejecutarSelect(query);
				if (resultado.next()) {
					String idProveedorAsociado = resultado.getString("ID_Supplier");
					ventanaPrincipal.ConexionPrincipal.desconectar();
				
					Actualizacion = true; // Activamos bandera para obligar al combo de proveedores a cambiar sin alterar el resto
				
					// Recorremos los ítems del combo de proveedores buscando cuál empieza con el ID que necesitamos
					for (int i = 0; i < desProveedoresRC.getItemCount(); i++) {
						String item = (String) desProveedoresRC.getItemAt(i);
							if (item.startsWith(idProveedorAsociado + " - ")) {
								desProveedoresRC.setSelectedIndex(i); // Seleccionamos el proveedor correcto automáticamente
								break;
							}
					}
					Actualizacion = false;
				} else {
					ventanaPrincipal.ConexionPrincipal.desconectar();
				}
			
		
		}catch (SQLException ex) {
			ex.printStackTrace();
			Actualizacion = false;
		}
	}
	
	//Calcular el costo al buscar el precio base
	public void obtenerPrecio(String idMercancia, JSpinner cantidadSelecionad) {
		
		try{
			ventanaPrincipal.ConexionPrincipal.conectar();
			//Buscamos el precio en la base de datos en la tabla de mercancias
			String query = "SELECT Price FROM merchandise WHERE ID_Merchandise = '" + idMercancia + "'";
			ResultSet resultado = ventanaPrincipal.ConexionPrincipal.ejecutarSelect(query);
			
			if (resultado.next()) {
				precioBase = resultado.getInt("Price"); //Guardar el precio
				
				//Multiplicamos
				int cantidadActual = (int) cantidadSelecionad.getValue();
				int costoInicial = precioBase * cantidadActual;
				
				precioRC.setText(String.valueOf(costoInicial));
				
			}
			ventanaPrincipal.ConexionPrincipal.desconectar();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	
}
