package com.secretsanta;

import com.secretsanta.beans.TransactionsBean;
import com.secretsanta.databaseOperationsDAO.WishListDAO;
import com.secretsanta.domain.Customer;
import com.secretsanta.domain.Transactions;
import com.secretsanta.domain.WishList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.time.LocalDate;

public class TestWishList {
    @Test
    public void crud() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        retrieveData(session);
        //create(session);
        //read(session);

        //update(session);
        // read(session);

        //delete(session);
        //read(session);

        session.close();
    }

    private void create(Session session) {
        System.out.println("Creating car records...");
        Customer customer = new Customer();
        customer.setName("Thembi");
        customer.setSurname("Thembi");
        customer.setEmail("thembi.moopa@gmail.com");
        customer.setPassword("mysql");




//        Address address2 = new Address();
//        address2.setStreet("L");
//        address2.setPostalCode(837);
//        address2.setHouseNumber(27);
//        address2.setDescription("Main");
//        address2.setCustomer(customer);


        session.beginTransaction();
        session.save(customer);
        session.getTransaction().commit();


        WishList wishList = new WishList("Bag", "Shoes","link");
        wishList.setCustomer(customer);
        session.beginTransaction();
        session.save(wishList);
        session.getTransaction().commit();


        WishListDAO doa = new WishListDAO();
        doa.add(wishList, customer.getId());

    }

    public void createTest(Session session)
    {


    }

    public void retrieveData(Session session)
    {
        WishListDAO wishListDAO = new WishListDAO();
        WishList wish = wishListDAO.getByIdOfCustomer(2);
        System.out.println(wish.getOptionOne());

    }
}
