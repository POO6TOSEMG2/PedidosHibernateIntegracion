package com.view.uiDesktop;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.hibernateintegracion.controllers.LineaPedidoController;
import com.hibernateintegracion.controllers.PedidosController;
import com.hibernateintegracion.controllers.StockController;
import com.hibernateintegracion.domain.LineaPedidos;
import com.hibernateintegracion.domain.Pedidos;
import com.hibernateintegracion.domain.Productos;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class Frm_PedidosReservados extends JFrame {

	private JPanel contentPane;
	private JTextField txtNumeroPedido;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frm_PedidosReservados frame = new Frm_PedidosReservados();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	DefaultTableModel modelo;
	private JTextField txtCliente;
	private JTextField txtCedula;
	private JTextField txtFecha;
	private JTextField txtIva;
	private JTextField txtTotalPedido;
	private JTable table_1;
	
	ApplicationContext context = new GenericXmlApplicationContext("appContext.xml");
	
	PedidosController pedidosController = context.getBean("pedidosController", PedidosController.class);
	LineaPedidoController lineaPedidoController = context.getBean("lineaPedidoController", LineaPedidoController.class);
	StockController stockController = context.getBean("stockController", StockController.class);
	
	/**
	 * Create the frame.
	 */
	public Frm_PedidosReservados() {
		setResizable(false);
		setTitle("Pedidos Reservados");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 595, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNumeroPedido = new JLabel("Numero Pedido");
		lblNumeroPedido.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNumeroPedido.setBounds(10, 21, 101, 20);
		contentPane.add(lblNumeroPedido);
		
		final JButton btnCargarPedido = new JButton("");
		btnCargarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table = new JTable();
				modelo = new DefaultTableModel();
				modelo.addColumn("Pedido No.");
				modelo.addColumn("Fecha");
				modelo.addColumn("Cliente");
				modelo.addColumn("Cedula");
				table.setModel(modelo);
				
				List<Pedidos> peds = pedidosController.pedidosReservados();
				
				for(Pedidos pe: peds){
					Object [] ob = new Object[4];
			        ob[0]=(pe.getNumero());
			        ob[1]=(pe.getFecha());
			        ob[2]=(pe.getClientes().getNombres());
			        ob[3]=(pe.getClientes().getCedula());
			        modelo.addRow(ob);
			        ob = null;
				}
				
				try{
					int dialogButton = JOptionPane.YES_NO_OPTION;
							JOptionPane.showConfirmDialog(btnCargarPedido, new JScrollPane(table), "Seleccione el Pedido", dialogButton);
							
							int f = table.getSelectedRow();
							if (dialogButton == JOptionPane.YES_OPTION){
								txtNumeroPedido.setText(table.getValueAt(f, 0).toString());
								txtFecha.setText(table.getValueAt(f, 1).toString());
								txtCliente.setText(table.getValueAt(f, 2).toString());
								txtCedula.setText(table.getValueAt(f, 3).toString());
							} else if (dialogButton == JOptionPane.NO_OPTION){
								remove(dialogButton);
							}
							
							Pedidos pedido = new Pedidos(); 
							pedido.setNumero(Integer.parseInt(txtNumeroPedido.getText()));
																					
							LineaPedidos lineaPedido = new LineaPedidos();
							lineaPedido.setPedidos(pedido);
														
							modelo = new DefaultTableModel();
							modelo.addColumn("Pedido No.");
							modelo.addColumn("Codigo Prod.");
							modelo.addColumn("Descripci�n");
							modelo.addColumn("Cantidad");
							modelo.addColumn("Precio");
							modelo.addColumn("SubTotal");
							table_1.setModel(modelo);
							
							List<LineaPedidos> lspeds = lineaPedidoController.obtenerLineasReservadasPedidos(lineaPedido);						
							
							for(LineaPedidos lped: lspeds){
								Object [] ob = new Object[6];
						        ob[0]=(lped.getPedidos().getNumero());
						        ob[1]=(lped.getProductos().getCodigo());
						        ob[2]=(lped.getProductos().getDescripcion());
						        ob[3]=(lped.getCantidad());
						        ob[4]=(lped.getProductos().getPrecio());
						        ob[5]=(lped.getSubtotal());
						        modelo.addRow(ob);
						        ob = null;
							}
							
							CalculoPedido();
							
				} catch (Exception e){
					e.getCause();
				}
			}
		});
		btnCargarPedido.setIcon(new ImageIcon(Frm_PedidosReservados.class.getResource("/com/sun/java/swing/plaf/windows/icons/Directory.gif")));
		btnCargarPedido.setBounds(187, 21, 35, 23);
		contentPane.add(btnCargarPedido);
		
		txtNumeroPedido = new JTextField();
		txtNumeroPedido.setEditable(false);
		txtNumeroPedido.setBounds(110, 22, 67, 20);
		contentPane.add(txtNumeroPedido);
		txtNumeroPedido.setColumns(10);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCliente.setBounds(10, 50, 101, 20);
		contentPane.add(lblCliente);
		
		txtCliente = new JTextField();
		txtCliente.setEditable(false);
		txtCliente.setColumns(10);
		txtCliente.setBounds(110, 51, 310, 20);
		contentPane.add(txtCliente);
		
		JLabel lblCedula = new JLabel("Cedula");
		lblCedula.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCedula.setBounds(430, 50, 101, 20);
		contentPane.add(lblCedula);
		
		txtCedula = new JTextField();
		txtCedula.setEditable(false);
		txtCedula.setColumns(10);
		txtCedula.setBounds(480, 52, 90, 20);
		contentPane.add(txtCedula);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFecha.setBounds(431, 21, 101, 20);
		contentPane.add(lblFecha);
		
		txtFecha = new JTextField();
		txtFecha.setEditable(false);
		txtFecha.setColumns(10);
		txtFecha.setBounds(480, 23, 90, 20);
		contentPane.add(txtFecha);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 81, 560, 285);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		table_1 = new JTable();
		table_1.setEnabled(false);
		panel.add(new JScrollPane(table_1), BorderLayout.CENTER);
		
		JButton btnDarAlta = new JButton("Dar Alta");
		btnDarAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Pedidos pedido = new Pedidos();
					
					if (txtNumeroPedido.getText().length() != 0){
						
						pedido.setNumero(Integer.parseInt(txtNumeroPedido.getText()));
						pedido.setIva(Double.parseDouble(txtIva.getText().toString()));						
						pedido.setTotal(Double.parseDouble(txtTotalPedido.getText().toString()));
						pedido.setEstado('A');
						
						pedidosController.actualizar(pedido);
						pedidosController.cambiarEstado(pedido);
						
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
		});
		btnDarAlta.setBounds(20, 377, 120, 29);
		contentPane.add(btnDarAlta);
		
		JButton btnEliminarPedido = new JButton("Eliminar Pedido");
		btnEliminarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if (txtNumeroPedido.getText().length() != 0){
					LineaPedidos lped;
					Pedidos pe;
					Productos prod;
					
					List<LineaPedidos> lineasActivas = new ArrayList<LineaPedidos>();
					
					for (int i = 0; i < table_1.getRowCount(); i++) {
						lped = new LineaPedidos();
						pe = new Pedidos();
						prod = new Productos();
						
						pe.setNumero(Integer.parseInt(table_1.getValueAt(i, 0).toString()));			
						prod.setCodigo(Integer.parseInt(table_1.getValueAt(i, 1).toString()));
						lped.setPedidos(pe);
						lped.setProductos(prod);
						lped.setCantidad(Integer.parseInt(table_1.getValueAt(i, 3).toString()));				
						
						lineasActivas.add(lped);
					}
					
					stockController.solicitarDevolucionProducto(lineasActivas);
										
					pe = new Pedidos();
					pe.setNumero(Integer.parseInt(txtNumeroPedido.getText()));
					pe.setEstado('E');
					pedidosController.cambiarEstado(pe);
				}
			}
		});
		btnEliminarPedido.setBounds(20, 417, 120, 29);
		contentPane.add(btnEliminarPedido);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(157, 417, 120, 29);
		contentPane.add(btnCancelar);
		
		JLabel lblIva = new JLabel("IVA");
		lblIva.setBounds(404, 378, 58, 14);
		contentPane.add(lblIva);
		
		txtIva = new JTextField();
		txtIva.setBounds(484, 375, 86, 20);
		contentPane.add(txtIva);
		txtIva.setColumns(10);
		
		JLabel lblTotalPedido = new JLabel("Total Pedido");
		lblTotalPedido.setBounds(404, 406, 70, 14);
		contentPane.add(lblTotalPedido);
		
		txtTotalPedido = new JTextField();
		txtTotalPedido.setColumns(10);
		txtTotalPedido.setBounds(484, 403, 86, 20);
		contentPane.add(txtTotalPedido);
	}
	
	private void CalculoPedido() {
		double suma = 0;
		for (int i = 1; i <= table_1.getRowCount(); i++) {
			suma += Double.parseDouble(table_1.getValueAt((i-1), 4).toString());
		}
		double iva = suma * 0.12;
		double total = suma + iva;
						
		txtIva.setText(Double.toString(iva));
		txtTotalPedido.setText(Double.toString(total));				
	}
	
}
