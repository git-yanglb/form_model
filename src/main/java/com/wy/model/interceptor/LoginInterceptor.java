package com.wy.model.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wy.model.entity.User;
import com.wy.model.util.SysConstants;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestURI = request.getRequestURI();
		if("/login".equals(requestURI)){
			response.sendRedirect("/");
		}
		User user = (User) request.getSession().getAttribute(SysConstants.USER_SESSION_KEY);
		if (user == null) {
			response.sendRedirect("/");
			return false;
		}
		return true;
	}

}
