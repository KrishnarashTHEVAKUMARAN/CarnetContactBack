package com.lip6.daos;

import com.lip6.entities.Address;
import com.lip6.entities.Contact;
import com.lip6.entities.PhoneNumber;
import com.lip6.util.JpaUtil;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DAOContact implements IDAOContact {
	@Override
	public Contact addContact(Contact contact){
		EntityManager em = JpaUtil.getEmf().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		if (contact.getPhones() != null) {
			for (PhoneNumber phone : contact.getPhones()) {
				phone.setContact(contact);
			}
		}
		if (contact.getAddress() != null) {
			contact.getAddress().setContact(contact);
		}
		em.persist(contact);
		tx.commit();

		em.close();
		return contact;
	}

	@Override
	// Avec NamedQuery
	public Contact updateContact(Contact updatedContact) {
		if (updatedContact == null || updatedContact.getIdContact() == 0) {
			throw new IllegalArgumentException("Le contact fourni est invalide.");
		}

		EntityManager em = JpaUtil.getEmf().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Contact existingContact = em.createNamedQuery("Contact.findById", Contact.class)
				.setParameter("id", updatedContact.getIdContact())
				.getSingleResult();

		if (existingContact != null) {
			// Mise à jour des données du contact
			existingContact.setFirstName(updatedContact.getFirstName());
			existingContact.setLastName(updatedContact.getLastName());
			existingContact.setEmail(updatedContact.getEmail());

			// Mise à jour de l'adresse
			if (updatedContact.getAddress() != null) {
				Address updatedAddress = updatedContact.getAddress();
				if (existingContact.getAddress() == null) {
					existingContact.setAddress(new Address());
					existingContact.getAddress().setContact(existingContact);
				}
				Address existingAddress = existingContact.getAddress();
				existingAddress.setStreet(updatedAddress.getStreet());
				existingAddress.setCity(updatedAddress.getCity());
				existingAddress.setZip(updatedAddress.getZip());
				existingAddress.setCountry(updatedAddress.getCountry());
			}

			// Mise à jour des numéros de téléphone
			Set<PhoneNumber> existingPhones = existingContact.getPhones();
			Set<PhoneNumber> updatedPhones = updatedContact.getPhones();

			// Si le contact existant n'a pas de numéros de téléphone, ajoutez simplement tous les numéros de téléphone fournis
			if (existingPhones == null || existingPhones.isEmpty()) {
				for (PhoneNumber updatedPhone : updatedPhones) {
					PhoneNumber newPhone = new PhoneNumber(updatedPhone.getPhoneKind(), updatedPhone.getPhoneNumber());
					newPhone.setContact(existingContact);
					existingPhones.add(newPhone);
				}
			} else {
				// Gérer la mise à jour ou l'ajout de numéros de téléphone
				for (PhoneNumber updatedPhone : updatedPhones) {
					boolean found = false;
					for (PhoneNumber existingPhone : existingPhones) {
						if (updatedPhone.getIdPhoneNumber() == existingPhone.getIdPhoneNumber()) {
							existingPhone.setPhoneKind(updatedPhone.getPhoneKind());
							existingPhone.setPhoneNumber(updatedPhone.getPhoneNumber());
							found = true;
							break;
						}
					}
					if (!found) {
						PhoneNumber newPhone = new PhoneNumber(updatedPhone.getPhoneKind(), updatedPhone.getPhoneNumber());
						newPhone.setContact(existingContact);
						existingPhones.add(newPhone);
					}
				}
				// Gérer la suppression de numéros de téléphone non présents dans les numéros mis à jour
				existingPhones.removeIf(existingPhone -> updatedPhones.stream().noneMatch(updatedPhone -> updatedPhone.getIdPhoneNumber() == existingPhone.getIdPhoneNumber()));
			}

			em.merge(existingContact);
			tx.commit();
		} else {
			throw new IllegalArgumentException("Aucun contact trouvé avec l'ID fourni.");
		}

		em.close();
		return existingContact;
	}

	@Override
	//Avec JPQL Parametre
	public boolean deleteContact(long id) {
	    boolean success = false;

	    try {
	        EntityManager em = JpaUtil.getEmf().createEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        tx.begin();

	        Contact contact = em.createQuery("SELECT c FROM Contact c WHERE c.idContact = :id", Contact.class)
	                .setParameter("id", id)
	                .getSingleResult();
	        
	        if (contact != null) {
	            em.remove(contact);
	            tx.commit();
	            success = true;
	        }else {
	            tx.rollback(); 
	        }
	        em.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return success;
	}

	
	@Override
	// Avec API Criteria
	public List<Contact> getAllContacts() {
	    List<Contact> contacts = new ArrayList<>();

	    try {
	        EntityManager em = JpaUtil.getEmf().createEntityManager();
	        CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<Contact> cq = cb.createQuery(Contact.class);
	        Root<Contact> contactRoot = cq.from(Contact.class);

	        cq.select(contactRoot);

	        TypedQuery<Contact> query = em.createQuery(cq);
	        contacts = query.getResultList();

	        em.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return contacts;
	}

	
	@Override
	// Avec API Criteria
	public Contact getContact(long id) {
	    Contact contact = null;

	    try {
	        EntityManager em = JpaUtil.getEmf().createEntityManager();
	        CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<Contact> cq = cb.createQuery(Contact.class);
	        Root<Contact> contactRoot = cq.from(Contact.class);

	        cq.select(contactRoot);
	        cq.where(cb.equal(contactRoot.get("idContact"), id));

	        TypedQuery<Contact> query = em.createQuery(cq);
	        contact = query.getSingleResult();

	        em.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return contact;
	}

	@Override
    public List<Contact> getContactByFirstName(String firstname) {
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        TypedQuery<Contact> query = em.createNamedQuery("Contact.findByFirstName", Contact.class);
        query.setParameter("firstName", firstname);
        List<Contact> contacts = query.getResultList();
        em.close();
        return contacts;
    }

    @Override
    public List<Contact> getContactByLastName(String lastname) {
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        TypedQuery<Contact> query = em.createNamedQuery("Contact.findByLastName", Contact.class);
        query.setParameter("lastName", lastname);
        List<Contact> contacts = query.getResultList();
        em.close();
        return contacts;
    }

    @Override
    public List<Contact> getContactByEmail(String email) {
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        TypedQuery<Contact> query = em.createNamedQuery("Contact.findByEmail", Contact.class);
        query.setParameter("email", email);
        List<Contact> contacts = query.getResultList();
        em.close();
        return contacts;
    }

}
