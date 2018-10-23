package cl.minsal.api.object;

public class PacienteData {
	
	private String nombre;
	private String rut;
	private String fechaNacimiento;
	private Number sexo;
	private String fechaPrimeraConsulta;
	public PacienteData(){}
	
	public PacienteData(String nombre, String rut, String fechaNacimiento,
			Number sexo, String fechaPrimeraConsulta) {
		super();
		this.nombre = nombre;
		this.rut = rut;
		this.fechaNacimiento = fechaNacimiento;
		this.sexo = sexo;
		this.fechaPrimeraConsulta = fechaPrimeraConsulta;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public Number getSexo() {
		return sexo;
	}
	public void setSexo(Number sexo) {
		this.sexo = sexo;
	}
	public String getFechaPrimeraConsulta() {
		return fechaPrimeraConsulta;
	}
	public void setFechaPrimeraConsulta(String fachePrimeraConsulta) {
		this.fechaPrimeraConsulta = fachePrimeraConsulta;
	}
	
}
