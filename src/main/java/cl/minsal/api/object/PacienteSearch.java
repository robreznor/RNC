package cl.minsal.api.object;

import java.io.Serializable;

public class PacienteSearch implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
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

