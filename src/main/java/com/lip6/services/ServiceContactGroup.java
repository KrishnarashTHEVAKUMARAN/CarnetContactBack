package com.lip6.services;

import com.lip6.daos.DAOContactGroup;
import com.lip6.entities.ContactGroup;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
