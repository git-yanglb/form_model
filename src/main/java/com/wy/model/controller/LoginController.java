package com.wy.model.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wy.model.entity.User;
import com.wy.model.service.UserService;
import com.wy.model.util.SysConstants;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public String login(@RequestParam String userName, @RequestParam String password, HttpServletRequest request) {
		if (userName == null || password == null || userName.isEmpty() || password.isEmpty()) {
			return "redirect:/";
		}
		User user = userService.selectByName(userName);
		if (user == null) {
			return "redirect:/";
		}
		String originPassword = user.getPassword();
		if (!password.equals(originPassword)) {
			return "redirect:/";
		}
		request.getSession().setAttribute(SysConstants.USER_SESSION_KEY, user);
		return "redirect:/index";
	}

}
