package com.poly.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * The persistent class for the Favorites database table.
 * 
 */
@Entity
@Table(name = "Favorites")
@NamedQuery(name = "Favorite.findAll", query = "SELECT f FROM Favorite f")
public class Favorite implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FavoriteId")
	private int favoriteId;

	@Column(name = "LikedDate")
	@Temporal(TemporalType.DATE)
	Date likedDate;

	@ManyToOne
	@JoinColumn(name = "Username")
	private User user;

	@ManyToOne
	@JoinColumn(name = "VideoId")
	private Video video;

	public Favorite() {

	}

	public Favorite(int favoriteId, Date likedDate, User user, Video video) {

		this.favoriteId = favoriteId;
		this.likedDate = likedDate;
		this.user = user;
		this.video = video;
	}

	public int getFavoriteId() {
		return favoriteId;
	}

	public void setFavoriteId(int favoriteId) {
		this.favoriteId = favoriteId;
	}

	public Date getLikedDate() {
		return likedDate;
	}

	public void setLikedDate(Date likedDate) {
		this.likedDate = likedDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}