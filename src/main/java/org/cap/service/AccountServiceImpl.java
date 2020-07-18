package org.cap.service;

import org.cap.dao.AccountDaoImp;
import org.cap.dao.IAccountDao;
import org.cap.exception.InsufficientOpeningBalanceException;
import org.cap.exception.InvalidCustomerException;
import org.cap.model.Account;
import org.cap.model.Customer;
import org.cap.util.AcccountUtility;

public class AccountServiceImpl implements IAccountService {
	
	private IAccountDao accountDao;
	
	public AccountServiceImpl() {
		this.accountDao=new AccountDaoImp();
	}

	public AccountServiceImpl(IAccountDao accountDao) {
		this.accountDao=accountDao;
	}

	@Override
	public Account createAccount(Customer customer, double amount) 
			throws InvalidCustomerException, InsufficientOpeningBalanceException {
	
		if(customer==null)
			throw new InvalidCustomerException("Sorry! Customer id null!");
		if(amount<1000)
			throw new InsufficientOpeningBalanceException("Sorry! INsufficient balance!");
		
		Account account=new Account();
		account.setAccountNo(AcccountUtility.generateAccountId());
		account.setCustomer(customer);
		account.setOpeningBalance(amount);
		
		if(accountDao.saveAccount(account))
			return account;
		
		return null;
	}

}
