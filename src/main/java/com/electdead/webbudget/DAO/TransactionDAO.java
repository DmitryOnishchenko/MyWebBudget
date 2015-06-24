package com.electdead.webbudget.DAO;

import java.util.List;

import com.electdead.webbudget.model.Account;
import com.electdead.webbudget.model.Category;
import com.electdead.webbudget.model.Transaction;
import com.electdead.webbudget.model.User;

public interface TransactionDAO {
    public List<Transaction> getAllByUser(User user);
    public List<Transaction> getAllByAccount(User user, Account account);
    public List<Transaction> getAllByCategory(User user, Category category);
    public List<Transaction> getAllByAccountAndCategory(User user, Account account, Category category);
    public Transaction getById(Integer id);
    public void create(Transaction transaction);
    public void edit(Transaction transaction);
    public Transaction deleteById(int id);
}