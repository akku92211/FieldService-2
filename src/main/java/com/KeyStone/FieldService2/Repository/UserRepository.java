package com.KeyStone.FieldService2.Repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.KeyStone.FieldService2.Enum.Role;

@Repository
public interface UserRepository extends JpaRepository<UserRepository,Long> {

	Optional<UserRepository>findByUserEmail(String userEmail);

	static Optional<UserRepository> findByResetToken(String token) {
		return null;
	}

	String getUserEmail();

	String getPassword();

	Object getRole();

	void setResetTokenExpiry(Object object);

	Date getResetTokenExpiry();

	void setPassword(String encode);

	void setRole(Role role);

	void setUsername(String userEmail);
	
}
