package com.lip6;

import com.lip6.entities.Contact;
import com.lip6.entities.ContactGroup;
import com.lip6.services.ServiceContact;
import com.lip6.services.ServiceContactGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class CarnetContactApplication implements CommandLineRunner {

	@Autowired
	@Qualifier("serviceContact")
	private ServiceContact serviceContact;

	@Autowired
	@Qualifier("serviceContactGroup")
	private ServiceContactGroup serviceContactGroup;

	@Autowired
	@Qualifier("contactKrish")
	private Contact contactKrish;

	@Autowired
	@Qualifier("contactAurel")
	private Contact contactAurel;

	@Autowired
	@Qualifier("contactMarie")
	private Contact contactMarie;

	@Autowired
	@Qualifier("contactGroupMIAGE")
	private ContactGroup contactGroupMIAGE;

	public static void main(String[] args) {
		SpringApplication.run(CarnetContactApplication.class, args);
	}

	@Override
	public void run(String... args) {
		serviceContact.createContact(contactKrish);
		serviceContact.createContact(contactAurel);
		serviceContact.createContact(contactMarie);

		serviceContactGroup.createContactGroup(contactGroupMIAGE);
	}
}
