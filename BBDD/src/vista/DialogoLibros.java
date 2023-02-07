package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.Editorial;
import modelo.Libro;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class DialogoLibros extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private Controlador controlador;


	/**
	 * Create the dialog.
	 */
	public DialogoLibros() {
		setBounds(100, 100, 672, 302);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[][grow][]"));
		{
			JLabel lblNewLabel = new JLabel("Listado de libros:");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
			contentPanel.add(lblNewLabel, "cell 0 0");
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, "cell 0 1,grow");
			{
				table = new JTable();
				table.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"ISBN", "T\u00EFtulo", "C\u00F3digo Editorial", "A\u00F1o", "Num. P\u00E1gs.", "Precio", "Cantidad", "Precio CD"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, String.class, Integer.class, Integer.class, Integer.class, Float.class, Integer.class, Float.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
					boolean[] columnEditables = new boolean[] {
						false, false, false, false, false, false, false, false
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
				table.getColumnModel().getColumn(0).setPreferredWidth(84);
				table.getColumnModel().getColumn(1).setPreferredWidth(120);
				table.getColumnModel().getColumn(2).setPreferredWidth(86);
				table.getColumnModel().getColumn(3).setPreferredWidth(62);
				table.getColumnModel().getColumn(4).setPreferredWidth(63);
				scrollPane.setViewportView(table);
			}
		}
		{
			{
				JPanel panel = new JPanel();
				contentPanel.add(panel, "cell 0 2,grow");
				panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
				{
					JButton btnModificar = new JButton("Modificar");
					btnModificar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							llamarActualizarLibro();
						}
					});
					{
						JButton btnEliminar = new JButton("Eliminar");
						btnEliminar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								llamarEliminarLibro();
							}
						});
						panel.add(btnEliminar);
					}
					panel.add(btnModificar);
					btnModificar.setHorizontalAlignment(SwingConstants.RIGHT);
				}
				JButton btnCerrar = new JButton("Cerrar");
				panel.add(btnCerrar);
				btnCerrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
			}
		}
	}
	
	protected void llamarEliminarLibro() {
		int fila=table.getSelectedRow();
		if(fila==-1) {
			JOptionPane.showMessageDialog(table, "Debe seleccionar un libro");
			return;
		}
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		String isbn = (String) modelo.getValueAt(fila, 0);
		
		controlador.eliminarLibro(isbn);
		
	}

	protected void llamarActualizarLibro() {
		int fila=table.getSelectedRow();
		if(fila==-1) {
			JOptionPane.showMessageDialog(table, "Debe seleccionar un libro");
			return;
		}
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		String isbn = (String) modelo.getValueAt(fila, 0);
		
		controlador.mostrarActualizarLibro(isbn);
	}

	public void setListaLibros(ArrayList<Libro> lista) {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0);
		for (Libro l : lista) {
			Object [] fila = {
					l.getIsbn(),l.getTitulo(),l.getCodEditorial(),l.getAnio(),l.getNumPags(),l.getPrecio(),l.getCantidad(),l.getPrecioCD()
			};
			modelo.addRow(fila);
		}
	}

	/**
	 * @param controlador el controlador a establecer
	 */
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	
	

}
