package com.poly.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * The persistent class for the Shares database table.
 * 
 */
@Entity
@Table(name = "Shares")
@NamedQuery(name = "Share.findAll", query = "SELECT s FROM Share s")
public class Share implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ShareId")
	private int shareId;

	@Column(name = "Emails")
	private String emails;

	@Column(name = "SharedDate")
	@Temporal(TemporalType.DATE)
	Date sharedDate;

	@ManyToOne
	@JoinColumn(name = "Username")
	private User user;

	@ManyToOne
	@JoinColumn(name = "VideoId")
	private Video video;

	public Share() {

	}

	public Share(int shareId, String emails, Date sharedDate, User user, Video video) {

		this.shareId = shareId;
		this.emails = emails;
		this.sharedDate = sharedDate;
		this.user = user;
		this.video = video;
	}

	public int getShareId() {
		return shareId;
	}

	public void setShareId(int shareId) {
		this.shareId = shareId;
	}

	public String getEmails() {
		return emails;
	}

	public void setEmails(String emails) {
		this.emails = emails;
	}

	public Date getSharedDate() {
		return sharedDate;
	}

	public void setSharedDate(Date sharedDate) {
		this.sharedDate = sharedDate;
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