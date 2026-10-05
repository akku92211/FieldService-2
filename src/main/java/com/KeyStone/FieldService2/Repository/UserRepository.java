package com.KeyStone.FieldService2.Repository;

import java.util.Optional;
import java.util.List;
import com.KeyStone.FieldService2.Enum.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.KeyStone.FieldService2.Entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUserEmail(String userEmail);

    Optional<UserEntity> findByResetToken(String resetToken);
    
    List<UserEntity> findByRole(Role role);

}