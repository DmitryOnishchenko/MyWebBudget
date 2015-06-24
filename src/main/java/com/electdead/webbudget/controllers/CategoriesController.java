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

import com.electdead.webbudget.DAO.CategoryDAO;
import com.electdead.webbudget.model.Category;
import com.electdead.webbudget.model.User;
import com.google.common.base.Joiner;

@Controller
public class CategoriesController {
	public static final Logger logger = LogManager.getLogger(CategoriesController.class.getName());
	
	@Autowired
	private CategoryDAO categoryDao;
	
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public String getCategories(HttpServletRequest request, Model model) {
		
		logger.debug("Request: categories.GET");
		
		HttpSession session = request.getSession();
		
		if (session == null) {
			logger.debug("User is not authorized. Redirect to login.jsp");
			model.addAttribute("user", new User());
			
			return "redirect:/login";
		}
		
		model.addAttribute("newCategory", new Category());
		
		logger.debug("Return categories.jsp");
		
		return "categories";
	}
	
	@RequestMapping(value = "/categories", method = RequestMethod.POST)
	public String addCategory(HttpServletRequest request, 
			@ModelAttribute("newCategory") Category category, Model model) {
		
		logger.debug("Request: categories.POST");
		
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("user");
		
		Category existingCategory = (Category) categoryDao.getByName(category.getName(), user);
		
		if (existingCategory != null) {
			logger.debug("Category is already exists. Return categories.jsp");
			model.addAttribute("categoryNameError", true);
			
			return "categories";
		}
		
		logger.debug("Try to save the category in DB");
		
		category.setUser(user);
		categoryDao.save(category);
		
		List<Category> categories = SessionUtils.getCategoriesFromSession(session);
		categories.add(category);
		session.setAttribute("categories", categories);
		
		model.addAttribute("newCategory", new Category());
		
		if (logger.isDebugEnabled()) {
			logger.debug(Joiner.on(' ').join(
					"All is fine. Category:", category, " created. Return categories.jsp"));			
		}
		
		return "categories";
	}
}
