package com.widyatama.widytamahotel.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity(name = "reservation")
@Table(name = "reservation")
public class Reservation extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9057039887013836097L;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Room room;
	@Column(name = "name",nullable = false)
	private String name;
	@Column(name = "no_hp",nullable = false)
	private String noHp;
	@Column(name = "nik",nullable = false)
	private String nik;
	@Column(name = "birth_date",nullable = false)
	private Date birthDate;
	@Column(name = "number_of_person",nullable = true)
	private Integer numberOfPerson;
	@Column(name = "number_of_children",nullable = true)
	private Integer numberOfChildren;
	@Column(name = "from_data",nullable = false)
	private Date fromDate;
	@Column(name = "to_data",nullable = false)
	private Date toDate;
	@Column(name = "price",nullable = false)
	private double price;
}
