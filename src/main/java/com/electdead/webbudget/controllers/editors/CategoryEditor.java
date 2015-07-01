package com.electdead.webbudget.controllers.editors;

public class CategoryEditor extends PropertyEditorSupport {
    private HttpServletRequest request;
    
    public CategoryEditor(HttpServletRequest request) {
        this.request = request;
    }
    
    @Override
    public void setAsText(String categoryId) throws IllegalArgumentException {
        int categoryId = Integer.parseInt(categoryId);
        HttpSession session = request.getSession(true);
        
        Category category = SessionUtils.findCategoryInSession(session, categoryId);
        
        setValue(category);
    }
}
