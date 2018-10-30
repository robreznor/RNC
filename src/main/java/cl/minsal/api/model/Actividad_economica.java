package cl.minsal.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Actividad_economica implements Serializable {
		
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer codigo;
	private String nombre;
	
	public String getNombre() {
		return nombre;
	}
	
}
