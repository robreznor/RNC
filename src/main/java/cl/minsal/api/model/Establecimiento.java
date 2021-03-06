package cl.minsal.api.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
	
	@JoinColumn(name = "id_localizacion")
    @OneToOne
    private Localizacion localizacion;
	
	@JoinColumn(name = "servicio_salud")
    @OneToOne
    private Servicio_salud servicio_salud;
	
	public Localizacion getLocalizacion(){
		return localizacion;
	}
	
	public void setLocalizacion(Localizacion localizacion){
		this.localizacion = localizacion;
	}
	
	public void setCodigo_establecimiento(Integer codigo_establecimiento) {
		this.codigo_establecimiento = codigo_establecimiento;
	}
	public void setNombre_establecimiento(String nombre_establecimiento) {
		this.nombre_establecimiento = nombre_establecimiento;
	}
	public Integer getId_establecimiento() {
		return id_establecimiento;
	}
	public Integer getCodigo_establecimiento() {
		return codigo_establecimiento;
	}
	public String getNombre_establecimiento() {
		return nombre_establecimiento;
	}

	public Servicio_salud getServicio_salud() {
		return servicio_salud;
	}

	public void setServicio_salud(Servicio_salud servicio_salud) {
		this.servicio_salud = servicio_salud;
	}
}
