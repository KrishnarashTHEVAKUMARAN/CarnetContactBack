package com.lip6.controllers;

import com.lip6.dtos.DTOContactGroup;
import com.lip6.entities.ContactGroup;
import com.lip6.services.ServiceContactGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Retournez le code 204 No Content lors de la suppression réussie.
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Retournez le code 500 si une erreur survient.
        }
    }

}
