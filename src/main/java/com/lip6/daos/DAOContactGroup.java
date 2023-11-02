package com.lip6.daos;

import com.lip6.entities.ContactGroup;
import com.lip6.util.JpaUtil;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

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


}
