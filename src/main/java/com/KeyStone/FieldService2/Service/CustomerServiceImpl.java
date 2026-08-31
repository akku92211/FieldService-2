package com.KeyStone.FieldService2.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KeyStone.FieldService2.Entity.Customer;
import com.KeyStone.FieldService2.Repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService  {

	@Autowired
	private CustomerRepository customerRepo;
	
	@Override
	public Customer createCustomer(Customer customer){
		
		if(customerRepo.existsByEmail(customer.getEmail())) {
			throw new RuntimeException("customer already exists");
		
		}
		customer.setActiva(true);
		customer.setCreatedAt(LocalDateTime.now());
		return customerRepo.save(customer);
		
	}
	
	public Customer updateCustomer(String email,Customer customer) {
		
		Customer existingCustomer= customerRepo.findByEmail(email)
				.orElseThrow(()-> new RuntimeException("Customer not found"));
		
		existingCustomer.setCompanyName(customer.getCompanyName());
		existingCustomer.setContactPerson(customer.getContactPerson());
		existingCustomer.setPhone(customer.getPhone());
		existingCustomer.setAddress(customer.getAddress());
		existingCustomer.setActiva(customer.isActiva());
		
		return customerRepo.save(existingCustomer);

	}
	
	public Customer getCustomer(Long id) {
		return customerRepo.findById(id)
				.orElseThrow(()-> new RuntimeException("Customer not found"));
	}
	
	public Customer getCustomerByEmail(String email) {
		return customerRepo.findByEmail(email)
				.orElseThrow(()-> new RuntimeException("User not found"));
		
	}
	
	public List<Customer>getAllCustomer(){
		return customerRepo.findAll();
	}
	
	public void deleteCustomer(Long id) {
		Customer customer=customerRepo.findById(id)
				.orElseThrow(()-> new RuntimeException("Customer not found"));
		
		customerRepo.delete(customer);
	}
	
}
