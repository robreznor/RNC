package cl.minsal.api.object;

public class PacienteSearch {
		
	private Integer id_paciente;
	private Integer rut;
	private String nombre;
	private String apellido1;
	private String apellido2;
	
	public PacienteSearch(){
		
	}
	public PacienteSearch(Integer id, Integer rut, String nombre,
			String apellido1, String apellido2) {
		super();
		this.id_paciente = id;
		this.rut = rut;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
	}
	public Integer getId_paciente() {
		return id_paciente;
	}
	public Integer getRut() {
		return rut;
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
	public void setId_paciente(Integer id) {
		this.id_paciente = id;
	}
	public void setRut(Integer rut) {
		this.rut = rut;
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
		
}

