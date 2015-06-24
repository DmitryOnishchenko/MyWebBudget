package com.electdead.webbudget.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.electdead.webbudget.model.Account;
import com.electdead.webbudget.model.User;

@Transactional
public class AccountDAOImpl implements AccountDAO {
    private SessionFactory sessionFactory;
    
    public AccountDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public List<Account> getAllByUser(User user) {
        @SuppressWarnings("unchecked")
        List<Account> listAccounts = sessionFactory.getCurrentSession()
                    .createCriteria(Account.class)
                    .add(Restrictions.eq("user", user))
                    .addOrder(Order.asc("name"))
                    .list();
        
        return listAccounts;
    }
    
    @Override
    public Account getById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        
        Account account = (Account) session.get(Account.class, id);
        
        return account;
    }
    
    @Override
    public Account getByName(String name, User user) {
    	Account account = null;
    	
        @SuppressWarnings("unchecked")
        List<Account> listAccounts = sessionFactory.getCurrentSession()
                    .createCriteria(Account.class)
                    .add(Restrictions.eq("name", name))
                    .add(Restrictions.eq("user", user))
                    .list();
        
        if (!listAccounts.isEmpty()) {
        	account = listAccounts.get(0);
        }
        
        return account;
    }
    
    @Override
    public void save(Account account) {
        Session session = sessionFactory.getCurrentSession();
        
        session.saveOrUpdate(account);
    }
    
    @Override
    public void edit(Account account) {
        Session session = sessionFactory.getCurrentSession();
        
        Account existingAccount = (Account) session.get(Account.class, account.getAccountId());
    }

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}
}
