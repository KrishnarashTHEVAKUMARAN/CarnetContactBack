package com.lip6.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class PhoneNumber {
	private String phoneKind;
	private String phoneNumber;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPhoneNumber;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contact_id")
	@JsonBackReference
	Contact contact;
	
	
	public PhoneNumber(){
	}
	

	public PhoneNumber(String phoneKind, String phoneNumber) {
		this.phoneKind = phoneKind;
		this.phoneNumber = phoneNumber;
	}
	
	
	public Contact getContact() {
		return contact;
	}


	public void setContact(Contact contact) {
		this.contact = contact;
	}


	public String getPhoneKind() {
		return phoneKind;
	}


	public void setPhoneKind(String phoneKind) {
		this.phoneKind = phoneKind;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public long getIdPhoneNumber() {
		return idPhoneNumber;
	}


	public void setIdPhoneNumber(long idPhoneNumber) {
		this.idPhoneNumber = idPhoneNumber;
	}
	
}
