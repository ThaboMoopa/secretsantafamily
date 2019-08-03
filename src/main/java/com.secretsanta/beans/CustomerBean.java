package com.secretsanta.beans;

import com.secretsanta.databaseOperationsDAO.CustomerDAO;
import com.secretsanta.databaseOperationsDAO.TransactionsDAO;
import com.secretsanta.domain.*;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import com.secretsanta.encryption.*;


@Named
@SessionScoped
public class CustomerBean implements Serializable {
    private CustomerDAO customerDAO;
    private Customer customer;


//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "customer_id")
    private long id;

   // @Column(name = "email")
    private String email;

    //@Column(name = "name")
    private String name;

    //@Column(name = "password")
    private String password;

   // @Column(name = "surname")
    private String surname;

    private boolean selectedCustomer;
    //@OneToMany(mappedBy = "customer")
    private Set<Transactions> transactions;


    /**
     * Setters and getters for the attributes of the customer class
     */

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
        this.email = email.toLowerCase();
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

    //Collections type§§
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


    public String redirect()
	{
		String result = null;
		if(!isEmailAvailable().equals("failure"))
		{
			saveCustomerRecord();
			result = "wishlist.xhtml";
		}
		else
		{
			result = "CustomerRegister.xhtml";
		}
		return result;
	}

	public String redirectToHomePage()
	{
		String log = null;
		if(login() !=null )
		{
		    System.out.println("************ In function******");
		    if(login().getEmail().equals("admin@admin.co.za") && login().getName().equals("admin"))
            {
                log = "adminHomepage.xhtml";
                TransactionsDAO transactionsDAO = new TransactionsDAO();
                transactionsDAO.addTransactions(customerDAO.getCustomerID());
            }
		    else
            {
                log = "homepage.xhtml";
                TransactionsDAO transactionsDAO = new TransactionsDAO();
                transactionsDAO.addTransactions(customerDAO.getCustomerID());
            }

		}
		else
			log = "login.xhtml";
		return log;
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No user with that details can be found"))
	}



	public Customer login()
	{
		customerDAO = new CustomerDAO();
		Customer result = customerDAO.login(email, password);
		return result;
	}

	public void saveCustomerRecord()
    {

        customerDAO = new CustomerDAO();
        customerDAO.add(new Customer(email.toLowerCase(), name, BCrypt.hashpw(password, BCrypt.gensalt(12)), surname,selectedCustomer));
        System.out.println("Saving the student record: ");
    }

    public void deleteStudentRecord()
    {
        System.out.println("Deleting student record");
        customerDAO = new CustomerDAO();
        customerDAO.delete(customer.getId());
    }

    public String updateCustomerRecord()
    {
        String redirect = "adminViewCustomer.xhtml";

        customerDAO = new CustomerDAO();
        customerDAO.update(new Customer(id, name, email.toLowerCase(), password, surname,selectedCustomer));
        return redirect;

    }

    public void logout()
	{
		customerDAO = new CustomerDAO();
		customerDAO.logout();

	}

	public void preload()
    {
        customerDAO = new CustomerDAO();
        customerDAO.preloadData();
    }


    public String isEmailAvailable()
	{
		customerDAO = new CustomerDAO();
		String result = customerDAO.isEmailAvailable(email.toLowerCase());
		return result;
	}

}
