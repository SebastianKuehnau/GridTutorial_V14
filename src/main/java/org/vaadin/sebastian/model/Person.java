package org.vaadin.sebastian.model;

public class Person {

    public enum Gender {MALE, FEMALE};

    private Gender gender;
    private String name ;
    private String firstName;
    private Address address;

    public Person(Gender gender, String name, String firstName) {
        this.gender = gender;
        this.name = name ;
        this.firstName = firstName;
    }

    public Person(Gender gender, String name, String firstName, Address address) {
        this(gender, name, firstName);
        this.address = address;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
