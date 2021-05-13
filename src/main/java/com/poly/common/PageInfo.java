package com.poly.common;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;

public class PageInfo {
	
	public static Map<PageType, PageInfo> pageRoute = new HashedMap();
	
	
	static {
		
		pageRoute.put(PageType.USER_MANAGEMENT_PAGE,
				new PageInfo("User Management", "/admin/users/users.jsp", null));
												
		pageRoute.put(PageType.REPORT_MANAGEMENT_PAGE,
				new PageInfo("Reports", "/admin/reports/reports.jsp", null));
		pageRoute.put(PageType.VIDEO_MANAGEMENT_PAGE,
				new PageInfo("Video Management", "/admin/videos/videos.jsp", null));
		
		pageRoute.put(PageType.SITE_HOME_PAGE,
				new PageInfo("Home Page", "/site/home/home.jsp", null));
		pageRoute.put(PageType.SITE_LOGIN_PAGE,
				new PageInfo("Home Page", "/site/users/login.jsp", null));
		pageRoute.put(PageType.SITE_REGISTRATION_PAGE,
				new PageInfo("Registration", "/site/users/registration.jsp", null));
		pageRoute.put(PageType.SITE_CHANGE_PASSWORD_PAGE,
				new PageInfo("Change Password", "/site/users/change-password.jsp", null));
		pageRoute.put(PageType.SITE_EDIT_PROFILE_PAGE,
				new PageInfo("Edit Profile", "/site/users/edit-profile.jsp", null));
		pageRoute.put(PageType.SITE_FORGOT_PASSWORD_PAGE,
				new PageInfo("Forgot Password", "/site/users/forgot-password.jsp", null));
		
		pageRoute.put(PageType.SITE_FAVORITE_PAGE,
				new PageInfo("Favorite", "/site/videos/favorite.jsp", null));
		pageRoute.put(PageType.SITE_VIDEO_DETAIL_PAGE,
				new PageInfo("Detail", "/site/videos/detail.jsp", null));
		pageRoute.put(PageType.SITE_SHARE_PAGE,
				new PageInfo("Share", "/site/videos/share.jsp", null));
	}
	
	public static void prepareForward(HttpServletRequest request,HttpServletResponse response, PageType pageType)
	throws ServletException, IOException{
		PageInfo page = pageRoute.get(pageType);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/admin/layout.jsp").forward(request, response);
	}
	public static void prepareForwardSite(HttpServletRequest request,HttpServletResponse response, PageType pageType)
	throws ServletException, IOException{
		PageInfo page = pageRoute.get(pageType);
		
		request.setAttribute("page", page);
		
		request.getRequestDispatcher("/site/layout.jsp").forward(request, response);
	}
	

	 
	private String title;
	private String contenUrl;
	private String scriptUrl;

	public PageInfo(String title, String contenUrl, String scriptUrl) {
		
		this.title = title;
		this.contenUrl = contenUrl;
		this.scriptUrl = scriptUrl;
	}

	public static Map<PageType, PageInfo> getPageRoute() {
		return pageRoute;
	}

	public static void setPageRoute(Map<PageType, PageInfo> pageRoute) {
		PageInfo.pageRoute = pageRoute;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContenUrl() {
		return contenUrl;
	}

	public void setContenUrl(String contenUrl) {
		this.contenUrl = contenUrl;
	}

	public String getScriptUrl() {
		return scriptUrl;
	}

	public void setScriptUrl(String scriptUrl) {
		this.scriptUrl = scriptUrl;
	}

}
