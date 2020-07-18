package org.cap.bankapp.test;

import static org.junit.Assert.*;

import org.cap.dao.IAccountDao;
import org.cap.exception.InsufficientOpeningBalanceException;
import org.cap.exception.InvalidCustomerException;
import org.cap.model.Account;
import org.cap.model.Address;
import org.cap.model.Customer;
import org.cap.service.AccountServiceImpl;
import org.cap.service.IAccountService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class BankAppTest {

	private IAccountService accountService;
	
	@Mock
	private IAccountDao accountDao;
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		//System.out.println(accountDao);
		accountService=new AccountServiceImpl(accountDao);
	}
	
	
	@Test(expected = InvalidCustomerException.class)
	public void createAccount_with_null_customer() 
				throws InvalidCustomerException, InsufficientOpeningBalanceException {
		Customer customer=null;
		accountService.createAccount(customer, 2000);
	}
	
	@Test(expected = InsufficientOpeningBalanceException.class)
	public void createAccount_with_invalid_amount() 
				throws InvalidCustomerException, InsufficientOpeningBalanceException {
		Address address=new Address("North Avvenue", "Pune");
		Customer customer=new Customer(123, "Tom",address);
		accountService.createAccount(customer, 200);
	}
	
	
	@Test
	public void createAccount_with_valid_Customer_And_Amount() throws InvalidCustomerException, InsufficientOpeningBalanceException {
		
		Address address=new Address("North Avvenue", "Pune");
		Customer customer=new Customer(123, "Tom",address);
		
		Account return_account=new Account();
		return_account.setAccountNo(1234);
		return_account.setCustomer(customer);
		return_account.setOpeningBalance(3000);
		
		//Declaration
		Mockito.when(accountDao.saveAccount(return_account)).thenReturn(true);
		
		//Actual
		Account account=accountService.createAccount(customer, 3000);
		
		//Verify
		Mockito.verify(accountDao).saveAccount(return_account);
		
		//assertEquals(account.getBalance(), return_account.getBalance(),0.0);
		
	}

}
