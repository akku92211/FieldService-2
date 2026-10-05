package com.KeyStone.FieldService2.Cloud;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

	import com.cloudinary.Cloudinary;

	@Configuration
	public class CloudinaryConfig {

	    @Value("${cloudinary.cloud-name}")
	    private String Name;

	    @Value("${cloudinary.api-key}")
	    private String Key;

	    @Value("${cloudinary.api-secret}")
	    private String Secret;

	    @Bean
	    public Cloudinary cloudinary() 	{
	    	
	    Map<String,Object>config=new HashMap<>();
		config.put("cloud-name",Name );
		config.put("api-key",Key );
		config.put("api-secrt",Secret );
		
		return new Cloudinary(config); 
		
	    }
	}

