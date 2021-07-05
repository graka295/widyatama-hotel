package com.widyatama.widytamahotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "category_room")
@Table(name = "category_room")
public class CategoryRoom extends AuditModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2259769729804319578L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Column(name = "name",nullable = false)
	private String name;
	@Column(name = "description",nullable = false)
	private String desc;
	@Column(name = "price",nullable = false)
	private double price;
}
