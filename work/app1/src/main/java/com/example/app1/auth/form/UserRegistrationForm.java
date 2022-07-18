package com.example.app1.auth.form;

import java.io.Serializable;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;

public class UserRegistrationForm implements Serializable {

	private static final long serialVersionUID = 6953557398220635269L;

	@Size(max = 128)
	private String email;
	
	@Size(max = 128)
	private String emailConfirm;
	
	@Size(max = 64)
	private String username;
	
	@Size(min = 8, max = 20)
	private String password;
	
	@Size(min = 8, max = 20)
	private String passwordConfirm;
	
	@AssertTrue
	public boolean isEmailConfirmed() {
		if(email == null && emailConfirm == null) {
			return true;
		}
		return email.equals(emailConfirm);
	}
	
	@AssertTrue
	public boolean isPasswordConfirmed() {
		if(password == null && passwordConfirm == null) {
			return true;
		}
		return password.equals(passwordConfirm);
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailConfirm() {
		return emailConfirm;
	}
	public void setEmailConfirm(String emailConfirm) {
		this.emailConfirm = emailConfirm;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
}
