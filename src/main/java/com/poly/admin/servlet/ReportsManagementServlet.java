package com.poly.admin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poly.common.PageInfo;
import com.poly.common.PageType;
import com.poly.dao.FavoriteDao;
import com.poly.dao.VideoDao;
import com.poly.domain.FavoriteReport;
import com.poly.domain.FavoritesUserReport;
import com.poly.models.Video;

@WebServlet("/Reports")
public class ReportsManagementServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		reportFavoritesByVideos(request, response);
		reportFavoritesUsersByVideos(request, response);
		PageInfo.prepareForward(request, response, PageType.REPORT_MANAGEMENT_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void reportFavoritesByVideos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			FavoriteDao dao = new FavoriteDao();
			List<FavoriteReport> list = dao.reportFavoritesByVideos();
			
			request.setAttribute("favList", list);
		} catch (Exception e) {
			request.setAttribute("error","Error"+ e.getMessage());
		}
	}
	protected void reportFavoritesUsersByVideos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String videoUserId = request.getParameter("videoUserId");
			VideoDao vdao = new VideoDao();
			
			List<Video> vlist = vdao.findAll();
			if(videoUserId == null && vlist.size()>0) {
				videoUserId = vlist.get(0).getVideoId();
			}
			FavoriteDao dao = new FavoriteDao();
			List<FavoritesUserReport> list = dao.reportFavoritesUsersByVideos(videoUserId);
			
			
			request.setAttribute("videoUserId", videoUserId);
			request.setAttribute("vidList", vlist);
			request.setAttribute("favUsers", list);
		} catch (Exception e) {
			request.setAttribute("error","Error"+ e.getMessage());
		}
	}

}
