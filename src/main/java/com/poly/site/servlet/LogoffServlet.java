package com.poly.site.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poly.common.CookiUtils;
import com.poly.common.SessionUtils;

@WebServlet("/Logoff")
public class LogoffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Sử dujgn service để nó gửi các dạng get và dạng post
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//xóa cookies username
		CookiUtils.add("username", null, 0, response);
		//xóa sessiong
		SessionUtils.invalidate(request);
		
		request.setAttribute("isLogin", false);
		//và hiển thị trang chủ
		request.getRequestDispatcher("/Homepage").forward(request, response);
	}

}
