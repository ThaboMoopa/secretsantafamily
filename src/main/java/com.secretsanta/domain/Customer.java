package com.secretsanta.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name="customer")

public class Customer implements Serializable {
    /**
     * Attributes associated with the customer
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "surname")
    private String surname;

    @Column(name="selectedCustomer")
    private boolean selectedCustomer;

    @OneToMany(mappedBy="customer")
    private Set<WishList> wishList;

    @OneToMany(mappedBy="customer")
    private Set<TransactionLine> transactionLine;

    @OneToMany(mappedBy = "customer")
    private Set<Transactions> transactions;


    public Customer() {
    }

    public Customer(long id, String email, String name, String password, String surname, boolean selectedCustomer){
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.surname = surname;
        this.selectedCustomer = selectedCustomer;

    }


    public Customer(String email,  String name, String password, String surname, boolean selectedCustomer) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.surname = surname;
        this.selectedCustomer = selectedCustomer;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(boolean selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}

//    @Override
//    public String toString() {
//        return "Customer [id=" + id + ", contact=" + contact + ", email=" + email + ", name=" + name + ", password="
//                + password + ", surname=" + surname + ", bankDetails=" + bankDetails + ", address=" + address
//                + ", transactions=" + transactions + "]";
//    }

//Collections type
//        public List<BankDetails> getBankDetails() {
//            return bankDetails;
//        }
//        public void setBankDetails(List<BankDetails> bankDetails) {
//            this.bankDetails = bankDetails;
//        }
//        public List<Address> getAddress() {
//            return address;
//        }
//        public void setAddress(List<Address> address) {
//            this.address = address;
//        }
//
//        public void setTransaction(List<Transactions> transactions) {
//            this.transactions = transactions;
//        }
//
//        public List<Transactions> getTransaction()
//        {
//            return transactions;
//        }
