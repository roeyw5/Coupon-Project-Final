package com.coupons.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.coupons.model.user.Company;
import com.coupons.model.user.Customer;
import com.coupons.utility.enums.Category;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "coupons")
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private int id;

	//The company that is connected to the coupon("owns" it)
	@ManyToOne
	@JsonBackReference
	private Company company;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Category category;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	private Date startDate;
	@Column(nullable = false)
	private Date endDate;
	private int amount;
	private double price;
	@Column(nullable = false)
	private String image;

	public Coupon(Category category, String title, String description, Date startDate, Date endDate, int amount,
			double price, String image) {
		customers = new ArrayList<>();
		this.category = category;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}

	//List of customers that are connected to the coupon (purchased it)
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "customersVsCoupons", joinColumns = @JoinColumn(name = "couponId"), inverseJoinColumns = @JoinColumn(name = "customerId"))
	@JsonBackReference
	private List<Customer> customers;

	
	@Override
	public String toString() {
		return "Coupon(id=" + id + ", category=" + category + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", title=" + title + ", description=" + description + ", amount=" + amount + ", price=" + price
				+ ", image=" + image + ", companyId=" + company.getId() + ")";
	}

}
