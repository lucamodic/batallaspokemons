package ar.edu.unlam.tallerweb1.modelo;

import java.util.List;

import javax.persistence.*;

@Entity
public class AtaquePokemon {
	
	

	public AtaquePokemon(Ataque ataque, Pokemon pokemon) {
		this.ataque = ataque;
		this.pokemon = pokemon;
	}
	
	public AtaquePokemon() {
		// Default
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Ataque ataque;
	@ManyToOne
	private Pokemon pokemon;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Ataque getAtaque() {
		return ataque;
	}
	public void setAtaque(Ataque ataque) {
		this.ataque = ataque;
	}
	public Pokemon getPokemon() {
		return pokemon;
	}
	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}
	
}