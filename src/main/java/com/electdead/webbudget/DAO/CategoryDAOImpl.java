package com.electdead.webbudget.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.electdead.webbudget.model.Category;
import com.electdead.webbudget.model.User;

@Transactional
public class CategoryDAOImpl implements CategoryDAO {
	private SessionFactory sessionFactory;
    
    public CategoryDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	@Override
	public List<Category> getAllByUser(User user) {
		@SuppressWarnings("unchecked")
        List<Category> listCategories = sessionFactory.getCurrentSession()
                    .createCriteria(Category.class)
                    .add(Restrictions.eq("user", user))
                    .addOrder(Order.asc("name"))
                    .list();
        
        return listCategories;
	}

	@Override
	public Category getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
    public Category getByName(String name, User user) {
    	Category category = null;
    	
        @SuppressWarnings("unchecked")
        List<Category> listCategories = sessionFactory.getCurrentSession()
                    .createCriteria(Category.class)
                    .add(Restrictions.eq("name", name))
                    .add(Restrictions.eq("user", user))
                    .list();
        
        if (!listCategories.isEmpty()) {
        	category = listCategories.get(0);
        }
        
        return category;
    }

	@Override
	public void save(Category category) {
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(category);
	}

	@Override
	public void edit(Category category) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
