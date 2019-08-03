package com.secretsanta.domain;

import com.secretsanta.databaseOperationsDAO.TransactionLineDAO;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="transaction_line")
//@Named
//@SessionScoped
public class TransactionLine implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="transaction_line_id")
	private long id;

	@Column(name="dayOfMonth")
	private LocalDate localDate;

	@ManyToOne
	@JoinColumn(name="transaction_id", nullable = false)
	private Transactions transactions;

	@ManyToOne
	@JoinColumn(name="customer_id", nullable = false)
	private Customer customer;

	public TransactionLine(LocalDate localDate) {
		this.localDate = localDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public LocalDate getLocalDate() {
		return localDate.now();
	}

	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}

	public TransactionLine() {
	}

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TransactionLine that = (TransactionLine) o;
		return id == that.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	//	@Override
//	public String toString() {
//		return "TransactionLine [id=" + id + ", quantity=" + quantity + ", total=" + total + ", transaction="
//				+ transactions + "]";
//	}


}
