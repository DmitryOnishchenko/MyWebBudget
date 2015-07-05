package com.electdead.webbudget.controllers;

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

import com.electdead.webbudget.DAO.UserDAO;
import com.electdead.webbudget.model.User;
import com.google.common.base.Joiner;

@Controller
public class LoginController {
	private static final Logger logger = LogManager.getLogger(LoginController.class.getName());
	
	@Autowired
	private UserDAO userDao;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLogin(HttpServletRequest request, Model model) {
		
		logger.debug("Request: login.GET");
		
		HttpSession session = request.getSession();
		if (!SessionUtils.isAuthorized(session)) {	
			model.addAttribute("user", new User());
			
			return "login";
		}
		
		logger.debug("User is authorized (prevent access to login page for authorized users). Redirect to home.jsp");
		return "redirect:/";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, @ModelAttribute("user") User user, Model model) {
		
		logger.debug("Request: login.POST");
		
		logger.debug("Try login...");
		if (!tryLogin(user, model)) {
			logger.debug("Error. Return login.jsp");
			model.addAttribute("user", new User());
			
			return "login";
		}
		
		logger.debug("Success!");
		HttpSession session = SessionUtils.getNewSession(request);
		session.setAttribute("user", user);
		
		if (logger.isDebugEnabled()) {
			logger.debug(Joiner.on("").join(
					"All is fine for User[id: ", user.getUserId(),
					", login: ", user.getLogin(),
					"] . Redirect to home.jsp"));
		}
		
		return "redirect:/";
	}
	
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET) 
	public String getRegistration(HttpServletRequest request, Model model) {
		
		logger.debug("Request: registration.GET");
				
		HttpSession session = request.getSession();
		if (!SessionUtils.isAuthorized(session)) {
			model.addAttribute("user", new User());
			
			return "registration";
		}
		
		logger.debug("User is authorized (prevent access to registration page for authorized users). Redirect to home.jsp");
		return "redirect:/";
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String register(@ModelAttribute("user") User user, HttpServletRequest request, Model model) {
		
		logger.debug("Request: registration.POST");
		if (logger.isDebugEnabled()) {
			logger.debug(Joiner.on("").join("Try to find user: [", user.getLogin(), "] in DB"));			
		}
		
		User existingUser = userDao.getByLogin(user.getLogin());
		
		if (existingUser != null) {
			logger.debug("User is already exists. Return registration.jsp");
			model.addAttribute("user", new User());
			model.addAttribute("usernameError", true);
			
			return "registration";
		}
		
		int userId = userDao.create(user);
		user.setUserId(userId);
		
		HttpSession session = SessionUtils.getNewSession(request);
		session.setAttribute("user", user);
		
		if (logger.isDebugEnabled()) {
			logger.debug(Joiner.on("").join(
					"Created new User:[id: ", user.getUserId(),
					", login: ", user.getLogin(), "]. Redirect to home.jsp"));
		}
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		
		logger.debug("Request: logout.GET");
		logger.debug("Invalidate session. Redirect to login.jsp");
		
		HttpSession session = request.getSession();
		if (session != null) {
			session.invalidate();
		}
		
		return "redirect:/login";
	}
	
	private boolean tryLogin(User user, Model model) {
		logger.entry(user);
		
		User existingUser = userDao.getByLogin(user.getLogin());
		
		if (existingUser == null) {
			logger.trace("Username error");
			model.addAttribute("usernameError", true);
			
			return false;
		} else if (!user.getPassword().equals(existingUser.getPassword())) {
			logger.trace("Password error");
			model.addAttribute("passwordError", true);
			
			return false;
		}
		
		user.setUserId(existingUser.getUserId());
		user.setEmail(existingUser.getEmail());
		
		logger.exit(true);
		return true;
	}
}
