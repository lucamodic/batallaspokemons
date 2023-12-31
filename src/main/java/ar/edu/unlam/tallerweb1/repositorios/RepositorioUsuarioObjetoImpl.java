package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Objeto;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.UsuarioObjeto;

@Repository("repositorioUsuarioObjeto")
public class RepositorioUsuarioObjetoImpl implements RepositorioUsuarioObjeto {

	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioUsuarioObjetoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<UsuarioObjeto> buscarObjetos(Long idUsuario) {
		return (List<UsuarioObjeto>) this.sessionFactory.getCurrentSession().createCriteria(UsuarioObjeto.class)
				.add(Restrictions.eq("usuario.id", idUsuario)).list();
	}

	@Override
	public void guardar(UsuarioObjeto usuarioObjeto) {
		this.sessionFactory.getCurrentSession().save(usuarioObjeto);

	}

	@Override
	public UsuarioObjeto buscar(Long idObjeto, Long idUsuario) {
		return (UsuarioObjeto) this.sessionFactory.getCurrentSession().createCriteria(UsuarioObjeto.class)
				.add(Restrictions.eq("objeto.id", idObjeto)).add(Restrictions.eq("usuario.id", idUsuario))
				.uniqueResult();
	}
}
