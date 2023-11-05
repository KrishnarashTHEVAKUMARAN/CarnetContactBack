package com.lip6.daos;

import com.lip6.entities.Contact;
import com.lip6.entities.ContactGroup;
import com.lip6.util.JpaUtil;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DAOContactGroup implements IDAOContactGroup{
    @Override
    public ContactGroup addContactGroup(ContactGroup contactGroup) {
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(contactGroup);
        tx.commit();
        em.close();
        return contactGroup;
    }

    @Override
    public List<ContactGroup> getAllContacts() {
        List<ContactGroup> contactsGroups = new ArrayList<>();

        try {
            EntityManager em = JpaUtil.getEmf().createEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<ContactGroup> cq = cb.createQuery(ContactGroup.class);
            Root<ContactGroup> contactRoot = cq.from(ContactGroup.class);

            cq.select(contactRoot);

            TypedQuery<ContactGroup> query = em.createQuery(cq);
            contactsGroups = query.getResultList();

            em.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return contactsGroups;
    }

    public boolean deleteContactGroup(long id){
        boolean success = false;

        try {
            EntityManager em = JpaUtil.getEmf().createEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            ContactGroup contactGroup = em.createQuery("SELECT cg FROM ContactGroup cg WHERE cg.groupId = :id", ContactGroup.class)
                    .setParameter("id", id)
                    .getSingleResult();

            if (contactGroup != null) {
                for (Contact contact : contactGroup.getContacts()) {
                    contact.getContactGroups().remove(contactGroup);
                }
                em.remove(contactGroup);
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
    public ContactGroup getContactGroup(long id) {
        ContactGroup contactGroup = null;

        try {
            EntityManager em = JpaUtil.getEmf().createEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<ContactGroup> cq = cb.createQuery(ContactGroup.class);
            Root<ContactGroup> contactGroupRoot = cq.from(ContactGroup.class);

            cq.select(contactGroupRoot);
            cq.where(cb.equal(contactGroupRoot.get("groupId"), id));

            TypedQuery<ContactGroup> query = em.createQuery(cq);
            contactGroup = query.getSingleResult();

            em.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return contactGroup;
    }

    @Override
    public boolean addContactInContactGroup(long idGroup, long idContact) {
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        boolean success = false;
        try {
            tx.begin();

            ContactGroup contactGroup = em.createQuery("SELECT cg FROM ContactGroup cg WHERE cg.groupId = :id", ContactGroup.class)
                    .setParameter("id", idGroup)
                    .getSingleResult();

            Contact contact = em.createQuery("SELECT c FROM Contact c WHERE c.idContact = :id", Contact.class)
                    .setParameter("id", idContact)
                    .getSingleResult();

            contactGroup.addContact(contact);
            em.merge(contactGroup);
            tx.commit();
            success = true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
        return success;
    }

    @Override
    public Set<Contact> getContactsInContactGroup(long idGroup) {
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        Set<Contact> contacts = null;
        try {
            ContactGroup contactGroup = em.createQuery("SELECT cg FROM ContactGroup cg WHERE cg.groupId = :id", ContactGroup.class)
                    .setParameter("id", idGroup)
                    .getSingleResult();

            if (contactGroup != null) {
                contacts = contactGroup.getContacts();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return contacts;
    }

    @Override
    public boolean deleteContactFromContactGroup(long idGroup, long idContact) {
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        boolean success = false;
        try {
            tx.begin();
            ContactGroup contactGroup = em.createQuery("SELECT cg FROM ContactGroup cg WHERE cg.groupId = :id", ContactGroup.class)
                    .setParameter("id", idGroup)
                    .getSingleResult();

            Contact contact = em.createQuery("SELECT c FROM Contact c WHERE c.idContact = :id", Contact.class)
                    .setParameter("id", idContact)
                    .getSingleResult();

            if (contactGroup != null && contact != null) {
                contactGroup.removeContact(contact);
                em.merge(contactGroup);
            }
            tx.commit();
            success = true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
        return success;
    }


}
