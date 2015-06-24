package com.electdead.webbudget.DAO;

import java.util.List;

import com.electdead.webbudget.model.Category;
import com.electdead.webbudget.model.User;

public interface CategoryDAO {
    public List<Category> getAllByUser(User user);
    public Category getById(Integer id);
    public Category getByName(String name, User user);
    public void save(Category category);
    public void edit(Category category);
    public void delete(Integer id);
}