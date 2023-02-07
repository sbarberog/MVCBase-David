package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import modelo.Editorial;
import modelo.Libro;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NuevoLibro extends JFrame {

	private JPanel contentPane;
	private JTextField txtIsbn;
	private JTextField txtTitulo;
	private JTextField txtCodEditorial;
	private JTextField txtAnio;
	private JTextField txtNumPags;
	private JTextField txtPrecio;
	private JTextField txtCantidad;
	private JTextField txtPrecioCD;
	private Libro libro;
	private Controlador controlador;
	private JLabel lblLibros;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevoLibro frame = new NuevoLibro();
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
	public NuevoLibro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 454, 347);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][][][][grow]"));
		
		lblLibros = new JLabel("Inserción de libros");
		lblLibros.setOpaque(true);
		lblLibros.setBackground(Color.BLACK);
		lblLibros.setForeground(new Color(255, 255, 255));
		lblLibros.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblLibros, "cell 0 0 2 1,growx");
		
		JLabel lblNewLabel = new JLabel("ISBN");
		contentPane.add(lblNewLabel, "cell 0 2,alignx trailing");
		
		txtIsbn = new JTextField();
		contentPane.add(txtIsbn, "cell 1 2,growx");
		txtIsbn.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Título");
		contentPane.add(lblNewLabel_5, "cell 0 3,alignx trailing");
		
		txtTitulo = new JTextField();
		contentPane.add(txtTitulo, "cell 1 3,growx");
		txtTitulo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Código editorial");
		contentPane.add(lblNewLabel_1, "cell 0 4,alignx trailing");
		
		txtCodEditorial = new JTextField();
		contentPane.add(txtCodEditorial, "cell 1 4,growx");
		txtCodEditorial.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Año");
		contentPane.add(lblNewLabel_6, "cell 0 5,alignx trailing");
		
		txtAnio = new JTextField();
		contentPane.add(txtAnio, "cell 1 5,growx");
		txtAnio.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Núm. Págs.");
		contentPane.add(lblNewLabel_2, "cell 0 6,alignx trailing");
		
		txtNumPags = new JTextField();
		contentPane.add(txtNumPags, "cell 1 6,growx");
		txtNumPags.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Precio");
		contentPane.add(lblNewLabel_7, "cell 0 7,alignx trailing");
		
		txtPrecio = new JTextField();
		contentPane.add(txtPrecio, "cell 1 7,growx");
		txtPrecio.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Cantidad");
		contentPane.add(lblNewLabel_3, "cell 0 8,alignx trailing");
		
		txtCantidad = new JTextField();
		contentPane.add(txtCantidad, "cell 1 8,growx");
		txtCantidad.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Precio CD");
		contentPane.add(lblNewLabel_8, "cell 0 9,alignx trailing");
		
		txtPrecioCD = new JTextField();
		contentPane.add(txtPrecioCD, "cell 1 9,growx");
		txtPrecioCD.setColumns(10);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 1 11,alignx right,growy");
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarLibro();
			}
		});
		panel.add(btnOk);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		panel.add(btnCancelar);
	}

	protected void insertarLibro() {
		try {
			String isbn = txtIsbn.getText();
			String titulo = txtTitulo.getText();
			int codEditorial = Integer.parseInt(txtCodEditorial.getText());
			int anio = Integer.parseInt(txtAnio.getText());
			int numPags = Integer.parseInt(txtNumPags.getText());
			float precio= Float.parseFloat(txtPrecio.getText());
			int cantidad = Integer.parseInt(txtCantidad.getText());
			float precioCD= Float.parseFloat(txtPrecioCD.getText());
			
			//Editorial ed = new Editorial(0,nombre,año);
			Libro l = new Libro();
			
			l.setIsbn(isbn);
			l.setTitulo(titulo);
			l.setCodEditorial(codEditorial);
			l.setAnio(anio);
			l.setNumPags(numPags);
			l.setPrecio(precio);
			l.setCantidad(cantidad);
			l.setPrecioCD(precioCD);
		
		if (this.libro == null) {
			controlador.insertarLibro(l);
		}else {
			l.setIsbn(this.libro.getIsbn());
			controlador.mostrarActualizarLibro(isbn);
		}
	} catch (NumberFormatException e ) {
		JOptionPane.showMessageDialog(this, "Introduzca un número correcto");
	}
		
	}

	public void setLibro(Libro l) {
		libro = l;
		if (l!=null) {
			lblLibros.setText("Modificar Libro");
			txtIsbn.setText(l.getIsbn());
			txtTitulo.setText(l.getTitulo());
			txtCodEditorial.setText(""+l.getCodEditorial());
			txtAnio.setText(""+l.getAnio());
			txtNumPags.setText(""+l.getNumPags());
			txtPrecio.setText(""+l.getPrecio());
			txtCantidad.setText(""+l.getCantidad());
			txtPrecioCD.setText(""+l.getPrecioCD());
		} else {
			lblLibros.setText("Insertar Libro");
			txtIsbn.setText("");
			txtTitulo.setText("");
			txtCodEditorial.setText("");
			txtAnio.setText("");
			txtNumPags.setText("");
			txtPrecio.setText("");
			txtCantidad.setText("");
			txtPrecioCD.setText("");
		}
	}
	
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}


}
