package com.lip6.daos;

import com.lip6.entities.Contact;

import java.util.List;

public interface IDAOContact {

	public Contact addContact(Contact contact);

	public Contact updateContact(Contact contact);

	public boolean deleteContact(long id);

	public List<Contact> getAllContacts();
	
	public Contact getContact(long id);
	
	public List<Contact> getContactByFirstName(String firstname);
	
	public List<Contact> getContactByLastName(String lastname);
	
	public List<Contact> getContactByEmail(String email);
	
}
