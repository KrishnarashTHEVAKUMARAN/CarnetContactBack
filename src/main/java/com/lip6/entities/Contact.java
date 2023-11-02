package com.lip6.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

;

@Entity
@NamedQueries({
    @NamedQuery(
        name = "Contact.findByFirstName",
        query = "SELECT c FROM Contact c WHERE c.firstName = :firstName"
    ),
    @NamedQuery(
        name = "Contact.findByLastName",
        query = "SELECT c FROM Contact c WHERE c.lastName = :lastName"
    ),
    @NamedQuery(
        name = "Contact.findByEmail",
        query = "SELECT c FROM Contact c WHERE c.email = :email"
    ),
    @NamedQuery(
    	    name = "Contact.findById",
    	    query = "SELECT c FROM Contact c WHERE c.idContact = :id"
    ),
    @NamedQuery(
            name = "Contact.deleteContact",
            query = "DELETE FROM Contact c WHERE c.idContact = :id"
    ),
})

public class Contact {

	private String firstName;
	private String lastName;
	private String email;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idContact;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, mappedBy = "contact", orphanRemoval = true)
	@JoinColumn(name="id_address")
	@JsonManagedReference
	Address address;
	@OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL,mappedBy = "contact", orphanRemoval = true)
	@JsonManagedReference
	Set<PhoneNumber> phones = new HashSet<PhoneNumber>();
	@ManyToMany(fetch = FetchType.EAGER,cascade=CascadeType.PERSIST)
	@JoinTable(name="CTC_GRP", joinColumns=@JoinColumn(name="CTC_ID"),inverseJoinColumns=@JoinColumn(name="GRP_ID"))
	private Set<ContactGroup> contactGroups=new HashSet<>();
	
	public Contact(){
	}
	
	public Set<PhoneNumber> getPhones() {
		return phones;
	}

	public void setPhones(Set<PhoneNumber> phones) {
		this.phones = phones;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		if (address == null) {
		  if (this.address != null) {
		     this.address.setContact(null);
		  }
		} else {
			address.setContact(this);
		}
		this.address = address;
	}

	public void addaddress(Address address) {
	    this.address = address;
	}

	public Contact(String firstName, String lastName, String email, long idContact) {
		this(firstName, lastName, email);
		this.idContact = idContact;
	}

	public Contact(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public void setFirstName(String firstname){
		this.firstName = firstname;
	}
	
	
	public String getLastName(){
		return lastName;
	}
	
	public void setLastName(String lastname){
		this.lastName = lastname;
	}

	public long getIdContact() {
		return idContact;
	}

	public void setIdContact(long idContact) {
		this.idContact = idContact;
	}

	public Set<ContactGroup> getContactGroups() {
		return contactGroups;
	}

	public void setContactGroups(Set<ContactGroup> contactGroups) {
		this.contactGroups = contactGroups;
	}	
	
}
