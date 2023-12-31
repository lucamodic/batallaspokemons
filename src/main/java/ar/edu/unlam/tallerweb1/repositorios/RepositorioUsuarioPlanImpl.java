package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Plan;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.UsuarioPlan;
import ar.edu.unlam.tallerweb1.modelo.UsuarioPokemon;

@Repository("repositorioUsuarioPlan")
public class RepositorioUsuarioPlanImpl implements RepositorioUsuarioPlan {

	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioUsuarioPlanImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public UsuarioPlan buscarPorUsuario(Long idUsuario) {
		return (UsuarioPlan) this.sessionFactory.getCurrentSession().createCriteria(UsuarioPlan.class)
				.add(Restrictions.eq("usuario.id", idUsuario)).uniqueResult();
	}

	@Override
	public void asignarPlanAUsuario(Usuario usuario, Plan plan) {
		this.sessionFactory.getCurrentSession().save(new UsuarioPlan().withUsuario(usuario).withPlan(plan));
	}

	@Override
	public void guardar(UsuarioPlan usuarioPlan) {
		this.sessionFactory.getCurrentSession().save(usuarioPlan);
	}
	
	@Override
	public void darDeBajaElPlan(UsuarioPlan up) {
		sessionFactory.getCurrentSession().delete(up);
	}

	@Override
	public void modificar(UsuarioPlan usuarioPlan) {
		this.sessionFactory.getCurrentSession().update(usuarioPlan);
	}

	@Override
	public List<UsuarioPlan> traerTodos() {
		return (List<UsuarioPlan>) this.sessionFactory.getCurrentSession()
				.createCriteria(UsuarioPlan.class).list();
	}

}
