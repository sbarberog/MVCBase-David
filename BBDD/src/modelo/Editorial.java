/**
 * 
 */
package modelo;

import java.util.Objects;

/**
 * @author David
 *
 */
public class Editorial {

	private int codEditorial;	//al ser de tipo unsigned, podríamos limitarlo por código
	private String nombre;
	private int anio;
	
	
	
	public Editorial() {
		this.nombre="";
	}
	public Editorial(int codEditorial, String nombre, int año) {
		super();
		this.codEditorial = codEditorial;
		this.nombre = nombre;
		this.anio = año;
	}
	public int getCodEditorial() {
		return codEditorial;
	}
	public void setCodEditorial(int codEditorial) {
		this.codEditorial = codEditorial;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getAño() {
		return anio;
	}
	public void setAño(int año) {
		this.anio = año;
	}
	@Override
	public int hashCode() {
		return Objects.hash(codEditorial);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Editorial other = (Editorial) obj;
		return codEditorial == other.codEditorial;
	}
	@Override
	public String toString() {
		return this.codEditorial+" - "+this.nombre;
	}
	
	
}
