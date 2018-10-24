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
			response.setContentType("text/html");
			
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print("<link rel='stylesheet' href='/assets/layer/theme/default/layer.css'>"
					+ "<script src='/assets/js/jquery-2.0.3.min.js'></script>"
					+ "<script src='/assets/layer/layer.js'></script>"
					+ "<script src='/plugin/common.js'></script>"
					+ "<script>$(function(){alert2('当前会话失效，请重新登录！',function(){top.location.href='/';});});</script>");;
			return false;
		}
		return true;
	}

}
