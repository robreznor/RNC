package cl.minsal.api.object;

public class tratamientoIndicado {
	
	private String tipo;
	private String intencion;
	private String fechaIntencion;
	private String estado;
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getIntencion() {
		return intencion;
	}
	public void setIntencion(String intencion) {
		this.intencion = intencion;
	}
	public String getFechaIntencion() {
		return fechaIntencion;
	}
	public void setFechaIntencion(String fechaIntencion) {
		this.fechaIntencion = fechaIntencion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
