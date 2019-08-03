package com.secretsanta.beans;

import com.secretsanta.databaseOperationsDAO.TransactionLineDAO;
import com.secretsanta.domain.Customer;
import com.secretsanta.domain.TransactionLine;
import com.secretsanta.domain.Transactions;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Named
@SessionScoped
public class TransactionLineBean implements Serializable {
    private TransactionLineDAO transactionLineDAO;

    private long id;

    private Customer customer;

    private LocalDate localDate;

    private Transactions transactions;

    public LocalDate getLocalDate() {
        return localDate.now();
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public TransactionLineBean(){}

    public void setTransaction(Transactions transactions)
    {
        this.transactions = transactions;
    }

    public Transactions getTransactions()
    {
        return transactions;
    }

    public void setId(long id)
    {
        this.id = id;
    }
    public long getId() {
        return id;
    }

    //Move to Controller
	public List<TransactionLine> getTransactionsLineList() {
		if(transactionLineDAO.populateTable(transactions.getId()).isEmpty())
		{
			transactionsLineList = null;
		}
		else
		{
			transactionsLineList = transactionLineDAO.populateTable(transactions.getId());
		}

		return transactionsLineList;
	}

    private List<TransactionLine> transactionsLineList;



	public String redirect()
	{
		//saveTransactionLineRecord();
		return "adminHomepage.xhtml";
	}

	//Used in the family App
    public String saveTransactionLine(long transactionId)
    {

        transactionLineDAO = new TransactionLineDAO();
        transactionLineDAO.addTransactionLine(new TransactionLine(getLocalDate()),transactionId);
        return "displaySelection.xhtml";
    }

}
