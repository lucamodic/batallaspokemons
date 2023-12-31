package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PokemonBatalla {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	private Pokemon pokemon;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Batalla batalla;

	private Boolean debilitado;
	private String entrenador;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pokemon getPokemon() {
		return pokemon;
	}

	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}

	public Batalla getBatalla() {
		return batalla;
	}

	public void setBatalla(Batalla batalla) {
		this.batalla = batalla;
	}

	public Boolean getDebilitado() {
		return debilitado;
	}

	public void setDebilitado(Boolean debilitado) {
		this.debilitado = debilitado;
	}

	public String getEntrenador() {
		return entrenador;
	}

	public void setEntrenador(String entrenador) {
		this.entrenador = entrenador;
	}

	public PokemonBatalla withPokemon(Pokemon pokemon) {
		this.setPokemon(pokemon);
		return this;
	}

	public PokemonBatalla withBatalla(Batalla batalla) {
		this.setBatalla(batalla);
		return this;
	}

	public PokemonBatalla withDebilitado(Boolean debilitado) {
		this.setDebilitado(debilitado);
		return this;
	}

	public PokemonBatalla withEntrenador(String entrenador) {
		this.setEntrenador(entrenador);
		return this;
	}
}
