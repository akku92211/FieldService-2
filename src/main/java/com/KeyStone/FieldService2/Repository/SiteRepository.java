package com.KeyStone.FieldService2.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.KeyStone.FieldService2.Entity.Site;

@Repository
public interface SiteRepository extends JpaRepository<Site,Long> {
	List<Site>findByCustomerId(Long customerId);

}
