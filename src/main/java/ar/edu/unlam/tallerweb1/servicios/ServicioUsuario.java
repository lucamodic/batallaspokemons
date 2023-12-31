package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.exceptions.PuntosInsuficientesException;
import ar.edu.unlam.tallerweb1.modelo.Pokemon;
import ar.edu.unlam.tallerweb1.modelo.RarezaPokemon;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioUsuario {

	void guardarEquipo(String[] pokemons,Long id);

	Usuario buscar(Long idUsuario);
	
	void comprarObjetos(Long idUsuario, List<Integer> cantidad) throws PuntosInsuficientesException;
	
	public List<Pokemon> obtenerListaDePokemons(Long idUsuario);

	Boolean restarPuntos(Integer monedas, Usuario usuario);

	void sumarPuntos(Long idUsuario, Integer puntos);

	Integer sumarpokeMonedas(RarezaPokemon rarezaPokemon, Usuario usuario);
	
	void sumarTiradasComunes(Usuario usuario);
	
	void sumarTiradasTotales(Usuario usuario);

	void reiniciarTiradasComunes(Usuario usuario);
	
	void reiniciarTiradasTotales(Usuario usuario);

	void sacarPrincipiante(Usuario usuario);

	void asignarObjetos(Usuario usuarioNuevo);
	
	void modificar(Usuario usuario);

	Boolean restarPokemonedas(Pokemon pokemon, Usuario usuario);
}