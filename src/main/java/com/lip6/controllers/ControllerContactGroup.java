package com.lip6.controllers;

import com.lip6.dtos.DTOContact;
import com.lip6.dtos.DTOContactGroup;
import com.lip6.entities.Contact;
import com.lip6.entities.ContactGroup;
import com.lip6.services.ServiceContactGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ControllerContactGroup {
    @Autowired
    private ServiceContactGroup serviceContactGroupe;

    @PostMapping("/addContactGroup")
    public ResponseEntity<ContactGroup> addContact(@RequestBody ContactGroup contactGroup){
        ContactGroup addContactGroup = serviceContactGroupe.createContactGroup(contactGroup);
        return new ResponseEntity<>(addContactGroup, HttpStatus.CREATED);
    }

    @GetMapping("/contactsGroups")
    public List<DTOContactGroup> getAllContacts() {
        List<ContactGroup> contactsGroups = serviceContactGroupe.getAllContactsGroups();

        List<DTOContactGroup> dtoContacts = contactsGroups.stream()
                .map(contactGroup -> new DTOContactGroup(
                        contactGroup.getGroupId(),
                        contactGroup.getGroupName()
                ))
                .collect(Collectors.toList());

        return dtoContacts;
    }

    @DeleteMapping("/deleteContactGroup/{id}")
    public ResponseEntity<Void> deleteContactGroup(@PathVariable Long id) {
        try {
            serviceContactGroupe.deleteContactGroup(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/contactGroupById/{id}")
    public DTOContactGroup getContactGroup(@PathVariable Long id) {
        ContactGroup contactGroup = serviceContactGroupe.getContactGroup(id);

        DTOContactGroup dtoContactGroup = new DTOContactGroup(
                contactGroup.getGroupId(),
                contactGroup.getGroupName()
        );

        return dtoContactGroup;
    }

    @PostMapping("/addContactGroups/{idGroup}/{idContact}")
    public ResponseEntity<ContactGroup> addContactToGroup(@PathVariable("idGroup") Long idGroup, @PathVariable("idContact") Long idContact) {
        try {
            serviceContactGroupe.addContactInContactGroup(idGroup, idContact);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/contactGroup/{idGroup}/contacts")
    public ResponseEntity<Set<DTOContact>> getContactsInContactGroup(@PathVariable Long idGroup) {
        try {
            Set<Contact> contacts = serviceContactGroupe.getContactInContactGroup(idGroup);
            if (contacts == null || contacts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                Set<DTOContact> dtoContacts = contacts.stream()
                        .map(contact -> new DTOContact(
                                contact.getIdContact(),
                                contact.getEmail(),
                                contact.getFirstName(),
                                contact.getLastName()
                        ))
                        .collect(Collectors.toSet());
                return new ResponseEntity<>(dtoContacts, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/deleteContactGroups/{idGroup}/{idContact}")
    public ResponseEntity<Void> deleteContactToGroup(@PathVariable("idGroup") Long idGroup, @PathVariable("idContact") Long idContact) {
        try {
            serviceContactGroupe.deleteContactFromContactGroup(idGroup, idContact);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
