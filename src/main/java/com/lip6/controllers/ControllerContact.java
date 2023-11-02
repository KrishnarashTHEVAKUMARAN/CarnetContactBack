package com.lip6.controllers;

import com.lip6.dtos.DTOAddress;
import com.lip6.dtos.DTOContact;
import com.lip6.dtos.DTOPhoneNumber;
import com.lip6.entities.Contact;
import com.lip6.services.ServiceContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ControllerContact {
    @Autowired
    private ServiceContact serviceContact;

    @GetMapping("/contacts")
    public List<DTOContact> getAllContacts() {
        List<Contact> contacts = serviceContact.getAllContacts();

        List<DTOContact> dtoContacts = contacts.stream()
                .map(contact -> new DTOContact(
                        contact.getIdContact(),
                        contact.getEmail(),
                        contact.getFirstName(),
                        contact.getLastName()
                ))
                .collect(Collectors.toList());

        return dtoContacts;
    }

    @PostMapping("/addContact")
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact){
        Contact addContact = serviceContact.createContact(contact);
        return new ResponseEntity<>(addContact, HttpStatus.CREATED);
    }

    @GetMapping("/contactById/{id}")
    public DTOContact getContact(@PathVariable Long id) {
        Contact contact = serviceContact.getContact(id);

        DTOAddress dtoAddress = new DTOAddress(
                contact.getAddress().getStreet(),
                contact.getAddress().getCity(),
                contact.getAddress().getZip(),
                contact.getAddress().getCountry()
        );

        List<DTOPhoneNumber> dtoPhones = contact.getPhones().stream()
                .map(phone -> new DTOPhoneNumber(phone.getIdPhoneNumber(), phone.getPhoneKind(), phone.getPhoneNumber()))
                .collect(Collectors.toList());

        DTOContact dtoContact = new DTOContact(
                contact.getIdContact(),
                contact.getEmail(),
                contact.getFirstName(),
                contact.getLastName(),
                dtoAddress,
                dtoPhones
        );

        return dtoContact;
    }

    @PutMapping("/updateContact/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id, @RequestBody Contact contact){
        contact.setIdContact(id);
        Contact updatedContact = serviceContact.updateContact(contact);
        if (updatedContact == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedContact, HttpStatus.OK);
    }

    @DeleteMapping("/deleteContact/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        try {
            serviceContact.deleteContact(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Retournez le code 204 No Content lors de la suppression réussie.
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Retournez le code 500 si une erreur survient.
        }
    }

}