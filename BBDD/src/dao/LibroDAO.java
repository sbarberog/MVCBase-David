package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.ConexionBD;
import modelo.Libro;

public class LibroDAO {
	
	private ConexionBD conexion;
	
	public LibroDAO() {
        this.conexion = new ConexionBD();
    }


	public ArrayList<Libro> obtenerLibros() {
    	// Obtenemos un objeto Connection para conectar a la base de datos.
		Connection con = conexion.getConexion();
		Statement consulta = null;
		ResultSet resultado = null;
		ArrayList<Libro> lista = new ArrayList<Libro>();
		
		try {
			consulta = con.createStatement();
			resultado = consulta.executeQuery("select * from libros");
			
			// Bucle para recorrer todas las filas que devuelve la consulta
			while(resultado.next()) {
				String isbn=resultado.getString("isbn");
				String titulo=resultado.getString("titulo");
				int codEditorial=resultado.getInt("codEditorial");
				int anio=resultado.getInt("anio");
				int numPags=resultado.getInt("num_pags");
				float precio=resultado.getFloat("precio");
				int cantidad=resultado.getInt("cantidad");
				float precioCD=resultado.getFloat("precioCD");
				
				Libro l = new Libro(isbn,titulo,codEditorial,anio,numPags,precio,cantidad,precioCD);
				lista.add(l);
			}
			
		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta: "+e.getMessage());
		} finally {
			try {
				resultado.close();
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} 
		}
		return lista;
    }
	
	public Libro obtenerLibro(String isbn) {
    	// Obtenemos una conexion a la base de datos.
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		ResultSet resultado = null;
		Libro l=null;
		
		try {
			consulta = con.prepareStatement("select * from libros "
					+ "where isbn = ?");
			consulta.setString(1, isbn);
			resultado = consulta.executeQuery();
			
			// Recoge la fila
			if (resultado.next()) {
				String titulo=resultado.getString("titulo");
				int codEditorial=resultado.getInt("codEditorial");
				int anio=resultado.getInt("anio");
				int numPags=resultado.getInt("num_pags");
				float precio=resultado.getFloat("precio");
				int cantidad=resultado.getInt("cantidad");
				float precioCD=resultado.getFloat("precioCD");
				
				l = new Libro(isbn,titulo,codEditorial,anio,numPags,precio,cantidad,precioCD);
			}
			
		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta: "+e.getMessage());
		} finally {
			try {
				resultado.close();
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
		}
		return l;
    }
	
	 public int insertarLibro(Libro libro) {
	    	// Obtenemos una conexion a la base de datos.
			Connection con = conexion.getConexion();
			PreparedStatement consulta = null;
			int resultado=0;
			
			try {
				consulta = con.prepareStatement("INSERT INTO libros (isbn,titulo,codEditorial,anio,num_pags,precio,cantidad,precioCD)"
						+ " VALUES (?,?,?,?,?,?,?,?) ");
				
				consulta.setString(1,libro.getIsbn());
				consulta.setString(2,libro.getTitulo());
				consulta.setInt(3, libro.getCodEditorial());
				consulta.setInt(4, libro.getAnio());
				consulta.setInt(5, libro.getNumPags());
				consulta.setFloat(6,libro.getPrecio());
				consulta.setInt(7, libro.getCantidad());
				consulta.setFloat(8, libro.getPrecioCD());
				resultado=consulta.executeUpdate();

			} catch (SQLException e) {
				System.out.println("Error al realizar la consulta: "+e.getMessage());
			} finally {
				try {
					consulta.close();
					conexion.desconectar();
				} catch (SQLException e) {
					System.out.println("Error al liberar recursos: "+e.getMessage());
				}
			}
			return resultado;
	    }
	 
	 public int actualizarLibro(Libro libro) {
	    	// Obtenemos una conexion a la base de datos.
			Connection con = conexion.getConexion();
			PreparedStatement consulta = null;
			int resultado=0;
			
			try {
				consulta = con.prepareStatement("update libros set \r\n"
						+ "titulo=?, `codEditorial`=?, anio=?, num_pags=?, "
						+ "precio=?, cantidad=?, `precioCD`=?\r\n"
						+ "    where isbn=?;");
				
				consulta.setString(1, libro.getTitulo());
				consulta.setInt(2, libro.getCodEditorial());
				consulta.setInt(3, libro.getAnio());
				consulta.setInt(4, libro.getNumPags());
				consulta.setFloat(5,libro.getPrecio());
				consulta.setInt(6, libro.getCantidad());
				consulta.setFloat(7, libro.getPrecioCD());
				consulta.setString(8, libro.getIsbn());
				resultado=consulta.executeUpdate();
				
				System.out.println(consulta);

			} catch (SQLException e) {
				System.out.println("Error al realizar la actualizacion: "+e.getMessage());
			} finally {
				try {
					consulta.close();
					conexion.desconectar();
				} catch (SQLException e) {
					System.out.println("Error al liberar recursos: "+e.getMessage());
				} 
			}
			return resultado;
	    }
	 
	 public int eliminarLibro(String isbn) {
	    	// Obtenemos una conexion a la base de datos.
			Connection con = conexion.getConexion();
			PreparedStatement consulta = null;
			int resultado=0;
			
			try {
				consulta = con.prepareStatement("DELETE FROM `biblioteca`.`libros`\r\n"
						+ "WHERE isbn = ?");
				
				consulta.setString(1, isbn);
				resultado=consulta.executeUpdate();

			} catch (SQLException e) {
				System.out.println("Error al realizar la actualizacion: "+e.getMessage());
			} finally {
				try {
					consulta.close();
					conexion.desconectar();
				} catch (SQLException e) {
					System.out.println("Error al liberar recursos: "+e.getMessage());
				}
			}
			return resultado;
	    }
}
