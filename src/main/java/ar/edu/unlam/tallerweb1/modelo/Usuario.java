package ar.edu.unlam.tallerweb1.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

// Clase que modela el concepto de Usuario, la anotacion @Entity le avisa a hibernate que esta clase es persistible
// el paquete ar.edu.unlam.tallerweb1.modelo esta indicado en el archivo hibernateCOntext.xml para que hibernate
// busque entities en el
@Entity
public class Usuario {

	// La anotacion id indica que este atributo es el utilizado como clave primaria de la entity, se indica que el valor es autogenerado.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// para el resto de los atributo no se usan anotaciones entonces se usa el default de hibernate: la columna se llama igual que
	// el atributo, la misma admite nulos, y el tipo de dato se deduce del tipo de dato de java.
	private String usuario;
	private String email;
	private String password;
	private String fotoPerfil;
	private Boolean esAdmin = false;
	private Boolean principiante = true;
	private int puntos = 300;
	private Integer pokemonedas = 0;
	private Integer tiradaUltraball = 0;
	private Integer tiradaMasterball = 0;
	
	//pitty
	private Integer cantTiradasComunes = 0;
	private Integer cantTiradasTotales = 0;

	private byte[] salt; //Sirve para guardar hashes seguros en lugar de contrase�as en la base de datos, no est� implementado
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<UsuarioPokemon> pokemons;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public Boolean getEsAdmin() {
		return esAdmin;
	}
	public void setEsAdmin(Boolean esAdmin) {
		this.esAdmin = esAdmin;
	}
	public byte[] getSalt() {
		return salt;
	}
	public void setSalt(byte[] salt) {
		this.salt = salt;
	}
	public List<UsuarioPokemon> getPokemons() {
		return pokemons;
	}

	public void setPokemons(List<UsuarioPokemon> pokemons) {
		this.pokemons = pokemons;
	}
	
	public Integer getPuntos() {
		return puntos;
	}
	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}

	public Boolean getPrincipiante() {
		return principiante;
	}
	public void setPrincipiante(Boolean principiante) {
		this.principiante = principiante;
	}

	public Integer getPokemonedas() {
		return pokemonedas;
	}
	public void setPokemonedas(Integer pokemonedas) {
		this.pokemonedas = pokemonedas;
	}
	public Integer getCantTiradasComunes() {
		return cantTiradasComunes;
	}
	public void setCantTiradasComunes(Integer cantTiradas) {
		this.cantTiradasComunes = cantTiradas;
	}
	public Integer getCantTiradasTotales() {
		return cantTiradasTotales;
	}
	public void setCantTiradasTotales(Integer cantTiradasTotales) {
		this.cantTiradasTotales = cantTiradasTotales;
	}
	public String getFotoPerfil() {
		return fotoPerfil;
	}
	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}
	public Integer getTiradaUltraball() {
		return tiradaUltraball;
	}
	public void setTiradaUltraball(Integer tiradaUltraball) {
		this.tiradaUltraball = tiradaUltraball;
	}
	public Integer getTiradaMasterball() {
		return tiradaMasterball;
	}
	public void setTiradaMasterball(Integer tiradaMasterball) {
		this.tiradaMasterball = tiradaMasterball;
	}
	
	
	
	
}

