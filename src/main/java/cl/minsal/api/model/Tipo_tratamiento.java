package cl.minsal.api.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tipo_tratamiento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer codigo;
	private String nombre_tratamiento;
	
	public String getNombre() {
		return nombre_tratamiento;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	
}
