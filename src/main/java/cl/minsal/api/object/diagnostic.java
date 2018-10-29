package cl.minsal.api.object;

public class diagnostic {
	
	private String diagnosticoCommite;
	private String diagnosticoCIE10;
	private String TNM;
	private String estadio;
	
	public String getDiagnosticoCommite() {
		return diagnosticoCommite;
	}
	public void setDiagnosticoCommite(String diagnosticoCommite) {
		this.diagnosticoCommite = diagnosticoCommite;
	}
	public String getDiagnosticoCIE10() {
		return diagnosticoCIE10;
	}
	public void setDiagnosticoCIE10(String diagnosticoCIE10) {
		this.diagnosticoCIE10 = diagnosticoCIE10;
	}
	public String getTNM() {
		return TNM;
	}
	public void setTNM(String tNM) {
		TNM = tNM;
	}
	public String getEstadio() {
		return estadio;
	}
	public void setEstadio(String estadio) {
		this.estadio = estadio;
	}
}
