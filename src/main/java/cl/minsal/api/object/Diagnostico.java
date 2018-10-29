package cl.minsal.api.object;

public class Diagnostico {
	
	public Diagnostico(){
		
	}
	
	private String header;
	private String tipo;
	private String fecha;
	private institucionOrigen institucionOrigen;
	private diagnostic diagnostico;
	private documentoDeOrigen documentoDeOrigen;
	private String fechaRegistro;
	
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public institucionOrigen getInstitucionOrigen() {
		return institucionOrigen;
	}
	public void setInstitucionOrigen(institucionOrigen institucionOrigen) {
		this.institucionOrigen = institucionOrigen;
	}
	public diagnostic getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(diagnostic diagnostico) {
		this.diagnostico = diagnostico;
	}
	public documentoDeOrigen getDocumentoDeOrigen() {
		return documentoDeOrigen;
	}
	public void setDocumentoDeOrigen(documentoDeOrigen documentoDeOrigen) {
		this.documentoDeOrigen = documentoDeOrigen;
	}
	public String getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
}
