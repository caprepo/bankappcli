package org.cap.service;

import org.cap.exception.InsufficientOpeningBalanceException;
import org.cap.exception.InvalidCustomerException;
import org.cap.model.Account;
import org.cap.model.Customer;

public interface IAccountService {

	public Account createAccount(Customer customer, double amount)
			throws InvalidCustomerException ,InsufficientOpeningBalanceException ;
}
