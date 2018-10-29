package cl.minsal.api.object;

public class documentoDeOrigen {
	
	private String servicioSalud;
	private String establecimientoCreadorDelDocumento;
	
	public String getServicioSalud() {
		return servicioSalud;
	}
	public void setServicioSalud(String servicioSalud) {
		this.servicioSalud = servicioSalud;
	}
	public String getEstablecimientoCreadorDelDocumento() {
		return establecimientoCreadorDelDocumento;
	}
	public void setEstablecimientoCreadorDelDocumento(
			String establecimientoCreadorDelDocumento) {
		this.establecimientoCreadorDelDocumento = establecimientoCreadorDelDocumento;
	}
}
