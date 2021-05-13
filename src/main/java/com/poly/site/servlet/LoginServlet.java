package com.poly.site.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.poly.common.CookiUtils;
import com.poly.common.PageInfo;
import com.poly.common.PageType;
import com.poly.common.SessionUtils;
import com.poly.dao.UserDao;
import com.poly.domain.LoginForm;
import com.poly.models.User;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = CookiUtils.get("username", request);	
		if(username==null) {
			PageInfo.prepareForwardSite(request, response, PageType.SITE_LOGIN_PAGE);
			return;
		}
		SessionUtils.add(request, "username", username);
		request.getRequestDispatcher("/Homepage").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			LoginForm form = new LoginForm();
			BeanUtils.populate(form, request.getParameterMap());
			
			UserDao dao = new UserDao();
			User user = dao.findById(form.getUsername());
			
			if(user!=null && user.getPasswords().equals(form.getPassword())){
				SessionUtils.add(request, "username", user.getUsername());
				
				if(form.isRemember()) {
					CookiUtils.add("username", form.getUsername(), 24, response);
					
				}else {
					CookiUtils.add("username", form.getUsername(), 0, response);
				}
				//thể hiện đăng nhập thành công
				request.setAttribute("isLogin", true);
				request.setAttribute("name", user.getFullname());
				request.getRequestDispatcher("/Homepage").forward(request, response);
				return;
			}
			request.setAttribute("error", "Tài khoản hoặc mật khẩu không chính xác !!");
			
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
		}
		PageInfo.prepareForwardSite(request, response, PageType.SITE_LOGIN_PAGE);
	}

}
