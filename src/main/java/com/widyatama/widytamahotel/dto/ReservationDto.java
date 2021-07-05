package com.widyatama.widytamahotel.dto;

import java.util.Date;
import java.util.List;

public class ReservationDto {
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
	public static class ReqForm {
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Long getRoomId() {
			return roomId;
		}
		public void setRoomId(Long roomId) {
			this.roomId = roomId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getNoHp() {
			return noHp;
		}
		public void setNoHp(String noHp) {
			this.noHp = noHp;
		}
		public String getNik() {
			return nik;
		}
		public void setNik(String nik) {
			this.nik = nik;
		}
		public Date getBirthDate() {
			return birthDate;
		}
		public void setBirthDate(Date birthDate) {
			this.birthDate = birthDate;
		}
		public Integer getNumberOfPerson() {
			return numberOfPerson;
		}
		public void setNumberOfPerson(Integer numberOfPerson) {
			this.numberOfPerson = numberOfPerson;
		}
		public Integer getNumberOfChildren() {
			return numberOfChildren;
		}
		public void setNumberOfChildren(Integer numberOfChildren) {
			this.numberOfChildren = numberOfChildren;
		}
		public Date getFromDate() {
			return fromDate;
		}
		public void setFromDate(Date fromDate) {
			this.fromDate = fromDate;
		}
		public Date getToDate() {
			return toDate;
		}
		public void setToDate(Date toDate) {
			this.toDate = toDate;
		}
		private Long id;
		private Long roomId;
		private String name;
		private String noHp;
		private String nik;
		private Date birthDate;
		private Integer numberOfPerson;
		private Integer numberOfChildren;
		private Date fromDate;
		private Date toDate;
	}
}
