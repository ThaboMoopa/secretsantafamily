package com.secretsanta.databaseOperationsDAO;

import com.secretsanta.domain.Customer;
import com.secretsanta.domain.WishList;
import com.secretsanta.hibernateUtil.HibernateUtil;
import com.secretsanta.repository.Repository;
import com.secretsanta.repository.RepositoryDAO;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class WishListDAO implements Serializable, RepositoryDAO<WishList> {

    private static Transaction transaction;
    private static Session session = HibernateUtil.getSessionFactory().openSession();
    private static Long id;

    @Override
    public void add(WishList wishList, long customerId) {
        try{
            transaction = session.beginTransaction();
            Customer dbCustomer = (Customer) session.get(Customer.class, customerId);
            wishList.setCustomer(dbCustomer);
            session.save(wishList);
            id = wishList.getId();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
            transaction.commit();
        }
    }

    @Override
    public void update(WishList wishList) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public WishList getById(long id) {
        return null;
    }

    @Override
    public List<WishList> getAll() {
        return null;
    }

    public Long getWishListID()
    {
        return id;
    }

    public WishList getByIdOfCustomer(long customerId)
    {
        System.out.println("*********** customer Id" + customerId);

        WishList wishList = new WishList();
        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM WishList Where customer.id =: customerId").setParameter("customerId", customerId);
            wishList = (WishList) query.uniqueResult();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
            transaction.commit();
        }
        System.out.println("______________" + wishList.getOptionOne());
        return wishList;
    }


}
