package com.lip6.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ContactGroup {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long groupId;
	private String groupName;
	@ManyToMany(mappedBy="contactGroups", fetch = FetchType.EAGER)
	private Set <Contact> contacts=new HashSet<Contact>();

	public ContactGroup(String groupName) {
		this.groupName = groupName;
	}

	public ContactGroup() {

	}

	public long getGroupId() {
		return groupId;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Set<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

	public void addContact(Contact contact) {
		this.contacts.add(contact);
		contact.getContactGroups().add(this);
	}

	public void removeContact(Contact contact) {
		this.contacts.remove(contact);
		contact.getContactGroups().remove(this);
	}

}
