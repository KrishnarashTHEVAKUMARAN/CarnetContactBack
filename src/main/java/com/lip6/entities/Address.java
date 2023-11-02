package com.lip6.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Address {
	
	private String street;
	private String city;
	private String zip;
	private String country;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idaddress;
	@OneToOne
	@JoinColumn(name="id_contact")
	@JsonBackReference
	Contact contact;
	
	public Address() {
		
	}
	
	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Address(String street, String city, String zip, String country) {
		this.street = street;
		this.city = city;
		this.zip = zip;
		this.country = country;
	}
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public long getIdaddress() {
		return idaddress;
	}

	public void setIdaddress(long idaddress) {
		this.idaddress = idaddress;
	}


}