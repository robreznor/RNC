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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Paciente implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id_paciente;
	private Integer rut;
	private String dverificador;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private Date fecha_nacimiento;

	private Timestamp fecha_registro;

    @JoinColumn(name="id_localizacion")
    @OneToOne
    private Localizacion localizacion;
    
    @JoinColumn(name="actividad_economica")
    @OneToOne
    private Actividad_economica actividad_economica;
    
    @JoinColumn(name="beneficiario_fonasa")
    @OneToOne
    private Beneficiario_fonasa beneficiario_fonasa;
    
    @JoinColumn(name="estado_conyugal")
    @OneToOne
    private Estado_conyugal estado_conyugal;
    
    @JoinColumn(name="genero")
    @OneToOne
    private Genero genero;
    
    @JoinColumn(name="nacionalidad")
    @OneToOne
    private Pais pais;
    
    @JoinColumn(name="nivel_instruccion")
    @OneToOne
    private Instruccion nivel_instruccion;
    
    public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	@JoinColumn(name="ocupacion")
    @OneToOne
    private Ocupacion ocupacion;
    
    @JoinColumn(name="prevision")
    @OneToOne
    private Prevision prevision;
    
    @JoinColumn(name="pueblo_originario")
    @OneToOne
    private Pueblo_originario pueblo_originario;
    
    @JoinColumn(name="religion")
    @OneToOne
    private Religion_culto religion;

	@OneToMany(mappedBy="paciente")
	private Set<Diagnostico> diagnostico;
	
	public Actividad_economica getActividad_economica() {
		return actividad_economica;
	}

	public void setActividad_economica(Actividad_economica actividad_economica) {
		this.actividad_economica = actividad_economica;
	}

	public Localizacion getLocalizacion(){
		return localizacion;
	}
	
	public void setLocalizacion(Localizacion localizacion) {
		this.localizacion = localizacion;
	}
	
	public Set<Diagnostico> getDiagnostico() {
		return diagnostico;
	}
	
	public Integer getId_paciente(){
		return id_paciente;
	}

	public Integer getRut() {
		return rut;
	}

	public String getDverificador() {
		return dverificador;
	}
	
	public void setRut(Integer rut) {
		this.rut = rut;
	}

	public void setDverificador(String dverificador) {
		this.dverificador = dverificador;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public void setPueblo_originario(Pueblo_originario pueblo_originario) {
		this.pueblo_originario = pueblo_originario;
	}

	public void setEstado_conyugal(Estado_conyugal estado_conyugal) {
		this.estado_conyugal = estado_conyugal;
	}

	public void setReligion(Religion_culto religion) {
		this.religion = religion;
	}

	public void setInstruccion(Instruccion instruccion) {
		this.nivel_instruccion = instruccion;
	}

	public void setOcupacion(Ocupacion ocupacion) {
		this.ocupacion = ocupacion;
	}

	public void setDiagnostico(Set<Diagnostico> diagnostico) {
		this.diagnostico = diagnostico;
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
	
	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public Genero getGenero() {
		return genero;
	}

	public Pueblo_originario getPueblo_originario() {
		return pueblo_originario;
	}

	public Estado_conyugal getEstado_conyugal() {
		return estado_conyugal;
	}

	public Religion_culto getReligion() {
		return religion;
	}

	public Instruccion getInstruccion() {
		return nivel_instruccion;
	}

	public Ocupacion getOcupacion() {
		return ocupacion;
	}

	public Prevision getPrevision() {
		return prevision;
	}

	public void setPrevision(Prevision prevision) {
		this.prevision = prevision;
	}

	public Beneficiario_fonasa getBeneficiario_fonasa() {
		return beneficiario_fonasa;
	}

	public void setBeneficiario_fonasa(Beneficiario_fonasa beneficiario_fonasa) {
		this.beneficiario_fonasa = beneficiario_fonasa;
	}

	public Timestamp getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(Timestamp fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

}
