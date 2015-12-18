package com.view.uiDesktop;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.hibernateintegracion.controllers.ProductoController;
import com.hibernateintegracion.domain.Productos;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

@SuppressWarnings("serial")
public class Frm_GestionProductos extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtDescripcion;
	private JTextField txtStock;
	private JTextField txtPrecio;
	private JTable table;

	ApplicationContext context = new GenericXmlApplicationContext("appContext.xml");
	ProductoController productoController = context.getBean("productoController", ProductoController.class);
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frm_GestionProductos frame = new Frm_GestionProductos();
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
	public Frm_GestionProductos() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(22, 31, 61, 14);
		contentPane.add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setEnabled(false);
		txtCodigo.setBounds(84, 25, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(22, 62, 61, 14);
		contentPane.add(lblDescripcion);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(84, 56, 230, 20);
		contentPane.add(txtDescripcion);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setBounds(22, 93, 61, 14);
		contentPane.add(lblStock);
		
		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(84, 87, 86, 20);
		contentPane.add(txtStock);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(186, 93, 61, 14);
		contentPane.add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(228, 87, 86, 20);
		contentPane.add(txtPrecio);
		
		final DefaultTableModel modelo = new DefaultTableModel();
		
		final JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setEnabled(false);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtCodigo.setEnabled(false);
				if ((txtCodigo.getText().length() != 0) 
						&& (txtDescripcion.getText().length() != 0)
						&& (txtStock.getText().length() != 0)
						&& (txtPrecio.getText().length() != 0)){
					
					productoController.insertar(objProducto());
					
					Object [] ob = new Object[4];
			        ob[0]=(txtCodigo.getText());
			        ob[1]=(txtDescripcion.getText());
			        ob[2]=(txtStock.getText());
			        ob[3]=(txtPrecio.getText());
			        modelo.addRow(ob);
			        ob = null;
					
					// LIMPIA LOS CAMPOS
					limpiarTexto();
				} else {
					JOptionPane.showMessageDialog(null, "No pueden existir campos vac�os...");
				}
				
			}
		});
		btnGuardar.setBounds(324, 27, 89, 23);
		contentPane.add(btnGuardar);
		
		final JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setEnabled(false);
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (txtCodigo.getText().length() != 0){
					
					// ACTUALIZA EN LA BASE DE DATOS
					productoController.cambiar(objProducto());	
				
					// REMUEVE LA FILA DEL JTABLE
					int f = table.getSelectedRow();
					modelo.removeRow(f);
					
					// A�ADE LA FILA CON NUEVOS CAMPOS
					Object [] ob = new Object[4];
			        ob[0]=(txtCodigo.getText());
			        ob[1]=(txtDescripcion.getText());
			        ob[2]=(txtStock.getText());
			        ob[3]=(txtPrecio.getText());
			        modelo.addRow(ob);
			        ob = null;
					
					// LIMPIA LOS CAMPOS
					limpiarTexto();
					
				} else {
					JOptionPane.showMessageDialog(null, "No hay producto que eliminar...");
				}
				
			}
		});
		btnActualizar.setBounds(324, 58, 89, 23);
		contentPane.add(btnActualizar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (txtCodigo.getText().length() != 0){
					
					// ELIMINA DE LA BASE DE DATOS
					productoController.eliminar(objProducto());	
					
					// ELIMINA DEL JTABLE
					int f = table.getSelectedRow();
					modelo.removeRow(f);
					
					// LIMPIA LOS CAMPOS
					limpiarTexto();
					
				} else {
					JOptionPane.showMessageDialog(null, "No hay producto que eliminar...");
				}
				
			}
		});
		btnEliminar.setBounds(324, 89, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCodigo.setEnabled(true);
				btnGuardar.setEnabled(true);
			}
		});
		btnNuevo.setBounds(202, 27, 89, 23);
		contentPane.add(btnNuevo);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 122, 444, 250);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int f = table.getSelectedRow();
				btnActualizar.setEnabled(true);
				txtCodigo.setText(table.getValueAt(f, 0).toString());
				txtDescripcion.setText(table.getValueAt(f, 1).toString());
				txtStock.setText(table.getValueAt(f, 2).toString());
				txtPrecio.setText(table.getValueAt(f, 3).toString());
			}
		});
		
		modelo.addColumn("Codigo");
		modelo.addColumn("Descripcion");
		modelo.addColumn("Stock");
		modelo.addColumn("Precio");
		table.setModel(modelo);
		
		List<Productos> productos = productoController.obtenerProductos();
		for (Productos pro: productos){
			Object [] ob = new Object[4];
	        ob[0]=(pro.getCodigo());
	        ob[1]=(pro.getDescripcion());
	        ob[2]=(pro.getStock());
	        ob[3]=(pro.getPrecio());
	        modelo.addRow(ob);
	        ob = null;
		}
		
		panel.add(new JScrollPane(table), BorderLayout.CENTER);
	}
		
	void limpiarTexto(){
		txtCodigo.setText("");
		txtDescripcion.setText("");
		txtStock.setText("");
		txtPrecio.setText("");
	}
	
	private Productos objProducto(){
		return new Productos(Integer.parseInt(txtCodigo.getText()),
				txtDescripcion.getText(), Integer.parseInt(txtStock.getText()), Double.parseDouble(txtPrecio.getText()));
	}
	
}
