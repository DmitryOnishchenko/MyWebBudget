package com.electdead.webbudget.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.electdead.webbudget.DAO.AccountDAO;
import com.electdead.webbudget.DAO.CategoryDAO;
import com.electdead.webbudget.DAO.TransactionDAO;
import com.electdead.webbudget.model.Account;
import com.electdead.webbudget.model.Category;
import com.electdead.webbudget.model.Transaction;
import com.electdead.webbudget.model.User;

@Controller
public class TransactionsController {
	
	@Autowired
	private AccountDAO accountDao;
	
	@Autowired
	private CategoryDAO categoryDao;
	
	@Autowired
	private TransactionDAO transactionDao;
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	
	@RequestMapping(value = "/transactions", method = RequestMethod.GET)
	public String getTransactions(HttpServletRequest request, Model model) {
		HttpSession session = SessionUtils.getSession(request);
		
		if (!SessionUtils.isAuthorized(session)) {
			model.addAttribute("user", new User());
			
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
			@RequestParam("date") Date date,
			@RequestParam("amount") String amount,
			@RequestParam("accountId") int accountId,
			@RequestParam("categoryId") int categoryId,
			@RequestParam("comment") String comment
			) {
		
		try {
			HttpSession session = SessionUtils.getSession(request);
			Account account = SessionUtils.findAccountInSession(session, accountId);
			Category category = SessionUtils.findCategoryInSession(session, categoryId);
			
			Transaction newTransaction = new Transaction();
			newTransaction.setDate(date);
			newTransaction.setAmount(Double.parseDouble(amount));
			newTransaction.setAccount(account);
			newTransaction.setCategory(category);
			newTransaction.setComment(comment);
			
			transactionDao.create(newTransaction);
			List<Transaction> transactions = SessionUtils.getTransactionsFromSession(session);
			transactions.add(newTransaction);
			
			double currentMoney = account.getMoney();
			account.setMoney(currentMoney + Double.parseDouble(amount));
			accountDao.save(account);
			
			double currentBalance = (Double) session.getAttribute("totalBalance");
			session.setAttribute("totalBalance", currentBalance + newTransaction.getAmount());
		} catch (Exception e) {
			
		}
		
		return "transactions";
	}
	
	@RequestMapping(value = "/transactions/delete", method = RequestMethod.GET)
	public String deleteTransaction(HttpServletRequest request, @RequestParam("id") int transactionId) {
		Transaction transaction = transactionDao.deleteById(transactionId);
		
		HttpSession session = SessionUtils.getSession(request);
		List<Transaction> transactions = SessionUtils.getTransactionsFromSession(session);
		transactions.remove(transaction);
		
		session.setAttribute("transactions", transactions);
		
		return "redirect:/transactions";
	}
	
//	@RequestMapping(value = "/transactions/delete", method = RequestMethod.POST,
//			produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public List<Transaction> deleteTransaction(HttpServletRequest request, @RequestParam("id") String transactionId) {
//		Integer id = Integer.parseInt(transactionId);
//		Transaction transaction = transactionDao.deleteById(id);
//		
//		HttpSession session = SessionUtils.getSession(request);
//		List<Transaction> transactions = SessionUtils.getTransactionsFromSession(session);
//		transactions.remove(transaction);
//		
//		session.setAttribute("transactions", transactions);
//		
//		return transactions;
//	}
}
