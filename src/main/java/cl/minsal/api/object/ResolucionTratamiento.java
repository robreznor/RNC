package cl.minsal.api.object;

public class ResolucionTratamiento {
	
	public ResolucionTratamiento(){
		
	}
	
	private String header;
	private String tipo;
	private String fecha;
	private documentoDeOrigen documentoDeOrigen;
	private tratamiento tratamiento;
	private tratamientoIndicado tratamientoIndicado;
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
	public documentoDeOrigen getDocumentoDeOrigen() {
		return documentoDeOrigen;
	}
	public void setDocumentoDeOrigen(documentoDeOrigen documentoDeOrigen) {
		this.documentoDeOrigen = documentoDeOrigen;
	}
	public tratamiento getTratamiento() {
		return tratamiento;
	}
	public void setTratamiento(tratamiento tratamiento) {
		this.tratamiento = tratamiento;
	}
	public tratamientoIndicado getTratamientoIndicado() {
		return tratamientoIndicado;
	}
	public void setTratamientoIndicado(tratamientoIndicado tratamientoIndicado) {
		this.tratamientoIndicado = tratamientoIndicado;
	}
	public String getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}	
}
