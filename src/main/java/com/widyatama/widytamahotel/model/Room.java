package com.widyatama.widytamahotel.model;

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

@Entity(name = "room")
@Table(name = "room")
public class Room extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2321065666772636858L;
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
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name",nullable = false)
	private String name;
	@Column(name = "description",nullable = false)
	private String desc;
	@ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CategoryRoom Category;
	public CategoryRoom getCategory() {
		return Category;
	}
	public void setCategory(CategoryRoom category) {
		Category = category;
	}
}
