package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UsuarioObjeto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;
	@ManyToOne
	private Objeto objeto;
	private Integer cantidad = 0;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Objeto getObjeto() {
		return objeto;
	}

	public void setObjeto(Objeto objeto) {
		this.objeto = objeto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public UsuarioObjeto withUsuario(Usuario usuario) {
		this.setUsuario(usuario);
		return this;
	}

	public UsuarioObjeto withObjeto(Objeto objeto) {
		this.setObjeto(objeto);
		return this;
	}

	public UsuarioObjeto withCantidad(Integer cantidad) {
		this.setCantidad(cantidad);
		return this;
	}
}
