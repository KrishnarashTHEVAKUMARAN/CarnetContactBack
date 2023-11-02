package com.lip6.dtos;

import java.util.List;

public class DTOContact {
    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private DTOAddress address;
    private List<DTOPhoneNumber> phones;

    public DTOContact(long id, String email, String firstName, String lastName,
                      DTOAddress address, List<DTOPhoneNumber> phones) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phones = phones;
    }

    public DTOContact(long id, String email, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public DTOAddress getAddress() {
        return address;
    }

    public void setAddress(DTOAddress address) {
        this.address = address;
    }

    public List<DTOPhoneNumber> getPhones() {
        return phones;
    }

    public void setPhones(List<DTOPhoneNumber> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "DTOContactExtended{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address=" + address +
                ", phones=" + phones +
                '}';
    }
}
