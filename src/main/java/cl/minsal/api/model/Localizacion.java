package cl.minsal.api.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Localizacion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id_localizacion;
	private Integer region;
	private Integer provincia;
	private Integer comuna;
	private String direccion;
	
	public void setRegion(Integer region) {
		this.region = region;
	}
	
	public void setProvincia(Integer provincia) {
		this.provincia = provincia;
	}

	public void setComuna(Integer comuna) {
		this.comuna = comuna;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getId() {
		return id_localizacion;
	}

	public Integer getRegion() {
		return region;
	}

	public Integer getProvincia() {
		return provincia;
	}

	public Integer getComuna() {
		return comuna;
	}

	public String getDireccion() {
		return direccion;
	}

}
