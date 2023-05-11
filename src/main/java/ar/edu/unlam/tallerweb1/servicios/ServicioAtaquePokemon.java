package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.AtaquePokemon;

public interface ServicioAtaquePokemon {
	
	void guardarAtaque(AtaquePokemon ataquePokemon);

	List<AtaquePokemon> obtenerListaDeAtaquePokemon(Long idPokemon);

	void borrarAtaquesDeUnPokemon(Long idPokemon);

}