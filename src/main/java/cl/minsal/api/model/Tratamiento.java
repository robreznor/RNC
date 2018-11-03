package cl.minsal.api.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Tratamiento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id_tratamiento;
	private String descripcion_tratamiento;
	private Date fecha_intencion;
	private Timestamp fecha_registro;
	
	@ManyToOne
    @JoinColumn(name="id_diagnostico", nullable=false)
	private Diagnostico diagnostico;
	
	@ManyToOne
    @JoinColumn(name="id_medico", nullable=false)
	private Medico medico;
	
	@JoinColumn(name = "resolucion_comite")
    @OneToOne
    private Resolucion_comite resolucion_comite;
	
	@JoinColumn(name = "tipo_tratamiento")
    @OneToOne
    private Tipo_tratamiento tipo_tratamiento;
	
	@JoinColumn(name = "intencion_tratamiento")
    @OneToOne
    private Intencion_tratamiento intencion_tratamiento;
	public Integer getId_tratamiento() {
		return id_tratamiento;
	}
	
	public String getDescripcion_tratamiento() {
		return descripcion_tratamiento;
	}
	public Resolucion_comite getResolucion_comite() {
		return resolucion_comite;
	}
	public Tipo_tratamiento getTipo_tratamiento() {
		return tipo_tratamiento;
	}
	public void setTipo_tratamiento(Tipo_tratamiento tipo_tratamiento) {
		this.tipo_tratamiento = tipo_tratamiento;
	}
	public Date getFecha_intencion() {
		return fecha_intencion;
	}
	public void setFecha_intencion(Date fecha_intencion) {
		this.fecha_intencion = fecha_intencion;
	}
	public void setDiagnostico(Diagnostico diagnostico) {
		this.diagnostico = diagnostico;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public void setDescripcion_tratamiento(String descripcion_tratamiento) {
		this.descripcion_tratamiento = descripcion_tratamiento;
	}
	public void setResolucion_comite(Resolucion_comite resolucion_comite) {
		this.resolucion_comite = resolucion_comite;
	}
	public Intencion_tratamiento getIntencion_tratamiento() {
		return intencion_tratamiento;
	}
	public void setIntencion_tratamiento(Intencion_tratamiento intencion_tratamiento) {
		this.intencion_tratamiento = intencion_tratamiento;
	}
	public Timestamp getFecha_registro() {
		return fecha_registro;
	}
	public void setFecha_registro(Timestamp fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
		
}
