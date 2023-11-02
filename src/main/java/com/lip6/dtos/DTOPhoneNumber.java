package com.lip6.dtos;

public class DTOPhoneNumber {
    private long idPhoneNumber;
    private String phoneKind;
    private String phoneNumber;

    public DTOPhoneNumber(long idPhoneNumber, String phoneKind, String phoneNumber) {
        this.idPhoneNumber = idPhoneNumber;
        this.phoneKind = phoneKind;
        this.phoneNumber = phoneNumber;
    }

    public long getIdPhoneNumber() {
        return idPhoneNumber;
    }

    public void setIdPhoneNumber(long idPhoneNumber) {
        this.idPhoneNumber = idPhoneNumber;
    }

    public String getPhoneKind() {
        return phoneKind;
    }

    public void setPhoneKind(String phoneKind) {
        this.phoneKind = phoneKind;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "DTOPhoneNumber{" +
                "idPhoneNumber=" + idPhoneNumber +
                ", phoneKind='" + phoneKind + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
