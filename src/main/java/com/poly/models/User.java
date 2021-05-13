package com.poly.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the Users database table.
 * 
 */
@Entity
@Table(name = "Users")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "Username")
	private String username;

	@Column(name = "Admin")
	private boolean admin;

	@Column(name = "Email")
	private String email;

	@Column(name = "Fullname")
	private String fullname;

	@Column(name = "Passwords")
	private String passwords;

	@OneToMany(mappedBy = "user")
	private List<Favorite> favorites;

	@OneToMany(mappedBy = "user")
	private List<Share> shares;

	public User() {

	}

	public User(String username, boolean admin, String email, String fullname, String passwords,
			List<Favorite> favorites, List<Share> shares) {

		this.username = username;
		this.admin = admin;
		this.email = email;
		this.fullname = fullname;
		this.passwords = passwords;
		this.favorites = favorites;
		this.shares = shares;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean getAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPasswords() {
		return passwords;
	}

	public void setPasswords(String passwords) {
		this.passwords = passwords;
	}

	public List<Favorite> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}

	public List<Share> getShares() {
		return shares;
	}

	public void setShares(List<Share> shares) {
		this.shares = shares;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}