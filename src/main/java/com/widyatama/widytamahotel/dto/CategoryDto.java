package com.widyatama.widytamahotel.dto;

import java.util.List;

public class CategoryDto {
	public static class ReqForm {
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public Long getPrice() {
			return price;
		}
		public void setPrice(Long price) {
			this.price = price;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		private String name;
		private String desc;
		private Long price;
		private Long id;
	}
	
	public static class ResForm {
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
