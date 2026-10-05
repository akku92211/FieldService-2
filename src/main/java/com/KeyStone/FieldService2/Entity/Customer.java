package com.KeyStone.FieldService2.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="customers")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})


public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;
	@Column(name="company_name", nullable=false)
	private String CompanyName;
	
	@Column(name="contact_person", nullable=false)
	private String ContactPerson;
	
	@Column(name = "email", nullable = false)
    private String email;
	
	@Column(name = "phone", nullable = false)
    private String phone;
	
	@Column(name = "address", nullable = false)
     private String address;
	
	@Column(name="activa")	
    private boolean activa;
	
	@Column(name="created_at")
	private LocalDateTime createdAt;

	@JsonIgnore
	@OneToMany(mappedBy="customer")
	private List<Site>sites=new ArrayList<>();
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getCompanyName() {
		return CompanyName;
	}
	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}
	public String getContactPerson() {
		return ContactPerson;
	}
	public void setContactPerson(String contactPerson) {
		ContactPerson = contactPerson;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isActiva() {
		return activa;
	}
	public void setActiva(boolean activa) {
		this.activa = activa;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}




}
