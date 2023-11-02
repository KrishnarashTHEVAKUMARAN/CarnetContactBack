package com.lip6.services;

import com.lip6.daos.DAOContact;
import com.lip6.entities.Contact;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceContact {

	public Contact createContact(Contact contact) {
		DAOContact daoc=new DAOContact();
		return daoc.addContact(contact);
	}

	public Contact updateContact(Contact contact) {
		DAOContact daoc=new DAOContact();
		return daoc.updateContact(contact);
	}
	
	public boolean deleteContact(long idContact) {
		DAOContact daoc=new DAOContact();
        return daoc.deleteContact(idContact);
    }
	
	public List<Contact> getAllContacts() {
		DAOContact daoc=new DAOContact();
		return daoc.getAllContacts();
	}

	public Contact getContact(long idContact) {
		DAOContact daoc=new DAOContact();
		return daoc.getContact(idContact);
	}

}
