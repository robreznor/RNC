package cl.minsal.api.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PacienteSearch implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private Integer rut;
	private String nombre;
	private String apellido1;
	private String apellido2;
	
	public Integer getId() {
		return id;
	}
	public Integer getRut() {
		return rut;
	}

	public String getNombre() {
		return nombre;
	}
	public String getApellido1() {
		return apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
		
}

