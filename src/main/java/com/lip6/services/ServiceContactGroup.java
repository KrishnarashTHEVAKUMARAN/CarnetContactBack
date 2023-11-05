package com.lip6.services;

import com.lip6.daos.DAOContactGroup;
import com.lip6.entities.Contact;
import com.lip6.entities.ContactGroup;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class ServiceContactGroup {

    public ContactGroup createContactGroup(ContactGroup contactGroup) {
        DAOContactGroup daocg=new DAOContactGroup();
        return daocg.addContactGroup(contactGroup);
    }

    public List<ContactGroup> getAllContactsGroups() {
        DAOContactGroup daocg=new DAOContactGroup();
        return daocg.getAllContacts();
    }

    public boolean deleteContactGroup(long idContactGroup) {
        DAOContactGroup daocg=new DAOContactGroup();
        return daocg.deleteContactGroup(idContactGroup);
    }

    public ContactGroup getContactGroup(long idContact) {
        DAOContactGroup daocg=new DAOContactGroup();
        return daocg.getContactGroup(idContact);
    }

    public boolean addContactInContactGroup(long idGroup, long idContact) {
        DAOContactGroup daocg=new DAOContactGroup();
        return daocg.addContactInContactGroup(idGroup, idContact);
    }

    public Set<Contact> getContactInContactGroup(long idGroup) {
        DAOContactGroup daocg=new DAOContactGroup();
        return daocg.getContactsInContactGroup(idGroup);
    }

    public boolean deleteContactFromContactGroup(long idGroup, long idContact) {
        DAOContactGroup daocg=new DAOContactGroup();
        return daocg.deleteContactFromContactGroup(idGroup, idContact);
    }

}
