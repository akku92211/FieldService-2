package com.KeyStone.FieldService2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.KeyStone.FieldService2.Entity.EmailLogin;

@Repository
public interface EmailLogRepository extends JpaRepository<EmailLogin,Long>{

}
