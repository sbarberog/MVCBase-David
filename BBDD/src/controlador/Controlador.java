/**
 * 
 */
package controlador;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import dao.EditorialDAO;
import modelo.Editorial;
import vista.DialogoEditoriales;
import vista.NuevaEditorial;
import vista.VentanaPpal;

/**
 * @author David
 *
 */
public class Controlador {

	// Ventanas del sistema, objetos de la vista
	private VentanaPpal ventanaPpal;
	private DialogoEditoriales dialogoEditoriales;

	private NuevaEditorial nuevaEditorial;
	
	// Objetos DAO o CRUD de la base de datos
	private EditorialDAO editorialDAO;
	
	public Controlador() {
		// Instanciamos las ventanas de la aplicación
		ventanaPpal = new VentanaPpal();
		dialogoEditoriales = new DialogoEditoriales();
		nuevaEditorial = new NuevaEditorial();
		
		// Establecemos los controladores para las vistas
		// Dando acceso al controlador desde las vistas
		ventanaPpal.setControlador(this);
		dialogoEditoriales.setControlador(this);
		nuevaEditorial.setControlador(this);
		
		// Instanciamos los objetos DAO
		editorialDAO = new EditorialDAO();
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
}
