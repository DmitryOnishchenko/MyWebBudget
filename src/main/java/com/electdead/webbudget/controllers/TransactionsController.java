package com.electdead.webbudget.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.electdead.webbudget.DAO.AccountDAO;
import com.electdead.webbudget.DAO.CategoryDAO;
import com.electdead.webbudget.DAO.TransactionDAO;
import com.electdead.webbudget.controllers.editors.AccountEditor;
import com.electdead.webbudget.controllers.editors.CategoryEditor;
import com.electdead.webbudget.model.Account;
import com.electdead.webbudget.model.Category;
import com.electdead.webbudget.model.Transaction;
import com.electdead.webbudget.model.User;

@Controller
public class TransactionsController {
	public static final Logger logger = LogManager.getLogger(TransactionsController.class.getName());
	
	@Autowired
	private AccountDAO accountDao;
	
	@Autowired
	private CategoryDAO categoryDao;
	
	@Autowired
	private TransactionDAO transactionDao;
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder, HttpServletRequest request) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		webDataBinder.registerCustomEditor(Date.class, "date", new CustomDateEditor(
				dateFormat, true));
		webDataBinder.registerCustomEditor(Account.class, "account", new AccountEditor(request));
		webDataBinder.registerCustomEditor(Category.class, "category", new CategoryEditor(request));
	}
	
	@RequestMapping(value = "/transactions", method = RequestMethod.GET)
	public String getTransactions(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		if (!SessionUtils.isAuthorized(session)) {
			logger.debug("User is not authorized. Redirect to login.jsp");
			
			return "redirect:/login";
		}
		
		User user = (User) session.getAttribute("user");
		
		if (SessionUtils.getTransactionsFromSession(session) == null) {
			List<Transaction> transactions = transactionDao.getAllByUser(user);
			session.setAttribute("transactions", transactions);
		}
		
		if (SessionUtils.getCategoriesFromSession(session) == null) {
			List<Category> categories = categoryDao.getAllByUser(user);
			session.setAttribute("categories", categories);
		}
		
		model.addAttribute("newTransaction", new Transaction());
		
		return "/transactions";
	}
	
	@RequestMapping(value = "/transactions", method = RequestMethod.POST)
	public String addTransaction(HttpServletRequest request,
			@ModelAttribute("newTransaction") Transaction newTransaction) {
		
		HttpSession session = request.getSession();
		if (!SessionUtils.isAuthorized(session)) {
			logger.debug("User is not authorized. Redirect to login.jsp");
			
			return "redirect:/login";
		}
		
		transactionDao.create(newTransaction);
		List<Transaction> transactions = SessionUtils.getTransactionsFromSession(session);
		transactions.add(newTransaction);
		
		Account account = newTransaction.getAccount();
		double currentMoney = account.getMoney();
		double transactionAmount = newTransaction.getAmount();
		account.setMoney(currentMoney + transactionAmount); 
		accountDao.save(account);
		
		double currentBalance = (Double) session.getAttribute("totalBalance");
		session.setAttribute("totalBalance", currentBalance + transactionAmount);
		
		return "transactions";
	}
	
	@RequestMapping(value = "/transactions/delete", method = RequestMethod.GET)
	public String deleteTransaction(HttpServletRequest request, @RequestParam("id") int transactionId) {
		
		HttpSession session = request.getSession();
		if (!SessionUtils.isAuthorized(session)) {
			logger.debug("User is not authorized. Redirect to login.jsp");
			
			return "redirect:/login";
		}
		
		transactionDao.deleteById(transactionId);
		
		List<Transaction> transactions = SessionUtils.getTransactionsFromSession(session);
		Transaction transaction = SessionUtils.findTransactionInSession(session, transactionId);
		transactions.remove(transaction);
		
		Account account = transaction.getAccount();
		double currentMoney = account.getMoney();
		double transactionAmount = transaction.getAmount() * -1;
		double accountTotal = currentMoney + transactionAmount;
		
		account.setMoney(accountTotal);
		
		accountDao.save(account);
		
		double currentBalance = (Double) session.getAttribute("totalBalance");
		session.setAttribute("totalBalance", currentBalance + transactionAmount);
		
		return "redirect:/transactions";
	}
}
