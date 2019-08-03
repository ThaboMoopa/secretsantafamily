package com.secretsanta.beans;

import com.secretsanta.databaseOperationsDAO.WishListDAO;
import com.secretsanta.domain.Customer;
import com.secretsanta.domain.WishList;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.*;
import java.io.Serializable;

@Named
@SessionScoped
public class WishListBean implements Serializable {

    private long id;

    private String optionOne;

    private String optionTwo;

    private String optionThree;

    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOptionOne() {
        return optionOne;
    }

    public void setOptionOne(String optionOne) {
        this.optionOne = optionOne;
    }

    public String getOptionTwo() {
        return optionTwo;
    }

    public void setOptionTwo(String optionTwo) {
        this.optionTwo = optionTwo;
    }

    public String getOptionThree() {
        return optionThree;
    }

    public void setOptionThree(String optionThree) {
        this.optionThree = optionThree;
    }

    public String addWishList(long customerId)
    {
        WishListDAO wishListDAO = new WishListDAO();
        wishListDAO.add(new WishList(getOptionOne(), getOptionTwo(), getOptionThree()),customerId);
        return "login.xhtml";
    }

}
