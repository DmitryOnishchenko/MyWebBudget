package com.electdead.mywebbudget.controllers.editors;

import java.beans.PropertyEditorSupport;

public class AccountEditor extends PropertyEditorSupport {
    private HttpServletRequest request;
    
    public AccountEditor(HttpServletRequest request) {
        this.request = request;
    }
    
    @Override
    public void setAsText(String accountId) throws IllegalArgumentException {
        int accountId = Integer.parseInt(accountId);
        HttpSession session = request.getSession(true);
        
        Account account = SessionUtils.findAccountInSession(session, accountId);
        
        setValue(account);
    }
    
}
