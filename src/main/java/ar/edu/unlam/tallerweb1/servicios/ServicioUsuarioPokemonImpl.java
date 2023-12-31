package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.exceptions.PokemonNoObtenidoException;
import ar.edu.unlam.tallerweb1.modelo.Pokemon;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.UsuarioPokemon;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuarioPokemon;

@Service("servicioUsuarioPokemon")
@Transactional
public class ServicioUsuarioPokemonImpl implements ServicioUsuarioPokemon {

	@Inject
	private RepositorioUsuarioPokemon repositorioUsuarioPokemon;
	@Inject
	private ServicioAtaquePokemon servicioAtaquePokemon;
	@Inject
	private ServicioUsuarioAtaquePokemon servicioUsuarioAtaquePokemon;

	// repetidos
	@Override
	public Boolean guardarUsuarioPokemon(UsuarioPokemon usuarioPokemon, Long idUsuario, Long idPokemon, Usuario usuario,
			Pokemon pokemon) {
		if (this.repositorioUsuarioPokemon.buscarUsuarioPokemon(idUsuario, idPokemon) == null) {
			this.servicioAtaquePokemon.obtenerLista(idPokemon)
					.forEach(x -> this.servicioUsuarioAtaquePokemon.guardar(x, usuario));
			this.repositorioUsuarioPokemon.guardarUsuarioPokemon(usuarioPokemon);
			return true;
		}
		return false;
	}

	public void guardarUsuarioPokemon(UsuarioPokemon usuarioPokemon) {
		this.servicioAtaquePokemon.obtenerLista(usuarioPokemon.getPokemon().getId())
				.forEach(x -> this.servicioUsuarioAtaquePokemon.guardar(x, usuarioPokemon.getUsuario()));
		this.repositorioUsuarioPokemon.guardarUsuarioPokemon(usuarioPokemon);
	}

	@Override
	public List<UsuarioPokemon> obtenerListaDeUsuarioPokemon(Long idUsuario) {
		return this.repositorioUsuarioPokemon.buscarPokemon(idUsuario);
	}
	@Override
	public List<Pokemon> obtenerListaDePokemonSinPokemonUsuario(Long idUsuario) {
		return this.repositorioUsuarioPokemon.getPokemonSinUsuarioPokemon(idUsuario);
	}

	@Override
	public void borrarUsuarioDeUnPokemon(Long idUsuario) {
		// TODO Auto-generated method stub

	}

	@Override
	public void guardarEquipo(UsuarioPokemon usuarioPokemon) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Pokemon> buscarPokemons(List<UsuarioPokemon> lista) {
		ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
		for (UsuarioPokemon pokemon : lista) {
			pokemons.add(pokemon.getPokemon());
		}
		return pokemons;
	}

	@Override
	public UsuarioPokemon buscarPokemonUsuario(Long idPokemon, Long idUsuario) throws PokemonNoObtenidoException {
		UsuarioPokemon pokemonUsuario = this.repositorioUsuarioPokemon.buscarUsuarioPokemon(idUsuario, idPokemon);
		if (pokemonUsuario != null) {
			return pokemonUsuario;
		} else {
			throw new PokemonNoObtenidoException("El usuario no posee el pokemon buscado");
		}
	}

	@Override
	public Pokemon buscarPokemon(Long idPokemon, Long idUsuario) throws PokemonNoObtenidoException {
		UsuarioPokemon pokemonUsuario = this.repositorioUsuarioPokemon.buscarUsuarioPokemon(idUsuario, idPokemon);
		if (pokemonUsuario != null) {
			return pokemonUsuario.getPokemon();
		} else {
			throw new PokemonNoObtenidoException("El usuario no posee el pokemon buscado");
		}
	}
}