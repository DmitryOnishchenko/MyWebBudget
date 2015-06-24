package com.electdead.webbudget.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.electdead.webbudget.model.User;

@Transactional
public class UserDAOImpl implements UserDAO {
	private SessionFactory sessionFactory;
	
	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<User> getAll() {
		@SuppressWarnings("unchecked")
		List<User> listUsers = sessionFactory.getCurrentSession()
				.createCriteria(User.class).list();
		
		return listUsers;
	}

	@Override
	public User getById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		
		User user = (User) session.get(User.class, id);
		
		return user;
	}
	
	@Override
	public User getByLogin(String login) {
		User user = null;
		
		@SuppressWarnings("unchecked")
		List<User> listUsers = sessionFactory.getCurrentSession()
				.createCriteria(User.class)
				.add(Restrictions.eq("login", login)).list();
		
		if (!listUsers.isEmpty()) {
			user = listUsers.get(0);
		}
		
		return user;
	}
	
	@Override
	public void create(User user) {
		Session session = sessionFactory.getCurrentSession();
		
		session.save(user);
	}

	@Override
	public void edit(User user) {
		Session session = sessionFactory.getCurrentSession();
		
		User existingUser = (User) session.get(User.class, user.getUserId());
		
		existingUser.setLogin(user.getLogin());
		existingUser.setPassword(user.getPassword());
		existingUser.setEmail(user.getEmail());
		
		session.save(existingUser);
	}

	@Override
	public void delete(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		
		User user = (User) session.get(User.class, id);
		
		session.delete(user);
	}
	
}
