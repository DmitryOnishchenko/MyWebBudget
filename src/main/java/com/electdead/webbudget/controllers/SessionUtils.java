package com.electdead.webbudget.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.electdead.webbudget.model.Account;
import com.electdead.webbudget.model.Category;
import com.electdead.webbudget.model.Transaction;
import com.google.common.base.Joiner;

public abstract class SessionUtils {
	public static final String AUTH_ATTR_NAME = "auth";
	public static final int SESSION_INTERVAL = 60 * 15;
	
	private static final Logger logger = LogManager.getLogger(SessionUtils.class.getName());
	
	public static HttpSession getNewSession(HttpServletRequest request) {
		logger.entry();
		
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(SESSION_INTERVAL);
		
		return session;
	}
	
	public static Boolean isAuthorized(HttpSession session) {
		logger.entry();
		
		Boolean authorized = (Boolean) session.getAttribute(AUTH_ATTR_NAME);
		
		if (authorized == null || authorized.equals(false)) {
			logger.exit(false);
			return false;
		}
		
		logger.exit(true);
		return true;
	}
	
	public static List<Account> getAccountsFromSession(HttpSession session) {
		logger.trace("SESSION: Getting accounts");
		@SuppressWarnings("unchecked")
		List<Account> accounts = (List<Account>) session.getAttribute("accounts");
		
		return accounts;
	}
	
	public static List<Transaction> getTransactionsFromSession(HttpSession session) {
		logger.trace("SESSION: Getting transactions");
		@SuppressWarnings("unchecked")
		List<Transaction> transactions = (List<Transaction>) session.getAttribute("transactions");
		
		return transactions;
	}
	
	public static List<Category> getCategoriesFromSession(HttpSession session) {
		logger.trace("SESSION: Getting categories");
		@SuppressWarnings("unchecked")
		List<Category> categories = (List<Category>) session.getAttribute("categories");
		
		return categories;
	}
	
	public static Account findAccountInSession(HttpSession session, int accountId)
		throws Exception {
		logger.trace("SESSION: Try to find account with id:" + accountId);
		@SuppressWarnings("unchecked")
		List<Account> accounts = (List<Account>) session.getAttribute("accounts");
		
		for (Account account : accounts) {
			if (account.getAccountId() == accountId) {
				logger.exit(account);
				return account;
			}
		}
		
		String exceptionMessage = Joiner.on(' ').join("FATAL: Account with ID:", accountId, "must exist!");
		
		logger.log(Level.ERROR, exceptionMessage);
		throw new Exception(exceptionMessage);
	}

	public static Category findCategoryInSession(HttpSession session, int categoryId)
		throws Exception {
		logger.trace("SESSION: Try to find category with id:" + categoryId);
		@SuppressWarnings("unchecked")
		List<Category> categories = (List<Category>) session.getAttribute("categories");
		
		for (Category category : categories) {
			if (category.getCategoryId() == categoryId) {
				logger.exit(category);
				return category;
			}
		}
		
		String exceptionMessage = Joiner.on(' ').join("ERROR: Category with ID:", categoryId, "must exist!");
		
		logger.log(Level.ERROR, exceptionMessage);
		throw new Exception(exceptionMessage);
	}
	
	public static Transaction findTransactionInSession(HttpSession session, int transactionId) {
			logger.trace("SESSION: Try to find transaction with id:" + transactionId);
			@SuppressWarnings("unchecked")
			List<Transaction> transactions = (List<Transaction>) session.getAttribute("transactions");
			
			for (Transaction transaction : transactions) {
				if (transaction.getTransactionId() == transactionId) {
					logger.exit(transaction);
					return transaction;
				}
			}
			
			return null;
		}
}
