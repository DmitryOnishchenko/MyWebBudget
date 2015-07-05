package com.electdead.webbudget.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.electdead.webbudget.model.Account;
import com.electdead.webbudget.model.Category;
import com.electdead.webbudget.model.Transaction;
import com.electdead.webbudget.model.User;

@Transactional
public class TransactionDAOImpl implements TransactionDAO {
	private SessionFactory sessionFactory;
    
    public TransactionDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	@Override
	public List<Transaction> getAllByUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "FROM Transaction AS ta WHERE ta.account.user = :user ORDER BY date desc";
		Query hqlQuery = session.createQuery(hql);
		hqlQuery.setParameter("user", user);
		
		@SuppressWarnings("unchecked")
		List<Transaction> transactionsList = hqlQuery.list();
		
		return transactionsList;
	}

	@Override
	public List<Transaction> getAllByAccount(User user, Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getAllByCategory(User user, Category category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getAllByAccountAndCategory(User user,
			Account account, Category category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Transaction transaction) {
		Session session = sessionFactory.getCurrentSession();
		Account account = (Account) session.get(Account.class, transaction.getAccount().getAccountId());
		
		session.save(transaction);
		
		account.setMoney(account.getMoney() + transaction.getAmount());
		session.saveOrUpdate(account);
	}

	@Override
	public void edit(Transaction transaction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		Transaction transaction = (Transaction) session.get(Transaction.class, id);
		
		session.delete(transaction);
	}

}
