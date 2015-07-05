package com.electdead.webbudget.DAO;

import java.util.List;

import com.electdead.webbudget.model.User;

public interface UserDAO {
	public List<User> getAll();
	public User getById(Integer id);
	public User getByLogin(String login);
	public int create(User user);
	public void edit(User user);
	public void delete(Integer id);
}
