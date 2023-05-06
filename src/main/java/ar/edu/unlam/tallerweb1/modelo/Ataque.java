package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ataque {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	private TipoPokemon tipo;
	private Double potencia;
	private Double precision;
	private Double pp;
	private Boolean efecto;
	
	
//	public Ataque() {
//		
//	}
//
//	public Ataque(Long id, String nombre, Enum tipo, Double potencia, Double precision, Double pp, Boolean efecto) {
//		super();
//		this.id = id;
//		this.nombre = nombre;
//		this.tipo = tipo;
//		this.potencia = potencia;
//		this.precision = precision;
//		this.pp = pp;
//		this.efecto = efecto;
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Double getPotencia() {
		return potencia;
	}

	public void setPotencia(Double potencia) {
		this.potencia = potencia;
	}

	public Double getPrecision() {
		return precision;
	}

	public void setPrecision(Double precision) {
		this.precision = precision;
	}

	public Double getPp() {
		return pp;
	}

	public void setPp(Double pp) {
		this.pp = pp;
	}

	public Boolean getEfecto() {
		return efecto;
	}

	public void setEfecto(Boolean efecto) {
		this.efecto = efecto;
	}

	public TipoPokemon getTipo() {
		return tipo;
	}

	public void setTipo(TipoPokemon tipo) {
		this.tipo = tipo;
	}
	
	
	
	
	
	
	
}
