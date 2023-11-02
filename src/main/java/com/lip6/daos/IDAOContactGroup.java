package com.lip6.daos;

import com.lip6.entities.ContactGroup;

import java.util.List;

public interface IDAOContactGroup {

    public ContactGroup addContactGroup(ContactGroup contactGroup);

    public List<ContactGroup> getAllContacts();

    public boolean deleteContactGroup(long id);
}
