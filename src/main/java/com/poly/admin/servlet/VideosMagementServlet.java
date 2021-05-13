package com.poly.admin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.poly.common.PageInfo;
import com.poly.common.PageType;
import com.poly.common.UploadUtils;
import com.poly.dao.VideoDao;
import com.poly.models.Video;

@WebServlet({ "/Admin/VideosMagement", "/Admin/VideosMagement/create", "/Admin/VideosMagement/update",
		"/Admin/VideosMagement/delete", "/Admin/VideosMagement/reset", "/Admin/VideosMagement/edit" })

public class VideosMagementServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		if (url.contains("edit")) {
			edit(request, response);
			return;
		}
		if (url.contains("delete")) {
			delete(request, response);
			return;
		}
		if (url.contains("reset")) {
			reset(request, response);
			return;
		}

		Video video = new Video();
		video.setPoster("images/desktop.jpg");
		findAll(request, response);
		//thiết lập thuộc tính video sẽ được đọc trong view jsp
		request.setAttribute("video", video);
		PageInfo.prepareForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		if (url.contains("create")) {
			create(request, response);
			return;
		}
		if (url.contains("delete")) {
			delete(request, response);
			return;
		}
		if (url.contains("update")) {
			create(request, response);
			return;
		}
		if (url.contains("reset")) {
			reset(request, response);
			return;
		}
	}

	private void reset(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Video video = new Video();
		video.setPoster("images/desktop.jpg");
		request.setAttribute("video", video);
		findAll(request, response);
		PageInfo.prepareForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);

	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("videoID");

		if (id == null) {
			request.setAttribute("error", "video id is required!");
			PageInfo.prepareForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
			return;
		}
		try {
			VideoDao dao = new VideoDao();
			Video video = dao.findById(id);
			if (video == null) {
				request.setAttribute("error", "Không tìm thấy ID Video!");
				PageInfo.prepareForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
				return;
			}
			dao.delete(id);
			request.setAttribute("message", "Xóa thành công!!");
			request.setAttribute("video", new Video());

			findAll(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
		PageInfo.prepareForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Video video = new Video();
		try {
			BeanUtils.populate(video, request.getParameterMap());
			VideoDao dao = new VideoDao();
			Video oldVideo = dao.findById(video.getVideoId());

//			if (request.getPart("cover").getSize() == 0) {
//				video.setPoster(oldVideo.getPoster());
//			} else {
//				video.setPoster(
//						"uploads/" + UploadUtils.processUploadFiled("cover", request, "/uploads", video.getVideoID()));
//			}
			dao.update(video);
			request.setAttribute("video", video);
			request.setAttribute("message", "Cập nhật thành công!!");
			findAll(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
		PageInfo.prepareForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
	}
	//sau khi mình thực hiện xong 1 chức năng nào đó sẽ hiển thị danh sách video trong database 
	private void findAll(HttpServletRequest request, HttpServletResponse response) {

		try {

			VideoDao dao = new VideoDao();

			List<Video> list = dao.findAll();

			request.setAttribute("videos", list);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}

	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("videoID");

		if (id == null) {
			request.setAttribute("error", "Bắt buộc phải nhập video ID!");
			PageInfo.prepareForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
			return;
		}
		try {
			VideoDao dao = new VideoDao();
			Video video = dao.findById(id);

			request.setAttribute("video", video);
			findAll(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
		PageInfo.prepareForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
	}

	private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Video video = new Video();
		try {
			BeanUtils.populate(video, request.getParameterMap());
//			video.setPoster(
//					"upload/" + UploadUtils.processUploadFiled("cover", request, "/uploads", video.getVideoId()));

			VideoDao dao = new VideoDao();
			dao.insert(video);

			request.setAttribute("video", video);
			request.setAttribute("message", "Thêm thành công!!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());

		}
		findAll(request, response);
		PageInfo.prepareForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);

	}

}
