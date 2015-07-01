package com.electdead.webbudget.controllers.editors;

import java.beans.PropertyEditorSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.electdead.webbudget.controllers.SessionUtils;
import com.electdead.webbudget.model.Account;

public class AccountEditor extends PropertyEditorSupport {
    private HttpServletRequest request;
    
    public AccountEditor(HttpServletRequest request) {
        this.request = request;
    }
    
    @Override
    public void setAsText(String accountIdString) throws IllegalArgumentException {
        int accountId = Integer.parseInt(accountIdString);
        HttpSession session = request.getSession(true);
        
		try {
			Account account = SessionUtils.findAccountInSession(session, accountId);
			setValue(account);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
}
