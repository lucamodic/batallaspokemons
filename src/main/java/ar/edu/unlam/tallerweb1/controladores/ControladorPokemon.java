package ar.edu.unlam.tallerweb1.controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.exceptions.NombreExistenteException;
import ar.edu.unlam.tallerweb1.exceptions.PermisosInsuficientesException;
import ar.edu.unlam.tallerweb1.exceptions.SpriteNoIngresadoException;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.*;

@Controller
public class ControladorPokemon {

	private ServicioPokemon servicioPokemon;
	private ServicioAtaque servicioAtaque;
	private ServicioAtaquePokemon servicioAtaquePokemon;

	@Autowired
	public ControladorPokemon(ServicioPokemon servicioPokemon, ServicioAtaque servicioAtaque,
			ServicioAtaquePokemon servicioAtaquePokemon) {
		this.servicioPokemon = servicioPokemon;
		this.servicioAtaque = servicioAtaque;
		this.servicioAtaquePokemon = servicioAtaquePokemon;
	}

	@RequestMapping("/crear-pokemon")
	public ModelAndView crearPokemon(HttpServletRequest request) {

		if (request.getSession().getAttribute("usuario") == null
				|| !(Boolean) request.getSession().getAttribute("esAdmin")) {
			return new ModelAndView("redirect:/home");
		}
		ModelMap model = new ModelMap();
		model.put("pokemon", new Pokemon());
		model.put("listaAtaques", obtenerListaDeAtaques());
		return new ModelAndView("crear-pokemon", model);
	}

	@RequestMapping(path = "/crear-pokemon", method = RequestMethod.POST)
	public ModelAndView crearPokemon(@ModelAttribute Pokemon pokemon, @RequestParam("ataquesLista") List<Long> ataquesLista,
			@RequestParam MultipartFile frente, @RequestParam MultipartFile dorso, HttpServletRequest request) {

		if (request.getSession().getAttribute("usuario") == null
				|| !(Boolean) request.getSession().getAttribute("esAdmin")) {
			return new ModelAndView("redirect:/home");
		}
		try {
			this.servicioPokemon.guardarPokemon(pokemon, ataquesLista, frente, dorso);
			return new ModelAndView("redirect:/lista-pokemons");
		} catch (IOException | NombreExistenteException | SpriteNoIngresadoException ex) {
			ModelMap model = new ModelMap();
			List<Ataque> ataquesSeleccionados = new ArrayList<>();
			ataquesLista.forEach(x -> ataquesSeleccionados.add(this.servicioAtaque.buscarAtaque(x)));
			pokemon.setAtaques(ataquesSeleccionados);
			model.put("error", ex.getMessage());
			model.put("listaAtaques", this.servicioAtaque.obtenerTodosLosAtaques());
			return new ModelAndView("crear-pokemon", model);
		}
	}

	@RequestMapping("/lista-pokemons")
	public ModelAndView listaPokemons(HttpServletRequest request) {

		if (request.getSession().getAttribute("usuario") == null
				|| !(Boolean) request.getSession().getAttribute("esAdmin")) {
			return new ModelAndView("redirect:/home");
		}
		ModelMap model = new ModelMap();
		model.put("listaPokemons", this.servicioPokemon.obtenerTodosLosPokemons());
		return new ModelAndView("lista-pokemons", model);
	}

	@RequestMapping("/modificar-pokemon")
	public ModelAndView modificarPokemon(@RequestParam Long id, HttpServletRequest request) {
		
		if (request.getSession().getAttribute("usuario") == null
				|| !(Boolean) request.getSession().getAttribute("esAdmin")) {
			return new ModelAndView("redirect:/home");
		}
		ModelMap model = new ModelMap();
		Pokemon pokemon = this.servicioPokemon.buscarPokemon(id);
		pokemon.setAtaques(this.servicioAtaquePokemon.obtenerListaDeAtaques(id));
		model.put("pokemon", pokemon);
		model.put("listaAtaques", this.servicioAtaque.obtenerTodosLosAtaques());
		return new ModelAndView("modificar-pokemon", model);
	}

	@RequestMapping(path = "/modificar-pokemon", method = RequestMethod.POST)
	public ModelAndView modificarPokemon(@ModelAttribute Pokemon pokemon, @RequestParam List<Long> ataquesLista,
			@RequestParam MultipartFile frente, @RequestParam MultipartFile dorso, @RequestParam String nombreAnterior,
			@RequestParam String frenteAnterior, @RequestParam String dorsoAnterior,
			@RequestParam List<Long> ataquesAprendidos, HttpServletRequest request) {

		if (request.getSession().getAttribute("usuario") == null
				|| !(Boolean) request.getSession().getAttribute("esAdmin")) {
			return new ModelAndView("redirect:/home");
		}
		pokemon.setImagenFrente(frenteAnterior);
		pokemon.setImagenDorso(dorsoAnterior);
		try {
			this.servicioPokemon.modificarPokemon(pokemon, ataquesLista, frente, dorso, nombreAnterior,
					ataquesAprendidos);
			return new ModelAndView("redirect:/lista-pokemons");
		} catch (Exception ex) {
			ModelMap model = new ModelMap();
			List<Ataque> ataquesSeleccionados = new ArrayList<>();
			ataquesLista.forEach(x -> ataquesSeleccionados.add(this.servicioAtaque.buscarAtaque(x)));
			pokemon.setAtaques(ataquesSeleccionados);
			model.put("error", ex);
			model.put("listaAtaques", this.servicioAtaque.obtenerTodosLosAtaques());
			return new ModelAndView("modificar-pokemon", model);
		}
	}

	@RequestMapping(path = "/borrar-pokemon", method = RequestMethod.POST)
	@ResponseBody
	public void borrarPokemon(@RequestParam String id, HttpServletRequest request)
			throws PermisosInsuficientesException {

		if (request.getSession().getAttribute("usuario") == null
				|| !(Boolean) request.getSession().getAttribute("esAdmin")) {
			throw new PermisosInsuficientesException();
		}
		this.servicioPokemon.borrarPokemon(Long.parseLong(id));
	}

	public List<Ataque> obtenerListaDeAtaques() {
		return this.servicioAtaque.obtenerTodosLosAtaques();
	}
}
