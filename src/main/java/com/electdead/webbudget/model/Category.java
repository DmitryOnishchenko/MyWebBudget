package com.electdead.webbudget.model;

import com.google.common.base.Joiner;

public class Category {
    private int categoryId;
    private String name;
    private User user;
    
    public int getCategoryId() {
        return categoryId;
    }
    
    public String getName() {
        return name;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
	public String toString() {
		return Joiner.on("").join("Category.class [categoryId: ",
				categoryId, ", name: ", name, ", user: ", user, "]");
	}
}