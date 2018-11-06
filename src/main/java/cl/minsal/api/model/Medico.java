package cl.minsal.api.model;

import java.io.Serializable;


import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Medico implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id_medico;
	private String nombre_medico;
	private String apellido1;
	private String apellido2;
	private Timestamp fecha_registro;
	
	@OneToMany(mappedBy="medico")
	private Set<Tratamiento> tratamiento;
	
	@OneToOne
	@JoinColumn(name = "id_establecimiento")
	private Establecimiento establecimiento;
	
	public Establecimiento getEstablecimiento() {
		return establecimiento;
	}
	public void setEstablecimiento(Establecimiento establecimiento) {
		this.establecimiento = establecimiento;
	}
	public void setNombre_medico(String nombre_medico) {
		this.nombre_medico = nombre_medico;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public void setTratamiento(Set<Tratamiento> tratamiento) {
		this.tratamiento = tratamiento;
	}
	public Integer getId() {
		return id_medico;
	}
	public String getNombre_medico() {
		return nombre_medico;
	}
	public String getApellido1() {
		return apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public Timestamp getFecha_registro() {
		return fecha_registro;
	}
	public void setFecha_registro(Timestamp fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	
}
