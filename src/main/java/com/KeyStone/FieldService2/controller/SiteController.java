package com.KeyStone.FieldService2.controller;

	import java.util.List;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.PutMapping;
	import org.springframework.web.bind.annotation.DeleteMapping;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;

	import com.KeyStone.FieldService2.Entity.Site;
	import com.KeyStone.FieldService2.Repository.SiteRepository;

	@RestController
	@RequestMapping("/api/site")
	public class SiteController {

	    @Autowired
	    private SiteRepository siteRepository;

	    @PostMapping("/create")
	    public ResponseEntity<Site> createSite(@RequestBody Site site) {

	        return ResponseEntity.ok(siteRepository.save(site));
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Site> getSite(@PathVariable Long id) {

	        return ResponseEntity.ok(
	                siteRepository.findById(id)
	                        .orElseThrow(() ->
	                                new RuntimeException("Site not found"))
	        );
	    }

	    @GetMapping("/customer/{customerId}")
	    public ResponseEntity<List<Site>> getSitesByCustomer(
	            @PathVariable Long customerId) {

	        return ResponseEntity.ok(
	                siteRepository.findByCustomerId(customerId)
	        );
	    }

	    @GetMapping("/all")
	    public ResponseEntity<List<Site>> getAllSites() {

	        return ResponseEntity.ok(siteRepository.findAll());
	    }
	    
	    @PutMapping("/update/{id}")
	    public ResponseEntity<Site> updateSite(
	            @PathVariable Long id,
	            @RequestBody Site updatedSite) {

	        Site site = siteRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Site not found"));

	        site.setSiteName(updatedSite.getSiteName());
	        site.setAppertmentName(updatedSite.getAppartmentName());
	        site.setFloorNo(updatedSite.getFloorNo());
	        site.setAddressDetails(updatedSite.getAddressDetails());
	        site.setCity(updatedSite.getCity());
	        site.setState(updatedSite.getstate());
	        site.setCountry(updatedSite.getCountry());
	        site.setZipCode(updatedSite.getZipCode());
	        site.setCustomer(updatedSite.getCustomer());

	        return ResponseEntity.ok(siteRepository.save(site));
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<String> deleteSite(
	            @PathVariable Long id) {

	        if (!siteRepository.existsById(id)) {
	            throw new RuntimeException("Site not found");
	        }

	        siteRepository.deleteById(id);

	        return ResponseEntity.ok("Site deleted successfully");
	    }
	}

