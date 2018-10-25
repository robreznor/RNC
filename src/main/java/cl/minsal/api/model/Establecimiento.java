package cl.minsal.api.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Establecimiento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id_establecimiento;
	private Integer codigo_establecimiento;
	private String nombre_establecimiento;
	private Integer servicio_salud; 
	
	@OneToOne(mappedBy="id_establecimiento")
	@JoinColumn(name = "id_establecimiento")
	private Localizacion id_localizacion;
	
	public Localizacion getlocalizacion() {
		return id_localizacion;
	}
	public void setlocalizacion(Localizacion id_localizacion) {
		this.id_localizacion = id_localizacion;
	}
	public void setCodigo_establecimiento(Integer codigo_establecimiento) {
		this.codigo_establecimiento = codigo_establecimiento;
	}
	public void setNombre_establecimiento(String nombre_establecimiento) {
		this.nombre_establecimiento = nombre_establecimiento;
	}
	public void setServicio_salud(Integer servicio_salud) {
		this.servicio_salud = servicio_salud;
	}
	public Integer getId() {
		return id_establecimiento;
	}
	public Integer getCodigo_establecimiento() {
		return codigo_establecimiento;
	}
	public String getNombre_establecimiento() {
		return nombre_establecimiento;
	}
	public Integer getServicio_salud() {
		return servicio_salud;
	}
	
}
