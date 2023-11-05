package com.lip6.daos;

import com.lip6.entities.Contact;
import com.lip6.entities.ContactGroup;

import java.util.List;
import java.util.Set;

public interface IDAOContactGroup {

    public ContactGroup addContactGroup(ContactGroup contactGroup);

    public List<ContactGroup> getAllContacts();

    public boolean deleteContactGroup(long id);

    public ContactGroup getContactGroup(long id);

    public boolean addContactInContactGroup(long idGroup, long idContact);

    public Set<Contact> getContactsInContactGroup(long idGroup);

    public boolean deleteContactFromContactGroup(long idGroup, long idContact);

}
