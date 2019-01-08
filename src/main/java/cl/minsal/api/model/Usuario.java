package cl.minsal.api.model;

import static cl.minsal.api.constant.SecurityConstants.SECRET;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ColumnTransformer;

@Entity
public class Usuario {
 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id_usuario;
 
    @ColumnTransformer(
        read =  "pgp_sym_decrypt(" +
                "    password, " +
                "    '"+SECRET+"'" +
                ")",
        write = "pgp_sym_encrypt( " +
                "    ?, " +
                "    '"+SECRET+"'" +
                ") "
    )
    
    @Column(columnDefinition = "bytea")
    private String password;
    
    private String usuario;
    private String role;
    
    @JoinColumn(name="codigo_establecimiento", referencedColumnName = "codigo_establecimiento")
    @OneToOne
    private Establecimiento establecimiento;

	public Usuario() {
		super();
	}
	
	public Usuario(String password, String usuario,String role, Establecimiento establecimiento) {
		super();
		this.password = password;
		this.usuario = usuario;
		this.role = role;
		this.establecimiento = establecimiento;
	}
	
	public Integer getId_usuario() {
		return id_usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Establecimiento getEstablecimiento() {
		return establecimiento;
	}
	public void setEstablecimiento(Establecimiento establecimiento) {
		this.establecimiento = establecimiento;
	}
 
}
