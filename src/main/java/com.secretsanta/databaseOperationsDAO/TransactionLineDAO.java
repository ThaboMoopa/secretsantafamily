package com.secretsanta.databaseOperationsDAO;

import com.secretsanta.domain.Customer;
import com.secretsanta.domain.TransactionLine;
import com.secretsanta.domain.Transactions;
import com.secretsanta.hibernateUtil.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//import javax.faces.context.FacesContext;

@Named
@SessionScoped
public class TransactionLineDAO implements Serializable {
    private static Transaction transaction;
    private static Session session = HibernateUtil.getSessionFactory().openSession();
    private static long id;
    private static TransactionsDAO transactionDAO = new TransactionsDAO();
    //private static long customerId;

    //Used for the Secret santa APP
    public void addTransactionLine(TransactionLine transactionLine, long transactionId)
    {
        try{
            Customer customer = new CustomerDAO().selectRandomCustomer();
            transaction = session.beginTransaction();
            Transactions dbTransactions  = (Transactions) session.get(Transactions.class, transactionId);
            Customer dbCustomer = (Customer) session.get(Customer.class, customer.getId());
            transactionLine.setTransaction(dbTransactions);
            transactionLine.setCustomer(dbCustomer);
            session.save(transactionLine);
            id = transactionLine.getId();

            //buttonPressed(productId);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
            transaction.commit();
        }
    }

    //Delete customer from DB
    public void deleteTransactionLine(long id){

        try{
            transaction = session.beginTransaction();
            TransactionLine transactionLineId = (TransactionLine) session.load(TransactionLine.class, new Long(id));
            session.delete(transactionLineId);

            //XHTML response Text
            //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("deletedId", id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
            transaction.commit();
        }
    }



    //Read Customer details
    public TransactionLine getTransactionLineById(long id){

        TransactionLine particularTransactionLine = new TransactionLine();

        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM TransactionLine WHERE id= :transactionLine_id").setParameter("transactionLine_id", id);
            particularTransactionLine = (TransactionLine) query.uniqueResult();
            //XHTML reponse Text
            //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("findAddressById", id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            transaction.commit();
        }

        return particularTransactionLine;
    }

    //Method to update the Customer
    public void updateTransactionLine(TransactionLine transactionLine)
    {
    }


    //Secret santa Find the Transaction Line where wishList is and transaction Id
    public TransactionLine populate(long transactionId)
    {
        TransactionLine line = new TransactionLine();
        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM TransactionLine WHERE transactions.id= :transactionId").setParameter("transactionId", transactionId);
            line = (TransactionLine) query.uniqueResult();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
            transaction.commit();
        }
        return line;
    }

    //Find All TransactionLine
    public List<TransactionLine> populateTable(long transactionId)
    {

        List<TransactionLine> particularTransactionLineList = new ArrayList<>();

        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM TransactionLine WHERE transactions.id= :transactionId").setParameter("transactionId" ,transactionId);
            particularTransactionLineList =  query.list();

            //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("findProductById", id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            transaction.commit();
        }

        return particularTransactionLineList;
    }

    public void TransactionLineAddButton(long id)
    {
        TransactionLine transactionLine = getTransactionLineById(id);
        //tranLine.setId(line.getId());


            transactionLine.getTransactions();
            updateTransactionLine(transactionLine);
    }



    //Disable button when clicked
    public boolean buttonPressedProductOne()
    {
        System.out.println("****************** button pressed ");


        return false;
    }
    //Find All TransactionLine
    public List<TransactionLine> populateTableAdmin()
    {

        List<TransactionLine> particularTransactionLineList = new ArrayList<>();

        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM TransactionLine");
            particularTransactionLineList =  query.list();

            //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("findProductById", id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            transaction.commit();
        }

        return particularTransactionLineList;
    }
}
