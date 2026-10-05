package com.KeyStone.FieldService2.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KeyStone.FieldService2.Entity.Customer;
import com.KeyStone.FieldService2.Entity.Site;
import com.KeyStone.FieldService2.Repository.CustomerRepository;
import com.KeyStone.FieldService2.Repository.SiteRepository;

@Service
public abstract   class SiteServiceImpl implements SiteService {

	@Autowired
	private SiteRepository siteRepo;
	
	@Autowired
	private CustomerRepository customerRepo;

    public Site create(Site site) {
    	 Customer customer= customerRepo.findById(site.getCustomer().getId())
    			.orElseThrow(()-> new RuntimeException("Customer not found"));
    	
    	 site.setCustomer(customer);
    	
    	return siteRepo.save(site);
    }
    
    public Site updateSite(Long id,Site siteDetails) {
    	
    	Site existingsite=siteRepo.findById(id)
    			.orElseThrow(()-> new RuntimeException("Site not found"));
    	
    	existingsite.setSiteName(siteDetails.getSiteName());
    	existingsite.setAppertmentName(siteDetails.getAppartmentName());
    	existingsite.setFloorNo(siteDetails.getFloorNo());
    	existingsite.setAddressDetails(siteDetails.getAddressDetails());
    	existingsite.setCity(siteDetails.getCity());
    	existingsite.setState(siteDetails.getstate());
    	existingsite.setCountry(siteDetails.getCountry());
    	existingsite.setZipCode(siteDetails.getZipCode());

        if(siteDetails.getCustomer()!=null && siteDetails.getCustomer().getId()!=null) {
        	
        	Customer customer= customerRepo.findById(siteDetails.getCustomer().getId())
        			.orElseThrow(()-> new RuntimeException("Customer not found")); 
        	
        	existingsite.setCustomer(customer);
        }
        
        return siteRepo.save(existingsite);
        }
    
    @Override
    public List<Site> getSiteByCustomer(Long customerId) {
        return siteRepo.findByCustomerId(customerId);
    }

    @Override
    public Site deleteSite(Long id) {
        Site site = siteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Site not found"));

        siteRepo.delete(site);
        return site;
    }
   
}