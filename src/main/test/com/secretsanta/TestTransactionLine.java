package com.secretsanta;

import com.secretsanta.databaseOperationsDAO.CustomerDAO;
import com.secretsanta.databaseOperationsDAO.TransactionLineDAO;
import com.secretsanta.domain.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.time.LocalDate;

public class TestTransactionLine {
    @Test
    public void crud() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        create(session);
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


        Transactions trns = new Transactions();
        trns.setCustomer(customer);// .setStreet("M");
        trns.setTransactionDate(LocalDate.now());



        session.beginTransaction();
        session.save(customer);
        session.save(trns);
        session.getTransaction().commit();


        TransactionLine transa = new TransactionLine();
        transa.setTransaction(trns);
        transa.setCustomer(customer);
        transa.setLocalDate(LocalDate.now());
        session.beginTransaction();
        session.save(transa);
        session.getTransaction().commit();

        transactionLine(transa,trns);

    }



    private void transactionLine(TransactionLine line, Transactions transaction)
    {
        TransactionLineDAO transactionLineDAO = new TransactionLineDAO();
        transactionLineDAO.addTransactionLine(line, transaction.getId());
    }
}
