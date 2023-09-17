package br.ada.customer.crud.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Customer implements Serializable, Comparable<Customer> {

    private Long id;
    private String name;
    private String document;
    private List<String> email;
    private List<String> telephone;
    private LocalDate birthDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public List<String> getTelephone() {
        return telephone;
    }

    public void setTelephone(List<String> telephone) {
        this.telephone = telephone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public int compareTo(Customer other) {
        return this.id.compareTo(other.id);
    }

}
