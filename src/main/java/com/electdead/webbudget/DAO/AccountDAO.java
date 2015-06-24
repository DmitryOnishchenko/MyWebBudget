package com.electdead.webbudget.DAO;

import java.util.List;

import com.electdead.webbudget.model.Account;
import com.electdead.webbudget.model.User;

public interface AccountDAO {
    public List<Account> getAllByUser(User user);
    public Account getByName(String name, User user);
    public Account getById(Integer id);
    public void save(Account account);
    public void edit(Account account);
    public void delete(Integer id);
}