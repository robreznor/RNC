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
public class Diagnostico implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id_diagnostico;
	private Integer tipo_comite;
	private Date fecha_diagnostico;
	private Date fecha_comite;
	private String diagnostico_comite;
	private String diagnostico_cie10;
	private Integer ecog;
	private String tnm;
	private String estadio;
	private Timestamp fecha_registro;
	
	@ManyToOne
    @JoinColumn(name="id_paciente", nullable=false)
	private Paciente paciente;
	
	@OneToMany(mappedBy="diagnostico")
	private Set<Tratamiento> tratamiento;
	
	@OneToOne
    @JoinColumn(name = "id_diagnostico")
	private Antecedentes id_antecedentes;
	
	public void setTipo_comite(Integer tipo_comite) {
		this.tipo_comite = tipo_comite;
	}
	public void setFecha_diagnostico(Date fecha_diagnostico) {
		this.fecha_diagnostico = fecha_diagnostico;
	}
	public void setDiagnostico_comite(String diagnostico_comite) {
		this.diagnostico_comite = diagnostico_comite;
	}
	public void setDiagnostico_cie10(String diagnostico_cie10) {
		this.diagnostico_cie10 = diagnostico_cie10;
	}
	public void setEcog(Integer ecog) {
		this.ecog = ecog;
	}
	public void setTnm(String tnm) {
		this.tnm = tnm;
	}
	public void setEstadio(String estadio) {
		this.estadio = estadio;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public Integer getId_diagnostico() {
		return id_diagnostico;
	}
	public Integer getTipo_comite() {
		return tipo_comite;
	}
	public Date getFecha_diagnostico() {
		return fecha_diagnostico;
	}
	public String getDiagnostico_comite() {
		return diagnostico_comite;
	}
	public String getDiagnostico_cie10() {
		return diagnostico_cie10;
	}
	public Integer getEcog() {
		return ecog;
	}
	public String getTnm() {
		return tnm;
	}
	public String getEstadio() {
		return estadio;
	}	
	public Date getFecha_comite() {
		return fecha_comite;
	}
	public void setFecha_comite(Date fecha_comite) {
		this.fecha_comite = fecha_comite;
	}
	public Set<Tratamiento> getTratamiento() {
		return tratamiento;
	}
	public Timestamp getFecha_registro() {
		return fecha_registro;
	}
	public void setFecha_registro(Timestamp fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

}
