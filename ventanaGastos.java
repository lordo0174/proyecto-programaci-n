package Ventanas;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ConexionBD.ConexionMySQL;

public class ventanaGastos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	//Se declaran las variables para poder acceder a ellas
	private JTable tabla;
	private DefaultTableModel modeloTabla;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaGastos frame = new ventanaGastos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ventanaGastos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 350);
		setLocationRelativeTo(null); //Centra la ventana en la pantalla
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton atras = new JButton("Atrás");
		atras.setBounds(10, 277, 89, 23); //Bajamos el botón para que no se choque con la tabla
		contentPane.add(atras);
		atras.addActionListener(e -> {
			dispose();
			new ventanaCompradores().setVisible(true);
		});

		String[] columnasTabla = {"ID de compra", "ID de producto", "ID de proveedor", "Coste", "Fecha de compra"};  //Se inicializan las columnas
		modeloTabla = new DefaultTableModel(null, columnasTabla);   
		
		tabla = new JTable(modeloTabla);   //Se crea el JTable
		
		//Se añade el JScrollPane
		JScrollPane scrollPane = new JScrollPane(tabla);
		scrollPane.setBounds(10, 11, 614, 250);
		contentPane.add(scrollPane);

		consultarGastos(); //Se llama al metodo para que cargue los datos de las compras realizadas.
	}
	
	public void consultarGastos()
	{
		try
		{
			ventanaPrincipal.ConexionPrincipal.conectar();
			String query = "SELECT * FROM buy";
			
			ResultSet resultado = ventanaPrincipal.ConexionPrincipal.ejecutarSelect(query);
		
			
			while(resultado.next())
			{
				Object[] filas = new Object[5];			//Declaramos las filas de los datos en un array		
				filas[0] = resultado.getString("ID_Buy");
				filas[1] = resultado.getString("ID_Merchandise");
				filas[2] = resultado.getString("ID_Supplier");
				filas[3] = resultado.getString("Cost");
				filas[4] = resultado.getString("Purchase_Date");
				
				modeloTabla.addRow(filas); //Se añade la fila directamente a la tabla visible de la ventana
			}
			ventanaPrincipal.ConexionPrincipal.desconectar();
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}
}