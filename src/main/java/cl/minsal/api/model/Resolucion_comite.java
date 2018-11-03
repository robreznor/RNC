package cl.minsal.api.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Resolucion_comite implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer codigo_resolucion;
	private String descripcion;
	private Integer codigo_comite;
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public Integer getCodigo() {
		return codigo_resolucion;
	}
	
	public Integer getCodigo_comite() {
		return codigo_comite;
	}
	
}
