package com.poly.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.poly.models.Video;

public class VideoDao extends AbstractEntityDao<Video> {

	public VideoDao() {
		super(Video.class);
		
	}
	
}
