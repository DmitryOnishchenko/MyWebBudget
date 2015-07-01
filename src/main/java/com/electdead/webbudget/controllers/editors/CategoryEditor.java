package com.electdead.webbudget.controllers.editors;

import java.beans.PropertyEditorSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.electdead.webbudget.controllers.SessionUtils;
import com.electdead.webbudget.model.Category;

public class CategoryEditor extends PropertyEditorSupport {
    private HttpServletRequest request;
    
    public CategoryEditor(HttpServletRequest request) {
        this.request = request;
    }
    
    @Override
    public void setAsText(String categoryIdString) throws IllegalArgumentException {
        int categoryId = Integer.parseInt(categoryIdString);
        HttpSession session = request.getSession(true);
        
		try {
			Category category = SessionUtils.findCategoryInSession(session, categoryId);
			setValue(category);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
