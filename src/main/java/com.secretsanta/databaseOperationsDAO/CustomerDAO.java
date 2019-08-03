package com.secretsanta.databaseOperationsDAO;




import com.secretsanta.domain.Customer;

import com.secretsanta.hibernateUtil.HibernateUtil;
import com.secretsanta.repository.Repository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.query.Query;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import com.secretsanta.encryption.*;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

//import javax.faces.application.FacesMessage;
//import javax.faces.context.FacesContext;

@Named
@SessionScoped
public class CustomerDAO implements Serializable, Repository<Customer> {
    private static Transaction transaction;
    private static Session session = HibernateUtil.getSessionFactory().openSession();
    private static Long id;
    private static Customer customer;

    @Override
    public void add(Customer customer) {
        try {
            transaction = session.beginTransaction();
            session.save(customer);
            id = customer.getId();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
            transaction.commit();
        }
    }

    public String isEmailAvailable(String email)
    {
        String result = "failure";
        try{
            Customer particularCustomer = new Customer();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Customer Where email= :email").setParameter("email", email);
            particularCustomer = (Customer) query.uniqueResult();

            if(particularCustomer == null)
            {
               result = "success";
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Email already exists!"));
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
            transaction.commit();
        }

        return result;
    }

    @Override
    public Customer getById(Long id) {
        Customer particularCustomer = new Customer();

        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Customer WHERE id= :customer_id").setParameter("customer_id", id);
            particularCustomer = (Customer) query.uniqueResult();

            System.out.println("Student with Id " + id + " is successfully fetched from db");

            //XHTML reponse Text
           // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("findCustomerById", id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            transaction.commit();
        }

        return particularCustomer;
    }

    @Override
    public void update(Customer customer) {
        try{
            transaction = session.beginTransaction();
            session.update(customer);
            session.clear();
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
    public void delete(Long id) {
        try{
            transaction = session.beginTransaction();
            Customer custId = (Customer) session.load(Customer.class, new Long(id));
            session.delete(custId);

            //XHTML response Text
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("deletedId", id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
            transaction.commit();
        }
    }

    //Login to the
    public Customer login(String email, String password)
    {
            String result = "failure";
        try{

            Customer particularCustomer = new Customer();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Customer WHERE email= :email").setParameter("email", email);
            particularCustomer = (Customer) query.uniqueResult();

                if(particularCustomer == null)
                {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Account is not be found, please register!"));
                }
                else if(BCrypt.checkpw(password, particularCustomer.getPassword()))
                {
                    customer = particularCustomer;
                    id = customer.getId();
                }

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                transaction.commit();

            }
            return customer;
        }

    public String logout() {
        session.close();
        return "login.xhtml";
    }

    public Long getCustomerID()
    {
        return id;
    }

    public void preloadData()
    {
        List<Customer> reading = readData("admin");
        if(!reading.isEmpty()){
            System.out.println("Customer already exists in the database");
        }
        else{
            try{
                String password = BCrypt.hashpw("admin", BCrypt.gensalt(12));
                List<Customer> listCustomer = new ArrayList<>();
                listCustomer.add(new Customer("admin@admin.co.za","admin", password,  "admin", true));
                listCustomer.add(new Customer("bozin@admin.co.za","Bozi", password,  "Bozi", false ));
                listCustomer.add(new Customer("lebo@admin.co.za", "Lebo",password, "Lebo", false ));
                listCustomer.add(new Customer("lerato@admin.co.za","Lerato", password, "Lerato", false ));
                listCustomer.add(new Customer("theo@admin.co.za", "theo",password, "theo", false ));
                listCustomer.add(new Customer("main@admin.co.za","main", password, "main",false ));
                listCustomer.add(new Customer("menu@admin.co.za","menu", password, "menu", false ));
                listCustomer.add(new Customer("love@admin.co.za","love", password, "love", false ));

                for (Customer customer : listCustomer) {
                    transaction = session.beginTransaction();
                    session.save(customer);
                    transaction.commit();
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public List<Customer> readData(String name) {

        List<Customer> particularCustomerList = new ArrayList<>();

        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Customer WHERE name= :customer_name").setParameter("customer_name", name);
            particularCustomerList = query.list();

            //XHTML reponse Text
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("findCustomerById", id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            transaction.commit();
        }

        return particularCustomerList;
    }

    public ArrayList<Customer> populateTable()
    {
        Customer particularCustomer = new Customer();

        ArrayList<Customer> particularCustomerList = new ArrayList<>();

        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Customer");
            //particularProduct =  query.list();
            particularCustomerList = (ArrayList<Customer>) query.list();

            //XHTML reponse Text
           FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("findProductById", id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            transaction.commit();
        }

        return particularCustomerList;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        return null;
    }

    //Secret santa working
    public Customer selectRandomCustomer()
    {
        Customer customerSelection = new Customer();
        try{
            SecureRandom random = new SecureRandom();
            int rand = random.nextInt(5);

            List<Customer> getList = new ArrayList<>();

            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Customer where selectedCustomer=false ");
            getList = query.list();
            customerSelection =  getList.get(rand);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
            customerSelection.setSelectedCustomer(true);
            transaction.commit();
            update(customerSelection);
        }
        return customerSelection;
    }
}
