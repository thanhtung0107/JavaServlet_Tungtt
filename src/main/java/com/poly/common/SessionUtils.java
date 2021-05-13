package com.poly.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {
	//cho phép lưu thuộc tính trong session
	public static void add(HttpServletRequest request, String name, Object value) {
		HttpSession session = request.getSession();
		session.setAttribute(name, value);
	}
	//lưu thuộc tính được truyền vào trong session
	public static Object get(HttpServletRequest request, String name) {
		HttpSession session = request.getSession();
		return session.getAttribute(name);
	}
	//hủy bỏ session =>> invalidate
	public static void invalidate(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("username");
		session.invalidate();
	}
	
	public static boolean isLogin(HttpServletRequest request) {
		//người dùng đã đăng nhập
		return get(request, "username") != null;
	}

	public static String getLoginUserName(HttpServletRequest request) {
		//trả về username đã đăng nhập vào hệ thống
		Object username = get(request, "username");
		return username == null? null: username.toString();
	}
}
