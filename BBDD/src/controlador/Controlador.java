/**
 * 
 */
package controlador;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import dao.EditorialDAO;
import dao.LibroDAO;
import modelo.Editorial;
import modelo.Libro;
import vista.DialogoEditoriales;
import vista.DialogoLibros;
import vista.NuevaEditorial;
import vista.NuevoLibro;
import vista.VentanaPpal;

/**
 * @author David
 *
 */
public class Controlador {

	// Ventanas del sistema, objetos de la vista
	private VentanaPpal ventanaPpal;
	private DialogoEditoriales dialogoEditoriales;
	private DialogoLibros dialogoLibros;

	private NuevaEditorial nuevaEditorial;
	private NuevoLibro nuevoLibro;
	
	// Objetos DAO o CRUD de la base de datos
	private EditorialDAO editorialDAO;
	private LibroDAO libroDAO;
	
	public Controlador() {
		// Instanciamos las ventanas de la aplicación
		ventanaPpal = new VentanaPpal();
		dialogoEditoriales = new DialogoEditoriales();
		nuevaEditorial = new NuevaEditorial();
		dialogoLibros = new DialogoLibros();
		nuevoLibro = new NuevoLibro();
		
		// Establecemos los controladores para las vistas
		// Dando acceso al controlador desde las vistas
		ventanaPpal.setControlador(this);
		dialogoEditoriales.setControlador(this);
		nuevaEditorial.setControlador(this);
		dialogoLibros.setControlador(this);
		nuevoLibro.setControlador(this);
		
		// Instanciamos los objetos DAO
		editorialDAO = new EditorialDAO();
		libroDAO = new LibroDAO();
	}
	
	public void inciarPrograma() {
		ventanaPpal.setVisible(true);
	}
	
	public void mostrarEditoriales() {
		ArrayList<Editorial> lista = editorialDAO.obtenerEditoriales();
		dialogoEditoriales.setListaEditoriales(lista);
		dialogoEditoriales.setVisible(true);
	}
	
	public void mostrarNuevaEditorial() {
		nuevaEditorial.setEditorial(null);
		nuevaEditorial.setVisible(true);
	}
	
	public void insertarEditorial(Editorial ed) {
		int res=editorialDAO.insertarEditorial(ed);
		if (res==0) {
			JOptionPane.showMessageDialog(nuevaEditorial, "Error. No se ha podido insertar");
		} else {
			JOptionPane.showMessageDialog(nuevaEditorial, "Editorial añadido correctamente.");
			nuevaEditorial.setVisible(false);
		}
	}

	public void mostrarActualizarEditorial(int codEditorial) {
		Editorial e = editorialDAO.obtenerEditorial(codEditorial);
		nuevaEditorial.setEditorial(e);
		nuevaEditorial.setVisible(true);
	}

	public void actualizarEditorial(Editorial ed) {
		int res=editorialDAO.actualizarEditorial(ed);
		if (res==0) {
			JOptionPane.showMessageDialog(nuevaEditorial, "Error. No se ha podido actualizar");
		} else {
			JOptionPane.showMessageDialog(nuevaEditorial, "Editorial actualizado correctamente.");
			nuevaEditorial.setVisible(false);
		}
		mostrarEditoriales();
	}

	public void eliminarEditorial(int codEditorial) {
		int res=editorialDAO.eliminarEditorial(codEditorial);
		if (res==0) {
			JOptionPane.showMessageDialog(nuevaEditorial, "Error. No se ha podido eliminar");
		} else {
			JOptionPane.showMessageDialog(nuevaEditorial, "Editorial eliminada correctamente.");
		}
		mostrarEditoriales();
	}

	public void eliminarLibro(String isbn) {
		int res=libroDAO.eliminarLibro(isbn);
		if (res==0) {
			JOptionPane.showMessageDialog(nuevaEditorial, "Error. No se ha podido eliminar");
		} else {
			JOptionPane.showMessageDialog(nuevaEditorial, "Libro eliminado correctamente.");
		}
		mostrarLibros();
		
	}

	public void mostrarActualizarLibro(String isbn) {
		Libro l = libroDAO.obtenerLibro(isbn);
		nuevoLibro.setLibro(l);
		nuevoLibro.setVisible(true);
	}

	public void mostrarLibros() {
		ArrayList<Libro> lista = libroDAO.obtenerLibros();
		dialogoLibros.setListaLibros(lista);
		dialogoLibros.setVisible(true);
		
	}

	public void mostrarNuevoLibro() {
		nuevoLibro.setLibro(null);
		nuevoLibro.setVisible(true);
		
	}

	public void insertarLibro(Libro l) {
		int res=libroDAO.insertarLibro(l);
		if (res==0) {
			JOptionPane.showMessageDialog(nuevaEditorial, "Error. No se ha podido insertar");
		} else {
			JOptionPane.showMessageDialog(nuevaEditorial, "Libro añadido correctamente.");
			nuevoLibro.setVisible(false);
		}
		
	}

}
