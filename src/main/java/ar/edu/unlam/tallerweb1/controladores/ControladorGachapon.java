package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Plan;
import ar.edu.unlam.tallerweb1.modelo.Pokemon;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.UsuarioPokemon;
import ar.edu.unlam.tallerweb1.servicios.ServicioGachapon;
import ar.edu.unlam.tallerweb1.servicios.ServicioPokemon;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuarioPlan;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuarioPokemon;

@Controller
public class ControladorGachapon {

	private ServicioUsuario servicioUsuario;
	private ServicioUsuarioPokemon servicioUsuarioPokemon;
	private ServicioGachapon servicioGachapon;
	private ServicioUsuarioPlan servicioUsuarioPlan;
	private ServicioPokemon servicioPokemon;

	@Autowired
	public ControladorGachapon(ServicioGachapon servicioGachapon, ServicioUsuario servicioUsuario,
			ServicioUsuarioPokemon servicioUsuarioPokemon, ServicioUsuarioPlan servicioUsuarioPlan, ServicioPokemon servicioPokemon) {

		this.servicioUsuario = servicioUsuario;
		this.servicioUsuarioPokemon = servicioUsuarioPokemon;
		this.servicioGachapon = servicioGachapon;
		this.servicioUsuarioPlan = servicioUsuarioPlan;
		this.servicioPokemon = servicioPokemon;
	}

	@RequestMapping("/gachapon")
	public ModelAndView gachapon(HttpServletRequest request) {
		if (request.getSession().getAttribute("usuario") == null) {
			return new ModelAndView("redirect:/login");
		}
		Long id = (Long) request.getSession().getAttribute("id");
		Usuario usuario = servicioUsuario.buscar(id);
		ModelMap model = new ModelMap();
		if (servicioUsuarioPlan.buscarPlanPorUsuario(id) != null) {
			Plan plan = servicioUsuarioPlan.buscarPlanPorUsuario(id).getPlan();
			model.put("plan", plan);
		}
		model.put("tiradas", 30 - usuario.getCantTiradasTotales());
		model.put("usuario", usuario);
		model.put("pokemons", this.servicioUsuarioPokemon.obtenerListaDePokemonSinPokemonUsuario(usuario.getId()));
		
		return new ModelAndView("gachapon", model);
	}
	
	@RequestMapping(path="/gachapon-comprar", method = RequestMethod.POST )
	public ModelAndView gachaponTienda(HttpServletRequest request) {
		if (request.getSession().getAttribute("usuario") == null) {
			return new ModelAndView("redirect:/login");
		}
		if(request.getParameter("idPokemon") == null) {
			return new ModelAndView("redirect:/home");
		}
		Long idPokemon = Long.parseLong(request.getParameter("idPokemon"));
		Long id = (Long) request.getSession().getAttribute("id");
		Usuario usuario = servicioUsuario.buscar(id);
		Pokemon pokemon = this.servicioPokemon.buscar(idPokemon);
		ModelMap model = new ModelMap();
		if (servicioUsuarioPlan.buscarPlanPorUsuario(id) != null) {
			Plan plan = servicioUsuarioPlan.buscarPlanPorUsuario(id).getPlan();
			model.put("plan", plan);
		}
		if (!this.servicioUsuario.restarPokemonedas(pokemon,usuario)) {
			model.put("errorPokemonedas", "Pokemonedas Insuficientes");
			model.put("tiradas", 30 - usuario.getCantTiradasTotales());
			model.put("usuario", usuario);
			model.put("pokemons", this.servicioUsuarioPokemon.obtenerListaDePokemonSinPokemonUsuario(usuario.getId()));
			return new ModelAndView("gachapon", model);
		} else {
			this.servicioUsuarioPokemon.guardarUsuarioPokemon(
					new UsuarioPokemon().withUsuario(usuario).withPokemon(pokemon), id, pokemon.getId(), usuario, pokemon);
			model.put("comprado", "Compraste a " + pokemon.getNombre());
		}
		
		model.put("tiradas", 30 - usuario.getCantTiradasTotales());
		model.put("usuario", usuario);
		model.put("pokemons", this.servicioUsuarioPokemon.obtenerListaDePokemonSinPokemonUsuario(usuario.getId()));
		
		
		return new ModelAndView("gachapon", model);
	}

	@RequestMapping(path = "gachapon-resultado", method = RequestMethod.POST)
	public ModelAndView gachaponResultado(HttpServletRequest request) {
		if (request.getSession().getAttribute("usuario") == null) {
			return new ModelAndView("redirect:/login");
		}
		ModelMap model = new ModelMap();
		Integer monedas = Integer.parseInt(request.getParameter("monedas"));
		Long id = (Long) request.getSession().getAttribute("id");
		Usuario usuario = servicioUsuario.buscar(id);

		if (!this.servicioUsuario.restarPuntos(monedas, usuario)) {
			model.put("error", "Puntos Insuficientes");
			model.put("tiradas", 30 - usuario.getCantTiradasTotales());
			model.put("usuario", usuario);
			model.put("pokemons", this.servicioUsuarioPokemon.obtenerListaDePokemonSinPokemonUsuario(usuario.getId()));
			return new ModelAndView("gachapon", model);
		}
		// repetidos
		Pokemon pokemon = this.servicioGachapon.tiradaGachapon(monedas, usuario);
		model.put("puntos", usuario.getPuntos());
		model.put("pokemon", pokemon);
		model.put("monedas", monedas);
		if (!usuario.getPrincipiante()) {
			request.getSession().removeAttribute("principiante");
		}
		if (this.servicioUsuarioPokemon.guardarUsuarioPokemon(
				new UsuarioPokemon().withUsuario(usuario).withPokemon(pokemon), id, pokemon.getId(), usuario,
				pokemon)) {
			return new ModelAndView("gachapon-resultado", model);
		} else {
			Integer pokemonedas = servicioUsuario.sumarpokeMonedas(pokemon.getRareza(), usuario);
			String error = "Obtuviste " + pokemonedas + " Pokemonedas por pokemon repetido, tus pokeMonedas son: ";
			model.put("repetido", error);
			model.put("pokemonedas", usuario.getPokemonedas());
			return new ModelAndView("gachapon-resultado", model);
		}
	}
}