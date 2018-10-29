package cl.minsal.api.object;

public class institucionOrigen {
	
	private String nombreMedicoDerivador;
	private String servicioDeSalud;
	private String establecimiento;
	
	public String getNombreMedicoDerivador() {
		return nombreMedicoDerivador;
	}
	public void setNombreMedicoDerivador(String nombreMedicoDerivador) {
		this.nombreMedicoDerivador = nombreMedicoDerivador;
	}
	public String getServicioDeSalud() {
		return servicioDeSalud;
	}
	public void setServicioDeSalud(String servicioDeSalud) {
		this.servicioDeSalud = servicioDeSalud;
	}
	public String getEstablecimiento() {
		return establecimiento;
	}
	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}
}
