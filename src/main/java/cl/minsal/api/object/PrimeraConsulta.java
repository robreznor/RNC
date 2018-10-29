package cl.minsal.api.object;

public class PrimeraConsulta {
	
	public PrimeraConsulta() {
		
	}
	
	private String header;
	private String tipo;
	private String fecha;
	private institucionOrigen institucionOrigen;
    private sospechaDiagnostico sospechaDiagnostico;
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
	public sospechaDiagnostico getSospechaDiagnostico() {
		return sospechaDiagnostico;
	}
	public void setSospechaDiagnostico(sospechaDiagnostico sospechaDiagnostico) {
		this.sospechaDiagnostico = sospechaDiagnostico;
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
