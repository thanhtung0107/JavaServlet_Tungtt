package com.poly.site.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.poly.common.PageInfo;
import com.poly.common.PageType;
import com.poly.common.SessionUtils;
import com.poly.dao.UserDao;
import com.poly.models.User;

@WebServlet("/EditProfile")
public class EditProfileServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//đọc thông tin username mà người dùng đã đăng nhập
		String username = SessionUtils.getLoginUserName(request);
		//nếu uersname ko tồn tại thì sẽ đưa người dùng đến trang login
		if (username == null) {
			request.getRequestDispatcher("/Login").forward(request, response);
			return;
		}
		// và khi user đăng nhập thành công sẽ tìm kiếm theo user và trả về tài khoản
		try {
			UserDao dao = new UserDao();
			User user = dao.findById(username);

			request.setAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		PageInfo.prepareForwardSite(request, response, PageType.SITE_EDIT_PROFILE_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			User user = new User();
			BeanUtils.populate(user, request.getParameterMap());

			
			String username = SessionUtils.getLoginUserName(request);
			UserDao dao = new UserDao();
			User oldUser = dao.findById(username);
			//dữ nguyên quyền ko cho edit thông tin quyền
			user.setAdmin(oldUser.getAdmin());
			dao.update(user);
			request.setAttribute("message", "Thông tin của bạn được cập nhật thành công !!");
			request.setAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		PageInfo.prepareForwardSite(request, response, PageType.SITE_EDIT_PROFILE_PAGE);
	}

}
