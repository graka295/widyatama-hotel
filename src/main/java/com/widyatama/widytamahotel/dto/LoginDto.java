package com.widyatama.widytamahotel.dto;

import java.util.List;

public class LoginDto {
	public static class ReqForm {
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
		private String email;
		private String password;	
	}
	
	public static class ResSuccessDto {
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
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return LastName;
		}
		public void setLastName(String lastName) {
			LastName = lastName;
		}
		private Long id;
		private String email;
		private String firstName;
		private String LastName;
	}

	
	public static class ResBadRequest {
		private String code;
		private List<String> message;
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public List<String> getMessage() {
			return message;
		}
		public void setMessage(List<String> message) {
			this.message = message;
		}
	}

}
