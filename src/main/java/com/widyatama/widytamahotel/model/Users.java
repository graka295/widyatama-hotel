package com.widyatama.widytamahotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "users")
@Table(name = "users")
public class Users extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8931669934853987715L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "email",nullable = false)
	private String email;
	@Column(name = "password",nullable = false)
	private String password;
	@Column(name = "first_name",nullable = false)
	private String firstName;
	@Column(name = "last_name",nullable = true)
	private String lastName;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
