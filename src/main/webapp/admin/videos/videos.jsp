<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <div class="col mt-4">
 <jsp:include page="/common/inform.jsp"></jsp:include>
            <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item" role="presentation">
                  <a class="nav-link active" id="videoEditing-tab" data-toggle="tab" href="#videoEditing" role="tab" aria-controls="videoEditing" aria-selected="true">Video Editing</a>
                </li>
                <li class="nav-item" role="presentation">
                  <a class="nav-link" id="videoList-tab" data-toggle="tab" href="#videoList" role="tab" aria-controls="videoList" aria-selected="false">Video List</a>
                </li>
                
              </ul>
              <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="videoEditing" role="tabpanel" aria-labelledby="videoEditing-tab">
                   
                    <form action="" method="post" >
                        <div class="card">
                    
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-3">
                                        <div class="boder p-3">
                                            <img src="${video.poster != null?video.poster:'/images/desktop.jpg' }" alt="" class="img-fluid">
                                            <div class="input-gruup mb-3 mt-3">
                                            	<div class="input-group-prepend">
                                            		<div class="input-group-text">Upload</div>
                                            	</div>
                                            	<div class="custom-file">
                                            		<input type="file" class="custom-file-input" id="cover" name="cover"/>
                                            		<label for="cover">Choose File</label>
                                            	</div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-9">
                                        <div class="form-group">
                                          <label for="videoId">Youtube ID</label>
                                          <input type="text" class="form-control" name="videoId" id="youtubeID" value="${video.videoId }" aria-describedby="youbtubeIDHid" placeholder="Youtube ID">
                                          <small id="youbtubeIDHid" class="form-text text-muted">Youtube id is required</small>
                                        </div>
                                        <div class="form-group">
                                          <label for="videoTitle">Video Title</label>
                                          <input type="text" class="form-control" name="title" id="videoTitle" value="${video.title }" aria-describedby="videoTitleHid" placeholder="Video Title">
                                          <small id="videoTitleHid" class="form-text text-muted">Video title is required</small>
                                        </div>
                                        <div class="form-group">
                                          <label for="viewcount">View Count</label>
                                          <input type="text" class="form-control" name="views" id="viewcount" value="${video.views }"  aria-describedby="viewcountHid" placeholder="ViewCount">
                                          <small id="viewcountHid" class="form-text text-muted">View is requered</small>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <label><input type="radio" class="form-check-input"
                                                value="true" name="active" id="active" ${video.active? 'checked':'' } >Active</label>
                                                <label><input type="radio" class="form-check-input"
                                                    value="false" ${! video.active? 'checked':'' } name="active" id="status">InActive</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <label for="description">Description</label>
                                    <div class="col"><textarea name="description" id="description" cols="30" rows="4" class="form-control">${video.description }</textarea></div>
                                </div>
                            </div>
                            <div class="card-footer text-muted">
                                <button class="btn btn-primary" formaction="Admin/VideosMagement/create">Create</button>
                                <button class="btn btn-warning" formaction="Admin/VideosMagement/update">Update</button>
                                <button class="btn btn-danger" formaction="Admin/VideosMagement/delete">Delete</button>
                                <button class="btn btn-info" formaction="Admin/VideosMagement/reset">Reset</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="tab-pane fade" id="videoList" role="tabpanel" aria-labelledby="videoList-tab">
                    <table class="table table-stripe">
                        <tr>
                            <td>Youtube ID</td>
                            <td>Video Title</td>
                            <td>Video Count</td>
                            <td>Status</td>
                            <td>&nbsp;</td>
                        </tr>
                        <c:forEach var="item" items="${videos }">
                        <tr>
                            <td>${item.videoId }</td>
                            <td>${item.title }</td>
                            <td>${item.views }</td>
                            <td>${item.active? 'Active': 'Deactive' }</td>
                            <td>
                                <a href="Admin/VideosMagement/edit?videoID=${item.videoId }"><i class="fa fa-edit" aria-hidden="true"></i>Edit</a>
                                <a href="Admin/VideosMagement/delete?videoID=${item.videoId }"><i class="fa fa-trash" aria-hidden="true"></i>Delete</a>
                            </td>
                        </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
              </div>