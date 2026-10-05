package com.KeyStone.FieldService2.controller;

	import java.util.List;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PutMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.DeleteMapping;

	import com.KeyStone.FieldService2.Entity.UserEntity;
	import com.KeyStone.FieldService2.Service.UserAuthService;

	@RestController
	@RequestMapping("/api/technician")
	public class TechnicianController {

	    @Autowired
	    private UserAuthService userAuthService;

	    @GetMapping("/all")
	    public ResponseEntity<List<UserEntity>> getAllTechnicians() {
	        return ResponseEntity.ok(userAuthService.getTechnicians());
	    }
	    
	    @PutMapping("/update/{id}")
	    public ResponseEntity<UserEntity> updateTechnician(
	            @PathVariable Long id,
	            @RequestBody UserEntity technician) {

	        return ResponseEntity.ok(
	                userAuthService.updateTechnician(id, technician)
	        );
	    }
	    
	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<String> deleteTechnician(@PathVariable Long id) {

	        userAuthService.deleteTechnician(id);

	        return ResponseEntity.ok("Technician deleted successfully");
	    }
	}

