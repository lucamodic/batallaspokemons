package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Batalla;

public interface RepositorioBatalla {

	void guardar(Batalla batalla);

	List<Batalla> obtenerBatallasUsuario(Long idUsuario);

}
