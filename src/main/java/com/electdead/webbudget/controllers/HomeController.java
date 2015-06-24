package com.electdead.webbudget.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.electdead.webbudget.DAO.AccountDAO;
import com.electdead.webbudget.DAO.CategoryDAO;
import com.electdead.webbudget.model.Account;
import com.electdead.webbudget.model.Category;
import com.electdead.webbudget.model.User;
import com.google.common.base.Joiner;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	private static final Logger logger = LogManager.getLogger(HomeController.class.getName());
	
	@Autowired
	private AccountDAO accountDao;
	
	@Autowired
	private CategoryDAO categoryDao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHome(HttpServletRequest request, Model model) {
		
		logger.debug("Request: home.GET");
		
		HttpSession session = request.getSession();
		
		if (session == null) {
			logger.debug("User is not authorized. Redirect to login.jsp");
			
			return "redirect:/login";
		}
		
		User user = (User) session.getAttribute("user");
		List<Account> accounts = SessionUtils.getAccountsFromSession(session);
		List<Category> categories = SessionUtils.getCategoriesFromSession(session);
		
		if (accounts == null) {
			double totalBalance = 0;
			
			if (logger.isDebugEnabled()) {
				logger.debug(Joiner.on("").join("Load accounts from DB for user: ", user));
			}
			
			accounts = accountDao.getAllByUser(user);
			
			for (Account account : accounts) {
				totalBalance += account.getMoney();
			}
			
			session.setAttribute("accounts", accounts);
			session.setAttribute("totalBalance", totalBalance);
		}
		
		if (categories == null) {
			if (logger.isDebugEnabled()) {
				logger.debug(Joiner.on("").join("Load categories from DB for user: ", user));
			}
			
			categories = categoryDao.getAllByUser(user);
			
			session.setAttribute("categories", categories);
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug(Joiner.on(' ').join(
					"All is fine for User: id", user.getUserId(), user.getLogin(), ". Return home.jsp"));
		}
		
		return "home";
	}
}
