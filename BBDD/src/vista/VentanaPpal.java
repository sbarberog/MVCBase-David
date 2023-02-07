package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class VentanaPpal extends JFrame {

	private JPanel contentPane;
	private Controlador controlador;



	/**
	 * Create the frame.
	 */
	public VentanaPpal() {
		
		setTitle("Base de datos de Biblioteca");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 196);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow][20:n][grow][]", "[][][][][][][]"));
		
		JButton btnMostrarEd = new JButton("MostarEditoriales");
		btnMostrarEd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.mostrarEditoriales();
			}
		});
		
		JLabel lblNewLabel = new JLabel("Editoriales");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		contentPane.add(lblNewLabel, "cell 1 1,alignx center");
		
		JLabel lblNewLabel_1 = new JLabel("Libros");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		contentPane.add(lblNewLabel_1, "cell 3 1,alignx center");
		contentPane.add(btnMostrarEd, "cell 1 3,growx");
		
		JButton btnInsertarEd = new JButton("Insertar Editorial");
		btnInsertarEd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.mostrarNuevaEditorial();
			}
		});
		
		JButton btnLibros = new JButton("Mostrar Libros");
		btnLibros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.mostrarLibros();
			}
		});
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		contentPane.add(separator, "cell 2 1 1 5");
		contentPane.add(btnLibros, "cell 3 3,growx");
		contentPane.add(btnInsertarEd, "cell 1 5,growx");
		
		JButton btnInsertarLibro = new JButton("Insertar Libro");
		btnInsertarLibro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.mostrarNuevoLibro();
			}
		});
		contentPane.add(btnInsertarLibro, "cell 3 5,growx");
	}



	/**
	 * @param controlador el controlador a establecer
	 */
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	
	

}
