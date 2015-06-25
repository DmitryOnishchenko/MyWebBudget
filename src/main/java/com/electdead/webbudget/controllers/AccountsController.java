package com.electdead.webbudget.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.electdead.webbudget.DAO.AccountDAO;
import com.electdead.webbudget.model.Account;
import com.electdead.webbudget.model.User;
import com.google.common.base.Joiner;

@Controller
public class AccountsController {
	public static final Logger logger = LogManager.getLogger(AccountsController.class.getName());
	
	@Autowired
	private AccountDAO accountDao;
	
	@RequestMapping(value = "/accounts", method = RequestMethod.GET)
	public String getAccounts(HttpServletRequest request, Model model) {
		
		logger.debug("Request: accounts.GET");
		
		HttpSession session = request.getSession(true);
		
		if (!SessionUtils.isAuthorized(session)) {
			logger.debug("User is not authorized. Redirect to login.jsp");
			model.addAttribute("user", new User());
			
			return "redirect:/login";
		}
		
		model.addAttribute("newAccount", new Account());
		
		logger.debug("Return accounts.jsp");
		
		return "accounts";
	}
	
	@RequestMapping(value = "/accounts", method = RequestMethod.POST)
	public String addAccount(HttpServletRequest request, 
			@ModelAttribute("newAccount") Account account, Model model) {
		
		logger.debug("Request: accounts.POST");
		
		HttpSession session = request.getSession(true);
		
		User user = (User) session.getAttribute("user");
		
		Account existingAccount = (Account) accountDao.getByName(account.getName(), user);
		
		if (existingAccount != null) {
			logger.debug("Account is already exists. Return accounts.jsp");
			model.addAttribute("accountNameError", true);
			
			return "accounts";
		}
		
		logger.debug("Try to save the account in DB and calculate sums");
		
		account.setUser(user);
		accountDao.save(account);
		
		double totalBalance = (Double) session.getAttribute("totalBalance");
		totalBalance += account.getMoney();
		session.setAttribute("totalBalance", totalBalance);
		
		List<Account> accounts = SessionUtils.getAccountsFromSession(session);
		accounts.add(account);
		session.setAttribute("accounts", accounts);
		
		model.addAttribute("newAccount", new Account());
		
		if (logger.isDebugEnabled()) {
			logger.debug(Joiner.on(' ').join(
					"All is fine. Account:", account, " created. Return accounts.jsp"));			
		}
		
		return "accounts";
	}
}
