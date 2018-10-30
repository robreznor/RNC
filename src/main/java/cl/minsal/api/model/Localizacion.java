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
	private String direccion;
	
	@JoinColumn(name = "comuna")
    @OneToOne
    private Comuna comuna;
	
	@JoinColumn(name = "provincia")
    @OneToOne
    private Provincia provincia;
	
	@JoinColumn(name = "region")
    @OneToOne
    private Region region;
	
	public void setRegion(Region region) {
		this.region = region;
	}
	
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public void setComuna(Comuna comuna) {
		this.comuna = comuna;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getId() {
		return id_localizacion;
	}

	public Region getRegion() {
		return region;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public Comuna getComuna() {
		return comuna;
	}

	public String getDireccion() {
		return direccion;
	}

}
