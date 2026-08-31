package com.KeyStone.FieldService2.Service;

import java.util.List;

import com.KeyStone.FieldService2.Entity.Customer;

public interface CustomerService {

	Customer createCustomer(Customer customer);
	Customer updateCustomer(String email,Customer customer);
	Customer getCustomer(Long id);
	Customer getCustomerByEmail(String email);
    List<Customer>getAllCustomer();
    void deleteCustomer(Long id);

}
